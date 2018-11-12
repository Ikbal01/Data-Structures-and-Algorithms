import java.util.Scanner;

public class Builder {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int buildingCount = scanner.nextInt();
        int[] buildingHeights = new int[buildingCount];
        int[] prices = new int[buildingCount];

        for (int i = 0; i < buildingCount; i++) {
            buildingHeights[i] = scanner.nextInt();
        }

        for (int i = 0; i < buildingCount; i++) {
            prices[i] = scanner.nextInt();
        }

        sort(buildingHeights, prices, 0, buildingCount - 1);


        int result = calculateMinCost(buildingHeights, prices);

        System.out.println(result);

    }

    /**
     * Calculates the minimum cost for equalizing the buildings
     *
     * @param buildingHeights array of building heights
     * @param prices array of prices for changing the heights of buildings
     * @return the cost of buildings equalization
     */
    public static int calculateMinCost(int[] buildingHeights, int[] prices){

        int cost = 0;
        int tallerCount;
        int shorterCount;
        int tallerPrices = 0;
        int shorterPrices = 1;

        while (tallerPrices < shorterPrices) {

            tallerCount = tallestCount(buildingHeights);
            shorterCount = buildingHeights.length - tallerCount;

            tallerPrices = 0;
            shorterPrices = 0;

            for (int i = 0, j = buildingHeights.length - 1; i < tallerCount; i++, j--) {
                tallerPrices += prices[j];
            }

            for (int i = 0; i < buildingHeights.length - tallerCount; i++) {
                shorterPrices += prices[i];
            }

            if (tallerPrices < shorterPrices) {
                for (int i = 0, j = buildingHeights.length - 1; i < tallerCount; i++, j--) {
                    buildingHeights[j]--;
                    cost += prices[j];
                }
            } else {
                cost += equalize(buildingHeights, prices, shorterCount, buildingHeights[buildingHeights.length - 1]);
            }
        }

        return cost;
    }

    /**
     *
     * @param buildingHeights array of building heights.
     * @param prices array of prices for changing the heights of buildings.
     * @param shorterOnesCount
     * @param equalNumber
     * @return
     */
    public static int equalize(int[] buildingHeights, int[] prices, int shorterOnesCount, int equalNumber){
        int cost = 0;

        for (int i = 0; i < shorterOnesCount; i++) {
            while (buildingHeights[i] < equalNumber){
                buildingHeights[i]++;
                cost += prices[i];
            }
        }

        return cost;
    }

    /**
     * Counts the elements equal to the last one(largest).
     *
     * @param buildingHeigths array of building heights.
     * @return the count of elements equal to the last one(largest).
     */
    public static int tallestCount(int[] buildingHeigths){
        int tallest = buildingHeigths[buildingHeigths.length - 1];
        int counter = 0;

        for (int i = buildingHeigths.length - 1; i >= 0 ; i--) {
            if (buildingHeigths[i] == tallest){
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }

    /**
     * Sorts arr1 and arr2 depending on the elements of arr1.
     * The lengths of the two arrays are equal.
     *
     * @param arr1 the array to be sorted
     * @param arr2 the array to be sorted
     * @param left
     * @param right
     */
    private static void sort(int[] arr1, int[] arr2, int left, int right) {

        int i = left, j = right;
        int tmp;

        int pivot = arr1[(left + right) / 2];
        while (i <= j) {
            while (arr1[i] < pivot)
            {
                i++;
            }
            while (arr1[j] > pivot){
                j--;
            }
            if (i <= j) {
                tmp = arr1[i];
                arr1[i] = arr1[j];
                arr1[j] = tmp;

                tmp = arr2[i];
                arr2[i] = arr2[j];
                arr2[j] = tmp;

                i++;
                j--;
            }
        }

        if (left < j)
        {
            sort(arr1, arr2, left, j);
        }
        if (i < right)
        {
            sort(arr1, arr2, i, right);
        }
    }
}
