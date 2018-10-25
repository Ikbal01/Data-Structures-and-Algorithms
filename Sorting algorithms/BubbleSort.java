public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int temp;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSortOptimized(int[] arr) {
        boolean sorted;
        int temp;

        for (int i = 1; i < arr.length; i++) {
            sorted = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    sorted = false;
                }
            }

            if (sorted){
                break;
            }
        }
    }
}
