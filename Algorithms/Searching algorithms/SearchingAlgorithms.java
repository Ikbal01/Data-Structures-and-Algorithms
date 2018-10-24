public class SearchingAlgorithms {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 7, 11, 45};

        System.out.println(jumpSearch(arr, 11));

    }

    public static int binarySearchIterative(int[] sortedArray, int x){
        int left = 0;
        int right = sortedArray.length - 1;

        while (left <= right){
            int middle = (left + right) / 2;

            if (sortedArray[middle] == x){
                return middle;

            } else if (x < sortedArray[middle]){
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    public static int binarySearchRecursive(int[] sortedArray, int x, int left, int right) {
        if (right < left) {
            return -1;
        }

        int middle = (left + right) / 2;

        if (x == sortedArray[middle]) {
            return middle;

        } else if (x < sortedArray[middle]) {
            return binarySearchRecursive(sortedArray, x, left, middle - 1);

        } else {
            return binarySearchRecursive(sortedArray, x, middle + 1, right);
        }
    }

    public static int binarySearchRecursive(int[] sortedArray, int x){
        return binarySearchRecursive(sortedArray, x, 0, sortedArray.length - 1);
    }


    public static int ternarySearch (int[] arr, int x, int left, int right)
    {
        if (left > right){
            return -1;
        }

        int mid1 = left + (right - left) / 3;
        int mid2 = left + 2 * (right - left) / 3;

        if (arr[mid1] == x){
            return mid1;

        } else if (arr[mid2] == x){
            return mid2;

        } else if (x < arr[mid1]){
            return ternarySearch (arr, x, left, mid1 - 1);

        } else if (x > arr[mid2]){
            return ternarySearch (arr, x, mid2 + 1, right);

        } else {
            return ternarySearch (arr, x, mid1 + 1, mid2 - 1);
        }

    }

    public static int jumpSearch(int[] arr, int x)
    {
        int n = arr.length;
        int step = (int)Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n)-1] < x)
        {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n){
                return -1;
            }
        }

        while (arr[prev] < x)
        {
            prev++;

            if (prev == Math.min(step, n)){
                return -1;
            }
        }

        if (arr[prev] == x){
            return prev;
        }

        return -1;
    }
}
