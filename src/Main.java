import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] intsForBubbleSort = generateRandomArray();
        int[] intsForSelectionSort = Arrays.copyOf(intsForBubbleSort, intsForBubbleSort.length);
        int[] intsForInsertionSort = Arrays.copyOf(intsForBubbleSort, intsForBubbleSort.length);
//        System.out.println(Arrays.toString(ints));

        long start = System.currentTimeMillis();
        sortBubble(intsForBubbleSort);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        sortSelection(intsForSelectionSort);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        sortInsertion(intsForInsertionSort);
        System.out.println(System.currentTimeMillis() - start);

//        System.out.println(Arrays.toString(ints));

    }

    public static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000);
        }
        return arr;
    }

    //Bubble sort
    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}