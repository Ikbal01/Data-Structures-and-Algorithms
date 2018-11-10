import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Potok {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();

        MyLinkedList list = new MyLinkedList();

        getInput(list, inputReader);

        int minNum = list.head.data;
        int maxNum = list.head.data;
        long sum = 0;

        Node node = list.head;
        int currNum;

        for (int i = 0; i < list.size; i++) {
            currNum = node.data;
            if (minNum > currNum) {
                minNum = currNum;
            } else if (maxNum < currNum) {
                maxNum = currNum;
            }
            sum += currNum;
            node = node.next;
        }

        System.out.printf("%d %d %d", minNum, maxNum, sum);

    }

    private static void getInput(MyLinkedList list, InputReader inputReader) {
        int currInput;

        try {
            while (true) {
                currInput = inputReader.readInt();
                list.add(currInput);
            }
        } catch (Exception e) {

        }
    }

    private static class MyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        public MyLinkedList() {
            head = null;
            size = 0;
        }

        public void add(int element) {
            Node currNode = new Node(element);

            if (head != null) {
                tail.next = currNode;
                tail = currNode;
            } else {
                tail = currNode;
                head = tail;
            }
            size++;
        }
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        InputReader() {
            this.stream = System.in;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}