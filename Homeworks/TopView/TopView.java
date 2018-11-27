import java.util.*;
import java.io.*;

public class TopView {
    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private static class QueueNode {
        Node node;
        int level;

        QueueNode(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static void topView(Node root) {
        if (root == null) {
            return;
        }

        Queue<QueueNode> queue = new LinkedList<>();
        Map<Integer, Node> viewMap = new TreeMap<>();

        queue.add(new QueueNode(root, 0));

        while (!queue.isEmpty()) {
            QueueNode tmpNode = queue.poll();
            if (!viewMap.containsKey(tmpNode.level)) {
                viewMap.put(tmpNode.level, tmpNode.node);
            }

            if (tmpNode.node.left != null) {
                queue.add(new QueueNode(tmpNode.node.left, tmpNode.level - 1));
            }
            if (tmpNode.node.right != null) {
                queue.add(new QueueNode(tmpNode.node.right, tmpNode.level + 1));
            }

        }
        for (Node node : viewMap.values()) {
            System.out.printf("%d ", node.data);
        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }
}
