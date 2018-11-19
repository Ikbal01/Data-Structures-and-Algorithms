import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MagicNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        LinkedList<Long> nums = new LinkedList<Long>();

        for (int i = 1; i <= 9; i++) {
            nums.add((long)i);
        }

        long lastNum = nums.remove();
        int lastDigit;
        long newNum;

        StringBuilder stringBuilder = new StringBuilder();

        while (lastNum <= n) {
            stringBuilder.append(lastNum);
            stringBuilder.append(" ");
            lastDigit = (int)(lastNum % 10);
            if (lastDigit - 2 >= 0) {
                newNum = (lastNum * 10) + lastDigit - 2;
                nums.add(newNum);
            }

            if (lastDigit + 2 < 10) {
                newNum = (lastNum * 10) + lastDigit + 2;
                nums.add(newNum);
            }
            lastNum = nums.remove();
        }

        System.out.println(stringBuilder.toString());
    }
}
