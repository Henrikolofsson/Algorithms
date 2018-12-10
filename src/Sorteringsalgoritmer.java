import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sorteringsalgoritmer {

    //Mergesort is an recursive method. It uses a divide and conquer approach.
    //It is very effective for bigger sets of data.

    public void mergeSort(int[] array, int low, int high) {
        //Checks if high is smaller or the same as low. This would mean that the array has only one value.
        //Which in turn means that the array is already sorted.
        if (high <= low) {
            return;
        } else {
            //Calculating what the mid index is, rounded off downwards due to the int value needed.
            int mid = (int) Math.floor((high + low) / 2);
            //Sorting left part from low to mid
            mergeSort(array, low, mid);
            //Sorting right part from mid+1 to high
            mergeSort(array, mid + 1, high);
            //Compares each of the values in both of the lists and merges them.
            merge(array, low, mid, high);

        }
    }

    public void merge(int[] arr, int lo, int mid, int hi) {
        //Create a copy of the original array
        int[] copy = new int[arr.length];
        //Place all elements in the copy
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        //Initialize the starting index value for i(starting position for the left part)
        //Initialize the starting index value for j(starting position for the right part)
        int i = lo, j = mid + 1;
        //Set k start position at the lowest index, and let it go through until it has been through the whole array.
        for (int k = lo; k <= hi; k++) {
            //Check if i > mid, if  that is the case then the left part is finished and we should take values from the right part (which equals J:s value)
            if (i > mid) {
                arr[k] = copy[j];
                j++;
            }
            //Check if j > hi, if that is the case then the right part is finished and we should take values from the left part(which equals I:s value)
            else if (j > hi) {
                arr[k] = copy[i];
                i++;
            }
            //Check if the value in the left part is smaller then the value from the right part.
            // If it is smaller the value is taken from the left part and placed in the original array.
            else if (copy[i] <= copy[j]) {
                arr[k] = copy[i];
                i++;
            }
            //Check if the value in the right part is smaller then the value in the left part.
            //If it is smaller the value is taken from the right part and placed in the original array.
            else if (copy[j] <= copy[i]) {
                arr[k] = copy[j];
                j++;
            }
        }
    }

    //@Author Jesper Larsson
    //Reads a file and returns an array of integers.
    public static int[] readFile(String filename) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filename));
        int[] ints = new int[bytes.length / 4];
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < 4; j++) {
                ints[i] += (bytes[i * 4 + j] & 255) << (3 - j) * 8;
            }
        }
        return ints;
    }

    //@Author Jesper Larsson
    //Checks if an array of integers is sorted.
    private static boolean isSorted(int[] a, int lo, int hi) {
        int flaws = 0;
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i] < a[i - 1]) {
                if (flaws++ >= 10) {
                    System.out.println("...");
                    break;
                }
                System.out.println("a[" + (i - 1) + "] = " + a[i - 1] + ", a[" + i + "] = " + a[i]);
            }
        }
        return flaws == 0;
    }


    public static void main(String[] args) throws IOException {
        Sorteringsalgoritmer algoritmer = new Sorteringsalgoritmer();
        int[] data = readFile("files/smallints");
        int[] data2 = readFile("files/largeints");
        int N = 30;

        algoritmer.mergeSort(data, 0, N);

        for (int i = 0; i <= N; i++) {
            System.out.print(data[i] + " ");
        }

          for(int i = 0; i <= N; i++){
                isSorted(data,i, N);
          }

    }
}
