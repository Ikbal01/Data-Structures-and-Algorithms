import java.util.Scanner;

public class Knights {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int knightsCount = scanner.nextInt();
        RoundLinkedList roundLinkedList = new RoundLinkedList();
        for (int i = 1; i <= knightsCount; i += 2) {
            roundLinkedList.add(i);
        }

        roundLinkedList.connectStartEnd();
        Node temp;

        if (knightsCount % 2 == 0) {
            temp = roundLinkedList.start;
        } else {
            temp = roundLinkedList.end;
        }

        while (roundLinkedList.size > 2) {
            temp.next = temp.next.next;
            roundLinkedList.size--;
            temp = temp.next;
        }

        System.out.println(temp.number);
    }

    private static class RoundLinkedList {
        private Node start;
        private Node end;
        private int size;

        public RoundLinkedList() {
            size = 0;
        }

        public void add(int num) {
            Node newNode = new Node(num);

            if (start == null) {
                start = newNode;
                end = start;
            } else {
                end.next = newNode;
                end = newNode;
            }
            size++;
        }

        public void connectStartEnd() {
            end.next = start;
        }
    }

    private static class Node {
        private Node next;
        private int number;

        public Node(int number) {
            this.number = number;
        }
    }
}
