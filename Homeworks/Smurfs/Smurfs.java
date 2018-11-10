import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Scanner;

public class Smurfs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int requests = Integer.parseInt(scanner.nextLine());

        SmurfList[] texts = new SmurfList[requests];

        String line;
        for (int i = 0; i < requests; i++) {
            line = scanner.nextLine();

            texts[i] = new SmurfList();
            correctTheLine(texts[i], line);
        }

        for (int i = 0; i < texts.length; i++) {
            print(texts[i]);
        }

    }

    public static void print(SmurfList list) {
        Node curr = list.head;
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < list.size; i++) {
            strBuilder.append(curr.data);
            curr = curr.next;
        }
        System.out.println(strBuilder.toString());
    }

    public static void correctTheLine(SmurfList list, String str) {
        boolean islastButton = true; // ']'

        SmurfList temp = new SmurfList();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[') {
                islastButton = false;

                if (!temp.isEmpty()) {
                    list.toBeginning(temp);
                    temp.clear();
                }

            } else if (str.charAt(i) == ']') {
                islastButton = true;

                if (!temp.isEmpty()) {
                    list.toBeginning(temp);
                    temp.clear();
                }
            } else {

                if (islastButton) {
                    list.addLast(str.charAt(i));
                } else {
                    temp.addLast(str.charAt(i));
                }
            }
        }

        if (!temp.isEmpty()) {
            list.toBeginning(temp);
        }
    }

    private static class SmurfList {
        private Node head;
        private Node tail;

        private int size;

        public SmurfList() {
            head = null;
            tail = null;
            size = 0;
        }

        public void addLast(char element) {
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

        public void toBeginning(SmurfList list) {
            if (list.isEmpty()) {
                return;
            }

            if (head == null) {
                head = list.head;
                tail = list.tail;
                size = list.size;

            } else {
                list.tail.next = head;
                head = list.head;

                size += list.size;
            }
        }
//
//        public void addFirst(char element) {
//            Node currNode = new Node(element);
//
//            if (head == null) {
//                head = currNode;
//                tail = head;
//            } else {
//                currNode.next = head;
//                head = currNode;
//            }
//            size++;
//        }
//
//        public char remove() {
//            if (head == null) {
//                throw new NoSuchElementException();
//            }
//
//            Node firstElement = head;
//            head = head.getNext();
//
//            size--;
//            return firstElement.getData();
//        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        public Node getHead() {
            return head;
        }

        public Node getTail() {
            return tail;
        }
    }

    private static class Node {
        private char data;
        private Node next;

        public Node(char data) {
            this.data = data;
            next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public char getData() {
            return data;
        }
    }
}


