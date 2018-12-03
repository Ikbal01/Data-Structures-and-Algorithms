import java.util.ArrayList;
import java.util.Scanner;

public class BrandNew {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodeCount = scanner.nextInt();
        int maxSuccessiveCafes = scanner.nextInt();
        int[] isThereCafe = new int[nodeCount + 1];
        for (int i = 1; i < nodeCount + 1; i++) {
            isThereCafe[i] = scanner.nextInt();
        }
        Tree tree = new Tree(nodeCount);
        int nodeOne;
        int nodeTwo;

        for (int i = 0; i < nodeCount - 1; i++) {
            nodeOne = scanner.nextInt();
            nodeTwo = scanner.nextInt();
            tree.addNode(nodeOne, nodeTwo, isThereCafe[nodeOne], isThereCafe[nodeTwo]);
        }

        System.out.print(dfs(tree.nodes[1], maxSuccessiveCafes, maxSuccessiveCafes));
    }

    private static int dfs(Node curr, int maxSuccessiveCafes, int tempMaxSucc) {
        curr.visited = true;
        if (curr.ai == 1) {
            tempMaxSucc -= 1;
        } else {
            tempMaxSucc = maxSuccessiveCafes;
        }
        int sum = 0;
        if (curr.children.size() == 1 && curr.children.get(0).visited && tempMaxSucc >= 0) {
            return 1;
        } else if (tempMaxSucc >= 0){
            for (Node node : curr.children) {
                if (!node.visited) {
                    sum += dfs(node, maxSuccessiveCafes, tempMaxSucc);
                }
            }
        }
        return sum;
    }

    private static class Tree {
        private Node[] nodes;
        private int size;

        public Tree(int n) {
            nodes = new Node[n + 1];
            size = n + 1;
        }

        public void addNode(int nodeOne, int nodeTwo, int aiOne, int aiTwo) {
            if (nodes[nodeOne] == null) {
                nodes[nodeOne] = new Node(aiOne, nodeOne);
            }

            if (nodes[nodeTwo] == null) {
                nodes[nodeTwo] = new Node(aiTwo, nodeTwo);
            }
            nodes[nodeOne].add(nodes[nodeTwo]);
            nodes[nodeTwo].add(nodes[nodeOne]);
        }
    }

    private static class Node {
        private ArrayList<Node> children;
        private boolean visited = false;
        private int ai;
        private int number;
        public Node(int ai, int number) {
            children = new ArrayList<>();
            this.ai = ai;
            this.number = number;
        }

        public void add(Node node) {
            children.add(node);
        }
    }
}
