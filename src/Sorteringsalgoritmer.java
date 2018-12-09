import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.util.Arrays.sort;

public class Sorteringsalgoritmer {

    //Mergesort är en rekursiv metod. Den använder sig utav en divide & conquer approach.
    //Den är väldigt effektiva för större datamängder.

    public void mergeSort(int[] array, int low, int high){
        if(high <= low){
            return;
        } else {
            int mid = (int)Math.floor((high + low) / 2);
            mergeSort(array, low, mid);
            mergeSort(array, mid+1, high);
            merge(array, low, mid, high);

        }
    }

    public void merge(int[] list, int lowIndex, int midIndex, int highIndex) {

        int[] leftpartArray = new int[midIndex - lowIndex + 2];
        for (int i = lowIndex; i <= midIndex; i++) {
            leftpartArray[i - lowIndex] = list[i];
        }
        leftpartArray[midIndex - lowIndex + 1] = Integer.MAX_VALUE;

        int[] rightpartArray = new int[highIndex - midIndex + 1];
        for (int i = midIndex + 1; i <= highIndex; i++) {
            rightpartArray[i - midIndex - 1] = list[i];
        }
        rightpartArray[highIndex - midIndex] = Integer.MAX_VALUE;

        int i = 0, j = 0, k = lowIndex;
        while(k <= highIndex){
            if (leftpartArray[i] <= rightpartArray[j]) {
                list[k] = leftpartArray[i];
                i++;
            }
            else {
                list[k] = rightpartArray[j];
                j++;
            }
            k++;
        }
    }

        public static int[] readFile(String filename) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filename));
        int[] ints = new int[bytes.length/4];
        for(int i = 0; i < ints.length; i++){
            for(int j = 0; j < 4; j++){
                ints[i] += (bytes[i*4+j] & 255) << (3-j)*8;
            }
        }
        return ints;
    }


    private static boolean isSorted(int[] a, int lo, int hi) {
        int flaws = 0;
        for (int i = lo+1; i <= hi; i++) {
            if (a[i] < a[i-1]) {
                if (flaws++ >= 10) {
                    System.out.println("...");
                    break;
                }
                System.out.println("a["+(i-1)+"] = "+a[i-1]+", a["+i+"] = "+a[i]);
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

        for(int i = 0; i <= N; i++){
            System.out.print(data[i] + " ");
        }

        for(int i = 0; i <= N; i++){
            isSorted(data,i, N);
        }

    }
}
