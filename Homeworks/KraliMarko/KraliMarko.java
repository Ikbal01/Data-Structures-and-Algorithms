import java.util.Scanner;

public class KraliMarko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        MyLinkedList nNumbers = new MyLinkedList();

        for(int i = 0; i < n; i++) {
            nNumbers.add(scanner.nextInt());
        }

        int p = scanner.nextInt();

        int[] pNumbers = new int[1235];

        for(int i = 0; i < p; i++) {
            int currNum = scanner.nextInt();

            if (currNum >= 1 && currNum <= 1234) {
                pNumbers[currNum] = currNum;
            }
        }

        int count = findUniquePairCount(nNumbers, pNumbers);

        System.out.println(count);
    }

    public static int findUniquePairCount(MyLinkedList nNumbers, int[] pNumbers) {
        int counter = 0;
        HashLinkedLists hashArr = new HashLinkedLists(1235);

        Node currNode = nNumbers.getHead();
        int currNum;
        int nextNum;

        for(int i = 0; i < nNumbers.size() - 1; i++) {
            currNum = currNode.getData();
            nextNum = currNode.getNext().getData();

            if (pNumbers[currNum] == currNum && pNumbers[nextNum] == nextNum) {
                if (!(hashArr.contains(currNum, nextNum))) {
                    counter++;
                    hashArr.add(currNum, nextNum);
                    hashArr.add(nextNum, currNum);
                }
            }
            currNode = currNode.getNext();
        }

        return counter;
    }

    private static class HashLinkedLists {
        MyLinkedList[] list;

        public HashLinkedLists(int size) {
            list = new MyLinkedList[size];
        }

        public void add(int head, int num) {
            if (list[head] == null) {
                list[head] = new MyLinkedList();
                list[head].add(head);
            }
            list[head].add(num);
        }

        public boolean contains(int head, int num) {
            if (list[head] == null) {
                return false;
            }

            Node currNode = list[head].getHead();

            for (int i = 0; i < list[head].size - 1; i++) {
                currNode = currNode.getNext();
                if (currNode.getData() == num) {
                    return true;
                }
            }

            return false;
        }
    }

    private static class MyLinkedList {
        private Node head;
        private Node tail;

        private int size;

        public MyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        public void add(int element) {
            Node currNode = new Node(element);

            if (head != null) {
                tail.setNext(currNode);
                tail = currNode;
            } else {
                tail = currNode;
                head = tail;
            }
            size++;
        }

//        public int remove() {
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

        public Node(int data) {
            this.data = data;
            next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
