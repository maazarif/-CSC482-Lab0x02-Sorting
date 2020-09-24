import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;

public class Lab0x02 {

    /// Generate Lists
    public static String[] GenerateTestsLists(int N, int k, int minV, int maxV) {
        // k is the length of the string. use k+1 cause of null char
        // N is the length of the array
        // minV and maxV are for specific range of byte values
        String[] retVal = new String[N];
        char[] val = new char[k];
        Random r = new Random();

        for(int i = 0; i < N; i++){
            // for each string
            for(int j = 0; j < k; j++){
                val[j] = (char) (r.nextInt(maxV-minV) + minV); // char array
                //System.out.printf("   %c  ", val[j]);
            }
            String temp = new String(val);
            retVal[i] = temp;
        }
        //String

        return retVal;
    }

    // print list
    void printArray(String array[]){
        for(String a : array){
            System.out.printf("%s \n", a);
        }
    }


    /// is sorted
    public static boolean isSorted(String array[]){

        for(int i = 1; i < array.length-1; i++){
            if(stringCompare(array[i-1], array[i]) > 0 ) return false;
        }

        return true;
    }



    /// Simple Sort
        // Selection sort
    void selectionSort(String array[], int N){
        int min = 0;
        String swapTemp;

        // first loop:
        for(int i = 0; i < N-1; i++){
            // selecting a minimum number
            min = i;
            // second loop
            for(int j = i + 1; j < N; j++) {
                // swap value into min index
                if (stringCompare(array[j], array[min]) < 0) {
                    //System.out.printf("\n  comparing %d \n", stringCompare(array[j], array[min]));
                    min = j;
                }
            }

                swapTemp = array[min];
                array[min] = array[i];
                array[i] = swapTemp;

                //System.out.print("\nmin is: " + array[min]);

        }
        //System.out.println("\nSelection Sort\n");
        //printArray(array);
/*
        if (isSorted(array)) {
            System.out.println("YES IT IS SORTED");
        } else {
            System.out.println("NOT SORTED");
        }
*/
    }


    // less than must be negative
    // greater than must be positive
    private static int stringCompare(String s, String s1) {
        int l1 = s.length();
        int l2 = s1.length();
        int lmin = Math.min(l1, l2);

        for(int i = 0; i < lmin; i++){
            int str1_ch = (int) s.charAt(i);
            int str2_ch = (int) s1.charAt(i);

            if(str1_ch != str2_ch){
                return str1_ch - str2_ch;  // -ve is means
            }
        }

        // small ----> big | i-1 is smaller than  (i-1, i) => -ve

        if (l1 != l2){
            return l1 - l2;  // -ve means 2nd string is bigger | +ve means l1 is bigger
        }
        else{
            return 0;
        }

    }

    // insertion sort
    void insertionSort(int array[], int N){
    }


    /// Merge Sort
        // divide the array
            // left / right array
    // Source: https://www.programiz.com/dsa/merge-sort

    void merge(String arr[], int low, int mid, int high){
        /// Left array
        ///// size calculation: low to mid
        int leftSize = mid - low + 1;
        String[] Left = new String[leftSize];
        // initialise left array
        for(int i = 0; i < leftSize; i++){
            Left[i] = arr[low + i];
        }

        /// Right Array
            // size calculation
        int rightSize = high - mid;
        String[] Right = new String[rightSize];
        for(int i = 0; i < rightSize; i++){
            Right[i] = arr[mid + i + 1];    /// add 1 cause mid+1 till high
        }

        int i = 0;    // Left array counter
        int j = 0;    // Right array counter
        int k = low;
        //// sorting and combining the arrays
        while(i < leftSize && j < rightSize){
            if( stringCompare(Left[i], Right[j]) < 0 /* Left[i] <= Right[j]*/ ){  // we want
                arr[k] = Left[i];
                i++;
            } else{
                arr[k] = Right[j];
                j++;
            }
            k++;
            //System.out.println("debugging: k is " + k + "  i is " + i + "   j is " + j);
        }

        while(i < leftSize){
            arr[k] = Left[i];
            i++;
            k++;
        }

        while(j < rightSize){
            arr[k] = Right[j];
            j++;
            k++;
        }
    }

    void MergeSort(String arr[], int l, int h){
        //char left[];
        //char right[];
        // if l == h then no division
        if(l < h){
            int mid = (l+h)/2;
            MergeSort(arr, l , mid);
            MergeSort(arr, mid+1, h);

            merge(arr, l, mid, h);
        }


/*        System.out.println("\nMerge Sort\n");
        printArray(arr);
        if (isSorted(arr)) {
            System.out.println("YES IT IS SORTED");
        } else {
            System.out.println("NOT SORTED");
        }*/

    }


    // quick sort partitions
    int partition(String arr[], int low, int high){
        // we attack from both sides
        String pivot = arr[high];
        // we need to make the each sides of the pivot balanced
        int i = (low - 1); // this will be the smallest value
        for(int j = low; j <= high; j++){
            if( stringCompare(arr[j], pivot) < 0 /*arr[j] < pivot*/ ){ // -ve
                i++;
                // swap the values
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        String temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return (i+1);
    }


    /// Quick sort
    void quickSort(String arr[], int low, int high){
        // find the pivot -- this will be an index
        // 1st recursive call: low will be
        // 2nd recursive call:
        if(low < high){
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot+1, high);
        }

/*
        System.out.println("\nQuick Sort\n");
        printArray(arr);
        if (isSorted(arr)) {
            System.out.println("YES IT IS SORTED");
        } else {
            System.out.println("NOT SORTED");
        }*/


    }

    /// Radix Sort
    // we will follow the creel video implementation
    void radixSort(String arr[], int d, int k){
        // this will take in the length of each string <<< k
        // length of the array << arr
        // d indicates the nimber of b buckets... i will use d = 1, which is 256 buckets

        // 1) we have a init array
        // 2) iterate over each element, check every char in the string element
        // the whole process is repeated k times
        // we will have a count array, the index will be from minV to maxV

        // temp array = arr
        int length = arr.length;
        String[] output = new String [length];
        //System.arraycopy(arr, 0, tempArray, 0, length);
        // declare count array
        int[] count = new int[256];
        int digits = (int) Math.pow(2, (d*8));
        // ABC:
        // FGT:
        // IOP: 232
        // OIU: 237
        // QWE: 237
        // WER: 238
        // ZXC: 285

        for(int i = k-1; i >= 0; i--){
            //System.out.print("a");
            // i will follow lexicographical
            for(int c = 0; c < digits; c++){
                count[c] = 0;
            }

            for(int c = 0; c < length; c++){
                // increment the count array index according to value
                count[(int)(arr[c].charAt(i))] += 1;
                //System.out.printf("INDEX INTO THE COUNT IS %d \n",(int)(arr[c].charAt(i)));
            }

            // find the prefix sum
            // current val + previous values
            for(int j = 1; j < digits; j++){
                count[j] += count[j-1];
                //System.out.printf("Prefix Sum %d: %d \n", j, count[j]);
            }
            //System.out.println("ENTERING");
            // update the temp array, size is length,
            // we go backwards of the temp array
            // we will check the kth index in the last element of temp array
            for(int x = length-1; x >= 0; x--){
                int a = (int)(arr[x].charAt(i));
                // this a is the
                int b = --count[a]; // b-1 will tell us where the value will go in the output array
                //System.out.printf("arr[x] is %s, a is %d and b is %d \n", arr[x], a, b);
                output[b] = arr[x];
            }
            // copy the output array to the arr
            System.arraycopy(output, 0, arr, 0, length);
        }
        // end: arr = temp array
        //System.arraycopy(output, 0, arr, 0, length);
        /*System.out.println("\nSorting with Radix Sort....\n");
        //printArray(arr);
        System.out.print("Verifying ...");
        if (isSorted(arr)) {
            System.out.println(" SORTED!");
        } else {
            System.out.println(" NOT SORTED!");
        }
        */

        //245
    }


    /// LOG2
    public static int log2(int N){
        return (int) (Math.log(N)/Math.log(2));
    }

    public static long getCPUTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }

    public static void Tests(){
        long init, end, prev = 0, curr = 0;
        Lab0x02 lab0x02 = new Lab0x02();

        System.out.print("SELECTION SORT\n");


        System.out.printf("%-10s%10s%10s%16s%26s \n", "N", "k", "Time", "Doubling Time", "Predicted Doubling Ratio");
        for(int n = 10; n < 1000; n*=2){
            for(int k = 4; k < 20; k*=2) {
                String[] s = GenerateTestsLists(n, k, 96, 122);
                prev = curr;
                init = getCPUTime();
                lab0x02.selectionSort(s, n);
                end = getCPUTime();
                curr = end - init;
                if(prev == 0) System.out.printf("%-10d%10d%10d%10.3f%12.3f\n", n, k, curr, 0.000, 0.000);
                else System.out.printf("%-10d%10d%10d%10.3f%12.3f \n", n, k, curr, (float)prev/curr, (float) ( Math.pow(n,2)/Math.pow(n/2.0, 2) ));
            }
        }


        System.out.print("\nQUICK SORT\n");
        prev = 0;
        curr = 0;

        System.out.printf("%-10s%10s%10s%16s%26s \n", "N", "k", "Time", "Doubling Time", "Predicted Doubling Ratio");
        for(int n = 10; n < 1000; n*=2){
            for(int k = 4; k < 20; k*=2) {
                String[] s = GenerateTestsLists(n, k, 96, 122);
                prev = curr;
                init = getCPUTime();
                lab0x02.quickSort(s, 0, n-1);
                end = getCPUTime();
                curr = end - init;

                if(prev == 0) System.out.printf("%-10d%10d%10d%10.3f%16.3f\n", n, k, curr, 0.000, 0.000);
                else System.out.printf("%-10d%10d%10d%10.3f%16.3f\n", n, k, curr, (float)curr/prev,  (float)Math.pow(n,2)/Math.pow(n/2.0, 2) );
            }
        }



        System.out.print("\nMERGE SORT\n");
        prev = 0;
        curr = 0;

        System.out.printf("%-10s%10s%10s%16s%26s \n", "N", "k", "Time", "Doubling Time", "Predicted Doubling Time");
        for(int n = 10; n < 1000; n*=2){
            for(int k = 4; k < 20; k*=2) {
                String[] s = GenerateTestsLists(n, k, 96, 122);
                prev = curr;
                init = getCPUTime();
                lab0x02.MergeSort(s, 0, n-1);
                end = getCPUTime();
                curr = end - init;
                if(prev == 0) System.out.printf("%-10d%10d%10d%10.3f%16.3f\n", n, k, curr, 0.000, 0.000);
                else System.out.printf("%-10d%10d%10d%10.3f%16.3f \n", n, k, curr, (float)curr/prev, (float)(n*log2(n)) / ((n/2)*log2(n/2)) );
            }
        }

        System.out.print("\nRADIX SORT\n");
        prev = 0;
        curr = 0;

        System.out.printf("%-10s%10s%10s%16s%26s \n", "N", "k", "Time", "Doubling Time", "Predicted Doubling Ratio");
        for(int n = 10; n < 1000; n*=2){
            for(int k = 4; k < 20; k*=2) {
                String[] s = GenerateTestsLists(n, k, 96, 122);
                prev = curr;
                init = getCPUTime();
                lab0x02.radixSort(s, 1, k);
                end = getCPUTime();
                curr = end - init;
                if(prev == 0) System.out.printf("%-10d%10d%10d%10.3f%16.3f\n", n, k, curr, 0.000, 0.00);
                else System.out.printf("%-10d%10d%10d%10.3f%16.3f\n", n, k, curr, (float)curr/prev, (float) (n*k/(n/2)*k)  );


            }
        }






    }


    public static void main(String[] args) {

        Lab0x02 lab0x02 = new Lab0x02();
        /*String[] s = {"ABz", "XQE", "lop"};
        //s = ["ABz", "XQE", "lop"];

        char x = s[2].charAt(0);
        System.out.printf(" %d \n", (int)x);
        //System.out.print(s.charAt(0) >> 4);

        */

        //String[] list = GenerateTestsLists(10, 3, 65, 90);
        //lab0x02.printArray(list);

        //System.out.println("\n==================================================\n");

        //String x = 'a'+'b'+'c';


//        String[] test = {"ABC", "ZXC", "OIU", "WER", "FGT", "IOP", "QWE"};


       /// lab0x02.radixSort(list, 1, 3);

        //lab0x02.selectionSort(list, 10);

        //lab0x02.MergeSort(list ,0, 9);

        //lab0x02.quickSort(list, 0, 9);

        Tests();
    }


} // end of class
