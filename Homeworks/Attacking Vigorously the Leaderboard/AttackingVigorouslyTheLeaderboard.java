import java.text.DecimalFormat;
import java.util.Scanner;

public class AttackingVigorouslyTheLeaderboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AVLTree tree = new AVLTree();
        String operation;
        double number = 0;
        int N = Integer.parseInt(scanner.next());

        for (int i = 0; i < N; i++) {
            operation = scanner.next();
            if (!operation.equals("print")) {
                number = Double.parseDouble(scanner.next());
            }

            if (operation.equals("add")) {
                tree.add(number);
            } else if (operation.equals("remove")) {
                tree.remove(number);
            } else if (operation.equals("contains")) {
                if (tree.contains(number)) {
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            } else if (operation.equals("print")) {
                tree.print();
            }
        }
    }

    private static class AVLTree {

        private Node root;

        public AVLTree() {
            root = null;
        }

        private class Node {
            private Node left;
            private Node right;

            private double value;
            private int height;

            public Node(double value) {
                this.value = value;
                height = 1;
            }
        }

        public void add(double value) {
            root = add(root, value);
        }

        private Node add(Node root, double value) {
            if (root == null) {
                return new Node(value);
            }

            if (value < root.value) {
                root.left = add(root.left, value);
            } else if (value > root.value){
                root.right = add(root.right, value);
            } else {
                System.out.printf("%.6f already added\n", value);
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

        public void remove(double value) {
            root  = remove(root, value);
        }

        private Node remove(Node root, double value) {
            if (root == null) {
                System.out.printf("%.6f not found to remove\n", value);
                return root;
            } else if (value < root.value) {
                root.left = remove(root.left, value);
            } else if (value > root.value) {
                root.right = remove(root.right, value);
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    Node temp = findMinValueNode(root.right);
                    root.value = temp.value;
                    root.right = remove(root.right, temp.value);
                }
            }

            root.height =  max(height(root.left), height(root.right)) + 1;

            int balance = getBalance(root);

            if (balance > 1 && getBalance(root.left) >= 0) {
                return rightRotate(root);
            } else if (balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            } else if (balance < -1 && getBalance(root.right) <= 0) {
                return leftRotate(root);
            } else if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        public boolean contains(double value) {
            return contains(root, value);
        }

        private boolean contains(Node root, double value) {
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

        private Node findMinValueNode(Node root) {
            Node minValue = root;
            while (minValue.left != null) {
                minValue = minValue.left;
            }
            return minValue;
        }

        public void print() {
            printInOrder(root);
            System.out.println();
        }

        private void printInOrder(Node root) {
            if (root != null) {
                printInOrder(root.left);
                System.out.printf("%.6f ", root.value);
                printInOrder(root.right);
            }
        }
    }
}
