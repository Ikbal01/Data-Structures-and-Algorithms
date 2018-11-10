import java.util.Scanner;

public class Snowballs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long totalSum = 0;

        int n = scanner.nextInt();

        Node[] snowmen = new Node[n + 1];
        snowmen[0] = new Node();

        int cloning;
        int mass;

        for(int i = 1; i <= n; i++) {
            cloning = scanner.nextInt();
            mass = scanner.nextInt();

            if (mass != 0) {
                snowmen[i] = new Node(snowmen[cloning]);

                snowmen[i].totalMass = snowmen[cloning].totalMass + mass;
                snowmen[i].topBall = mass;

            } else {
                snowmen[i] = snowmen[cloning].clone;
            }

            totalSum += snowmen[i].totalMass;
        }

        System.out.println(totalSum);

    }

    private static class Node {
        Node clone;
        int totalMass;
        int topBall;

        public Node(Node clone) {
            this.clone = clone;
            totalMass = 0;
            topBall = 0;
        }

        public Node() {
            clone = null;
            totalMass = 0;
            topBall = 0;
        }
    }
}
