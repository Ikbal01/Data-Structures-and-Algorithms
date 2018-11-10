import java.util.NoSuchElementException;
import java.util.Scanner;

public class Kontrolna2SDA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MyLinkedList list = new MyLinkedList();

        int requests = scanner.nextInt();
        String request;

        for (int i = 0; i < requests; i++) {
            request = scanner.next();

            switch (request) {
                case "add" :
                    add(list, scanner);
                    break;
                case "gun" :
                    gun(list);
                    break;
                case "milen":
                    milen(list);
                    break;
                default :
                    System.out.println("wrong input");
            }
        }


        Node currHead = list.head;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.size; i++) {
            stringBuilder.append(currHead.data);
            stringBuilder.append(" ");
            currHead = currHead.next;
        }

        System.out.printf("%d\n%s",list.size, stringBuilder.toString());
    }

    public static void add(MyLinkedList list, Scanner scanner) {
        int num = Integer.parseInt(scanner.next());
        list.add(num);
    }

    public static void gun(MyLinkedList list) {
        list.removeLast();
    }

    public static void milen(MyLinkedList list) {
        int mid = list.size / 2;

        if (mid == 0) {
            return;
        }

        Node currHead = list.head;
        Node currMid = list.middle;

        list.head = list.middle.next;
        list.head.previous = null;
        currMid.next = null;

        list.tail.next = currHead;
        currHead.previous = list.tail;

        if (list.size % 2 == 0) {
            list.middle = list.tail;
        } else {
            list.middle = list.tail.previous;
        }
        list.tail = currMid;
    }

    private static class MyLinkedList {
        private Node head;
        private Node tail;
        private Node middle;

        private int size;

        public MyLinkedList() {
            head = null;
            tail = null;
            middle = null;
            size = 0;
        }

        public void add(int element) {
            Node currNode = new Node(element);
            size++;

            if (head != null) {
                currNode.setPrevious(tail);
                tail.setNext(currNode);
                tail = currNode;
                if (size % 2 == 0) {
                    middle = middle.next;
                }
            } else {
                tail = currNode;
                head = tail;
                middle = new Node(0);
                middle.next = tail;
            }
        }

        public int removeLast() {
            if (head == null) {
                throw new NoSuchElementException();
            }

            Node last = tail;

            tail = tail.previous;

            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            if (size % 2 == 0) {
                middle = middle.previous;
            }
            size--;
            return last.getData();
        }

        public int size() {
            return size;
        }

        public Node getHead() {
            return head;
        }
    }

    private static class Node {
        private int data;
        private Node next;
        private Node previous;

        public Node(int data) {
            this.data = data;
            next = null;
            previous = null;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
