public class MergeSort {

    public static void mergeSort(int[] arr) {
        mergeSort(arr, new int[arr.length] , 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }

        int middle = (leftStart + rightEnd) / 2;
        mergeSort(arr, temp, leftStart, middle);
        mergeSort(arr, temp, middle + 1, rightEnd);
        merge(arr, temp, leftStart, rightEnd);
    }

    private static void merge(int[] arr, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;

        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if (arr[left] <= arr[right]) {
                temp[index] = arr[left];
                left++;
            } else {
                temp[index] = arr[right];
                right++;
            }
            index++;
        }

        System.arraycopy(arr, left, temp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, arr, leftStart, size);

//        while (left <= leftEnd) {
//            temp[index] = arr[left];
//            left++;
//            index++;
//        }
//
//        while (right <= rightEnd) {
//            temp[index] = arr[right];
//            right++;
//            index++;
//        }
//
//        int i = leftStart;
//        while (i <= rightEnd) {
//            arr[i] = temp[i];
//            i++;
//        }
    }
}
