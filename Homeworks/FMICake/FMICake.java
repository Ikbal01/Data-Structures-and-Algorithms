import java.util.Scanner;

public class FMICake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        int[] tests = new int[testCount];

        for (int i = 0; i < testCount; i++) {
            tests[i] = scanner.nextInt();
        }

        printMinDays(tests);
    }

    public static void printMinDays(int[] tests){
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tests.length; i++) {
            output.append(minEatDays(tests[i]));
            output.append("\n");
        }
        System.out.println(output.toString());
    }

    public static int minEatDays(int test){
        if (test == 0){
            return 0;
        }

        int currDayBiscuits = (test / 2) + 1;
        int days = 1;

        while (currDayBiscuits != 1){
            days++;
            currDayBiscuits = ((currDayBiscuits - 1) / 2) + 1;
        }
        return days;
    }
}
