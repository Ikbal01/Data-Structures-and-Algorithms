public class SelectionSort {

    public static void selectionSort(int[] arr) {
        int temp;
        int minIndex;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
