import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ShortestPath {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int t = Integer.parseInt(reader.readLine());

            for (int i = 0; i < t; i++) {
                String[] mass = reader.readLine().split(" ");
                int vertCount = Integer.parseInt(mass[0]);
                int edgeCount = Integer.parseInt(mass[1]);

                Graph graph = new Graph(vertCount);

                for (int j = 0; j < edgeCount; j++) {
                    String[] mass2 = reader.readLine().split(" ");

                    int v1 = Integer.parseInt(mass2[0]);
                    int v2 = Integer.parseInt(mass2[1]);
                    long weight = Long.parseLong(mass2[2]);

                    graph.addEdge(v1, v2, weight);
                }

                int source = Integer.parseInt(reader.readLine());
                if (graph.nodes[source] == null) {
                    graph.nodes[source] = new Node(source);
                }

                long[] distances = shortestDistances(graph, source);
                print(distances, source);
                if (i < t - 1) {
                    System.out.println();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void print(long[] distances, int source) {
        for (int i = 1; i < distances.length; i++) {
            if (i != source) {
                System.out.print(distances[i] + " ");
            }
        }
    }

    public static long[] shortestDistances(Graph graph, int source) {
        long[] distances = new long[graph.size + 1];
        boolean[] setted = new boolean[graph.size + 1];

        for (int i = 0; i <= graph.size; i++) {
            distances[i] = Long.MAX_VALUE;
            setted[i] = false;
        }

        distances[source] = 0;

        while (true) {
            int currVert = getMinDistance(distances, setted);
            if (currVert == -1) {
                break;
            }

            Node currNode = graph.nodes[currVert];
            setted[currVert] = true;

            for (Node node : currNode.adjs.keySet()) {
                long newLength = distances[currVert] + currNode.adjs.get(node);

                if (!setted[node.number] && newLength < distances[node.number]) {
                    distances[node.number] = newLength;
                }
            }
        }
        setUntuchedVertices(distances);
        return distances;
    }

    private static void setUntuchedVertices(long[] distances) {
        long maxValue = Long.MAX_VALUE;

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == maxValue) {
                distances[i] = -1;
            }
        }
    }

    private static int getMinDistance(long[] distances, boolean[] setted) {
        long min = Long.MAX_VALUE;
        int minIndex = -1;

        for (int i = 1; i < distances.length; i++) {
            if (distances[i] < min && !setted[i]) {
                min = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static class Graph {
        private int size;
        private Node[] nodes;

        public Graph(int size) {
            this.size = size;
            nodes = new Node[size + 1];
        }

        public void addEdge(int v1, int v2, long weight) {
            if (nodes[v1] == null) {
                nodes[v1] = new Node(v1);
            }

            if (nodes[v2] == null) {
                nodes[v2] = new Node(v2);
            }
            if (nodes[v1].adjs.containsKey(nodes[v2])) {
                if (nodes[v1].adjs.get(nodes[v2]) > weight) {
                    nodes[v1].adjs.put(nodes[v2], weight);
                    nodes[v2].adjs.put(nodes[v1], weight);

                }
            } else {
                nodes[v1].adjs.put(nodes[v2], weight);
                nodes[v2].adjs.put(nodes[v1], weight);

            }
        }
    }

    private static class Node {
        private int number;
        private HashMap<Node, Long> adjs;

        public Node(int number) {
            this.number = number;
            this.adjs = new HashMap<>();
        }
    }
}
