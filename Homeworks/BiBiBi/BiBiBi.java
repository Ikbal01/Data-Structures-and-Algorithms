import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BiBiBi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int arrLength = scanner.nextInt();
        long[] arr = new long[arrLength];

        for (int i = 0; i < arrLength; i++) {
            arr[i] = scanner.nextLong();
        }
        int requestCount = scanner.nextInt();
        long[] requests = new long[requestCount];

        for (int i = 0; i < requestCount; i++) {
            requests[i] = scanner.nextLong();
        }

        Arrays.sort(arr);

        printIndexes(arr, requests);
    }

    public static void printIndexes(long[] arr, long[] requests){

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < requests.length; i++) {
            int leftIndex = binarySearch(arr, 0, arr.length - 1, requests[i], true);

            if (leftIndex == -1){
                result.append("-1 -1\n");

            } else {
                int rightIndex = binarySearch(arr, 0, arr.length - 1, requests[i], false);

                result.append(leftIndex + 1);
                result.append(" ");
                result.append(rightIndex + 1);
                result.append("\n");
            }
        }

        System.out.println(result.toString());
    }

    // Returns the index of the first or last (depending on isLeftmost) occurrence of x.
    public static int binarySearch(long arr[], int left, int right, long x, boolean isLeftmost)
    {
        if (right >= left)
        {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x){
                int leftSearch = -1;
                int rightSearch = -1;

                if (isLeftmost){
                    leftSearch = binarySearch(arr, left, mid - 1, x, isLeftmost);
                } else {
                    rightSearch = binarySearch(arr, mid + 1, right, x, isLeftmost);
                }
                if (leftSearch != -1 ){
                    return leftSearch;

                } else if (rightSearch != -1) {
                    return rightSearch;
                }

                return mid;
            }

            if (arr[mid] > x)
            {
                return binarySearch(arr, left, mid - 1, x, isLeftmost);
            }

            return binarySearch(arr, mid + 1, right, x, isLeftmost);
        }

        return -1;
    }
}
