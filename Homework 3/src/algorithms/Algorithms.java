package algorithms;

public class Algorithms {
    public static void bubbleSort(int[] arr) {

        // My version of bubble sort.

        boolean swapped = false;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;

                swapped = true;
            }
            if (swapped && i == arr.length - 2) {
                i = 0;
                swapped = false;
            }
        }

        // The real version of bubble sort.

//        boolean isSorted;
//
//        do {
//            isSorted = true;
//
//            for (int i = 0; i < arr.length - 1; i++) {
//                if (arr[i] > arr[i + 1]) {
//                    int temp = arr[i];
//                    arr[i] = arr[i + 1];
//                    arr[i + 1] = temp;
//
//                    isSorted = false;
//                }
//            }
//        } while (!isSorted);
    }
}
