public class AVL {

    private Node root;

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

    public void delete(int value) {
        root  = delete(root, value);
    }

    private Node delete(Node root, int value) {
        if (root == null) {
            return root;
        } else if (value < root.value) {
            root.left = delete(root.left, value);
        } else if (value > root.value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node temp = findMinValue(root.right);
                root.value = temp.value;
                root.right = delete(root.right, temp.value);
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

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.value);
            printInOrder(root.right);
        }
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

    public int findMinValue() {
        return findMinValue(root).value;
    }

    private Node findMinValue(Node root) {
        Node minValue = root;
        while (minValue.left != null) {
            minValue = minValue.left;
        }
        return minValue;
    }

    public int findMaxValue() {
        return findMaxValue(root).value;
    }

    private Node findMaxValue(Node root) {
        Node maxValue = root;
        while (maxValue.right != null) {
            maxValue = maxValue.right;
        }
        return maxValue;
    }
}
