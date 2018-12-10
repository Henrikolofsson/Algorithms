import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class QuickSort {

    public void quickSort(int[] arr, int lo, int hi) {
        shuffle(arr, lo, hi);
       // quickSort(arr, lo, hi);

        if(hi <= lo){
            return;
        } else {
            int pivot = arr[0];
            int p = partition(arr, lo, hi, pivot);
            quickSort(arr, lo, p-1);
            quickSort(arr, p+1, hi);
        }
    }

    //The method that moves all the values smaller then the pivot to the left
    //And all values bigger then the pivot to the right. Then returning the pivot index.
    private int partition(int[] arr, int lo, int hi, int pivot){
        swap(arr, lo, pivot);
        int border = lo + 1;
        for(int i = border; i <= hi; i++){
            if(arr[i] < arr[lo]){
                swap(arr, i, border);
            }
        }
        swap(arr, lo, border-1);
        return border-1;
    }

    //@Author Jesper Larsson
    //A method used to shuffle an array.
    public static void shuffle(int[] a, int lo, int hi) {
        Random rand = new Random();

        for (int i = lo; i <= hi; ++i) {
            int r = i + rand.nextInt(hi + 1 - i);
            int t = a[i];
            a[i] = a[r];
            a[r] = t;
        }
    }

    //A method used to clean up code, used for swapping elements in an array.
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        QuickSort quicksort = new QuickSort();
        int[] data = readFile("files/smallints");
      //  int[] data2 = readFile("files/largeints");
        int N = 40;

        quicksort.quickSort(data, 0, N);

        for (int i = 0; i <= N; i++) {
            System.out.print(data[i] + " ");
        }

       for(int i = 0; i <= N; i++){
            isSorted(data,i, N);
        }

    }
}
