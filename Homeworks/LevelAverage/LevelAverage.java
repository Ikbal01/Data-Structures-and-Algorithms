import java.util.*;

public class LevelAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        BinarySearchTree tree = new BinarySearchTree();

        for (int i = 0; i < n; i++) {
            tree.insert(scanner.nextInt());
        }

        printLevelAverage(tree);
    }

    private static void printLevelAverage(BinarySearchTree tree) {
        StringBuilder result = new StringBuilder();
        long sum = 0;
        int size;

        LinkedList<Node> currLevel = new LinkedList<>();
        LinkedList<Node> nextLevel = new LinkedList<>();
        currLevel.add(tree.root);

        while (!currLevel.isEmpty()) {
            size = currLevel.size();
            while (!currLevel.isEmpty()) {
                Node curr = currLevel.remove();
                sum += curr.value;
                if (curr.left != null) {
                    nextLevel.add(curr.left);
                }
                if (curr.right != null) {
                    nextLevel.add(curr.right);
                }
            }
            result.append(String.format("%.2f\n", (double)sum / (double)size));
            sum = 0;
            currLevel = nextLevel;
            nextLevel = new LinkedList<>();
        }

        System.out.println(result.toString());
    }


    private static class BinarySearchTree {
        private Node root;

        public BinarySearchTree() {
            root = null;
        }

        public void insert(int value) {
            if (root == null) {
                root = new Node(value);
            } else {
                insert(root, value);
            }
        }

        private void insert(Node root, int value) {
            if (root.value > value) {
                if (root.left == null) {
                    root.left = new Node(value);
                } else {
                    insert(root.left, value);
                }
            } else {
                if (root.right == null) {
                    root.right = new Node(value);
                } else {
                    insert(root.right, value);
                }
            }
        }
    }

    private static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
