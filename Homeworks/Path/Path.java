import java.util.*;

public class Path {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pathSize = Integer.parseInt(scanner.nextLine());
        Stack<Integer> testList = new Stack<>();

        String[] arr = new String[2];
        for (int i = 0; i < pathSize; i++) {
            arr = scanner.nextLine().split(" ");
            String color = arr[0];
            int k = Integer.parseInt(arr[1]);

            switch (color) {
                case "white":
                    testList.add(k);
                    break;

                case "green":
                    int sum = 0;

                    for (int j = 0; j < k && !testList.isEmpty(); j++) {
                        sum += testList.pop();
                    }
                    testList.add(sum);

                    break;
                case "blue":
                    int maxNumber = (int)(- Math.pow(2, 31));

                    for (int j = 0; j < k && !testList.isEmpty(); j++) {
                        int currK = testList.pop();
                        if (maxNumber < currK) {
                            maxNumber = currK;
                        }
                    }
                    testList.add(maxNumber);
                    break;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < testList.size(); i++) {
            stringBuilder.append(testList.get(i));
            stringBuilder.append(" ");
        }

        System.out.println(stringBuilder.toString());
    }
}
