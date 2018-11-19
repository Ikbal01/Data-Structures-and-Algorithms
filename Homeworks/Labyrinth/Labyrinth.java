import java.util.LinkedList;
import java.util.Scanner;

public class Labyrinth {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] contraints = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(contraints[0]);
        int columns = Integer.parseInt(contraints[1]);
        int request = Integer.parseInt(contraints[2]);
        String[][] maze = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            maze[i] = scanner.nextLine().split(" ");
        }

        Node source = new Node(0, 0);
        Node destination;

        StringBuilder stringBuilder = new StringBuilder();
        String[] line;
        int x;
        int y;
        for (int i = 0; i < request; i++) {
            line = scanner.nextLine().split(" ");
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);
            stringBuilder.append(findShortestPath(source, new Node(x, y), maze));
            if (i < request - 1) {

                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public static int findShortestPath(Node source, Node destination, String[][] matrix) {
        int counter = 0;
        LinkedList<Node> nextToVisit = new LinkedList<>();

        if (matrix[destination.x][destination.y].equals("1")) {
            return -1;
        }

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int pass = 0;
        int counterTwo = 0;

        nextToVisit.add(source);
        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();
            if (node.x == destination.x && node.y == destination.y) {
                return counter;
            }

            int size = nextToVisit.size();

            if (node.x + 1 < matrix.length && matrix[node.x + 1][node.y].equals("0")) {
                if (!visited[node.x + 1][node.y]) {
                    nextToVisit.add(new Node(node.x + 1, node.y));
                    visited[node.x + 1][node.y] = true;
                }
            }
            if (node.x - 1 >= 0 && matrix[node.x - 1][node.y].equals("0")) {
                if (!visited[node.x - 1][node.y]) {
                    nextToVisit.add(new Node(node.x - 1, node.y));
                    visited[node.x - 1][node.y] = true;
                }
            }
            if (node.y + 1 < matrix[0].length && matrix[node.x][node.y + 1].equals("0")) {
                if (!visited[node.x][node.y + 1]) {
                    nextToVisit.add(new Node(node.x, node.y + 1));
                    visited[node.x][node.y + 1] = true;
                }
            }
            if (node.y - 1 >= 0 && matrix[node.x][node.y - 1].equals("0")) {
                if (!visited[node.x][node.y - 1]) {
                    nextToVisit.add(new Node(node.x, node.y - 1));
                    visited[node.x][node.y - 1] = true;
                }
            }

            counterTwo += (nextToVisit.size() - size);

            if (pass == 0) {
                counter++;
                pass = counterTwo;
                counterTwo = 0;
            }

            pass--;
        }
        return -1;
    }

    private static class Node {
        private int x;
        private int y;
        private Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
