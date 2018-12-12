import java.util.Scanner;

public class VolleyballFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AVL team = new AVL();

        int firstParticipNum = scanner.nextInt();
        team.insert(firstParticipNum);

        int numberFPls = scanner.nextInt();

        int bestFriend;
        int currStudent;

        for (int i = 0; i < numberFPls; i++) {
            bestFriend = scanner.nextInt();
            currStudent = scanner.nextInt();

            if (team.contains(bestFriend)) {
                team.insert(currStudent);
            }
        }

        System.out.println(team.size());
    }

    private static class AVL {

        private Node root;
        private int size;

        public AVL() {
            root = null;
        }

        private class Node {
            private Node left;
            private Node right;

            private int value;
            private int height;

            public Node(int value) {
                this.value = value;
                height = 1;
            }
        }

        public void insert(int value) {
            root = insert(root, value);
        }

        private Node insert(Node root, int value) {
            if (root == null) {
                size++;
                return new Node(value);
            }

            if (value < root.value) {
                root.left = insert(root.left, value);
            } else if (value > root.value){
                root.right = insert(root.right, value);
            } else {
                return root;
            }

            root.height = max(height(root.left), height(root.right)) + 1;

            int balance = getBalance(root);

            if (balance > 1 && value < root.left.value) {
                return rightRotate(root);
            } else if (balance < -1 && value > root.right.value) {
                return leftRotate(root);
            } else if (balance > 1 && value > root.left.value) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            } else if (balance < -1 && value < root.right.value) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        private Node rightRotate(Node root) {
            Node left = root.left;

            root.left = left.right;
            left.right = root;

            root.height = max(height(root.left), height(root.right)) + 1;
            left.height = max(height(left.left), height(left.right)) + 1;

            return left;
        }

        private Node leftRotate(Node root) {
            Node right = root.right;

            root.right = right.left;
            right.left = root;

            root.height = max(height(root.left), height(root.right)) + 1;
            right.height = max(height(right.left), height(right.right)) + 1;

            return right;
        }

        private int getBalance(Node node) {
            if (node == null) {
                return 0;
            }

            return height(node.left) - height(node.right);
        }

        private int max(int a, int b) {
            return (a > b) ? a : b;
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }

            return node.height;
        }

        public boolean contains(int value) {
            return contains(root, value);
        }

        private boolean contains(Node root, int value) {
            if (root == null) {
                return false;
            }

            if (value == root.value) {
                return true;
            } else if (value < root.value) {
                return contains(root.left, value);
            } else {
                return contains(root.right, value);
            }
        }

        public int size() {
            return size;
        }
    }
}
