public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    private class Node {
        private Node left;
        private Node right;
        private int data;
        public Node(int data) {
            this.data = data;
        }
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insert(root, value);
        }
    }

    private void insert(Node root, int value) {
        if (value <= root.data) {
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

    public void delete(int value) {
        root  = delete(root, value);
    }

    private Node delete(Node root, int value) {
        if (root == null) {
            return root;
        } else if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
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
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }
        return root;
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node root, int value) {
        if (value == root.data) {
            return true;
        } else if (value < root.data) {
            if (root.left == null) {
                return false;
            } else {
                return contains(root.left, value);
            }
        } else {
            if (root.right == null) {
                return false;
            } else {
                return contains(root.right, value);
            }
        }
    }

    public int findMinValue() {
        return findMinValue(root).data;
    }

    private Node findMinValue(Node root) {
        Node minValue = root;
        while (minValue.left != null) {
            minValue = minValue.left;
        }
        return minValue;
    }

    public int findMaxValue() {
        return findMaxValue(root).data;
    }

    private Node findMaxValue(Node root) {
        Node maxValue = root;
        while (maxValue.right != null) {
            maxValue = maxValue.right;
        }
        return maxValue;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.data);
            printInOrder(root.right);
        }
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node root) {
        if (root != null) {
            System.out.println(root.data);
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(Node root) {
        if (root != null) {
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.println(root.data);
        }
    }
}

