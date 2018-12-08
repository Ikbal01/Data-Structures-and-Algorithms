public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    private class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
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
        if (value <= root.value) {
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
                Node temp = findMinValueNode(root.right);
                root.value = temp.value;
                root.right = delete(root.right, temp.value);
            }
        }
        return root;
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
        return findMinValueNode(root).value;
    }

    private Node findMinValueNode(Node root) {
        Node minValueNode = root;
        while (minValueNode.left != null) {
            minValueNode = minValueNode.left;
        }
        return minValueNode;
    }

    public int findMaxValue() {
        return findMaxValueNode(root).value;
    }

    private Node findMaxValueNode(Node root) {
        Node maxValueNode = root;
        while (maxValueNode.right != null) {
            maxValueNode = maxValueNode.right;
        }
        return maxValueNode;
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

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(Node root) {
        if (root != null) {
            System.out.println(root.value);
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
            System.out.println(root.value);
        }
    }
}
