import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

public class Lab0x03 {


    public static int log2(int N){
        return (int) (Math.log(N)/Math.log(2));
    }


    public static long getCPUTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }



    static int[] GenerateLists(int N, int max, int min){
        int[] array = new int[N];
        //Set<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < N; i++){
            array[i] = (int) ((Math.random() * ((max - min) + 1) ) + min);
            //s.add((int) ((Math.random() * ((max - min) + 1) ) + min));
        }
        return array;
    }

    static void printArray(int arr[]){
        for(int i : arr){
            System.out.printf("%4d", i);
        }
        System.out.printf("\n");
    }


    static int[] ThreeSumBF(int arr[]){
        int N = arr.length;
        int[] retList = new int[3];
        retList[0] = -1; retList[1] = -1; retList[2] = -1;


        for(int i = 0; i < N-3; i++){
            for(int j = i+1; j < N-2; j++){
                for(int k = j+1; j < N-3; j++){
                    int s = arr[i] + arr[j] + arr[k];
                    if(s == 0){
                        retList[0] = i; retList[1] = j; retList[2] = k;
                        return retList;
                    }
                }
            }
        }

        return retList;
    }


    // PARTITION
    static int partition(int arr[], int low, int high){
        // we attack from both sides
        int pivot = arr[high];
        // we need to make the each sides of the pivot balanced
        int i = (low - 1); // this will be the smallest value
        for(int j = low; j <= high; j++){
            if(arr[j] < pivot){ // -ve
                i++;
                // swap the values
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return (i+1);
    }

    //// Quick Sort
    static void quickSort(int arr[], int low, int high){
        if(low < high){
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot+1, high);
        }
    }



    /////MERGE SORT
    public static void merge(int arr[], int low, int mid, int high){
        /// Left array
        ///// size calculation: low to mid
        int leftSize = mid - low + 1;
        int[] Left = new int[leftSize];
        // initialise left array
        for(int i = 0; i < leftSize; i++){
            Left[i] = arr[low + i];
        }

        /// Right Array
        // size calculation
        int rightSize = high - mid;
        int[] Right = new int[rightSize];
        for(int i = 0; i < rightSize; i++){
            Right[i] = arr[mid + i + 1];    /// add 1 cause mid+1 till high
        }

        int i = 0;    // Left array counter
        int j = 0;    // Right array counter
        int k = low;
        //// sorting and combining the arrays
        while(i < leftSize && j < rightSize){
            if( /*stringCompare(Left[i], Right[j]) < 0*/  Left[i] <= Right[j] ){  // we want
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

    public static void MergeSort(int arr[], int l, int h){
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




    //// Binary Search
    public static int BinarySearch(int arr[], int key){
        int upper = arr.length - 1;
        int mid;
        int lower = 0;

        while(lower <= upper) {
            //System.out.println("LOOP");
            //if (upper >= lower) {
            mid = lower + (upper - lower) / 2;
            //System.out.printf(" inside while loop:mid element is %d \n", arr[mid]);
            //System.out.printf("mid  = %d, low = %d, high = %d\n", mid, lower, upper);
            if (arr[mid] < key) lower = mid + 1;
            else if (arr[mid] > key) upper = mid - 1;
            else /*(arr[mid] == key)*/ return mid;

            if(lower > upper) break;
            //}
        }
        return -1;
    }



    // ThreeSum Improved
    static int[] ThreeSumImproved(int[] arr){
        int N = arr.length;
        int sign;
        int[] retList = new int[3];
        retList[0] = -1; retList[1] = -1; retList[2] = -1;
        int x = -1;
        int temp1, temp2, tempSum;

        // sort the array cause we need to have some idea where the values must be
        // find a pair
        // find their sum
        // search the next value according to that

        //System.out.print("Sorting the array\n");
        //quickSort(arr, 0, N-1);
        MergeSort(arr, 0, N-1);
        //System.out.print("Sorted the array\n");
        //printArray(arr);

        for(int i = 0; i < N-3; i++){
            for(int j = 1+1; j < N-2; j++){
                temp1 = arr[i];
                temp2 = arr[j];
                tempSum = temp1+temp2;
                //System.out.print("\npairs are [" + temp1 + ", " + temp2 + "]" + "pair sum is " + tempSum + "\n");
                /// whatever the sum switch the sign
                sign = tempSum*-1;
                // System.out.print("Performing Binary Search \n");

                if( (x =  BinarySearch(arr, sign)) != -1) {
                    retList[0] = temp1; retList[1] = temp2; retList[2] = arr[x];
                    return retList;
                }

                // System.out.print("Done with binary search \n");
            }

            //int s = arr[i] + arr[i+1] + arr[i+2];
            //if(s == 0) return true;
        }
        return retList;
    }


    static int[] ThreeSumFastest(int arr[]){
        int N = arr.length;
        int l, r, curr;
        int sum = 0;
        int[] retVal = new int[3];
        retVal[0] = -1; retVal[1] = -1; retVal[2] = -1;
        // sort the array in ascending: left side will be -ve values and right side will be +ve values
        // if the total sum is negative then increment the left side which will result in a
        // total sum > 0 decrement right side


        //quickSort(arr, 0, N-1);
        MergeSort(arr, 0, N-1);
        //Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            l = 0;
            r = N-1;
            curr = i;
            while(l < r){
                sum = arr[l] + arr[curr] + arr[r];
                if(sum == 0){
                    retVal[0] = arr[l]; retVal[1] = arr[curr]; retVal[2] = arr[r];
                    return retVal;
                }
                // when the sum is positive::
                else if(sum > 0){
                    r--;
                } else{
                    l++;
                }

            }

        }

        return retVal;
    }


    static void found(int[] arr){
        if(arr[0] == -1 && arr[1] == -1 && arr[2] == -1){
            System.out.printf("%-20s", "NO");
        } else{
            System.out.printf("%-20s", "YES");
        }
    }


    static void VerificationTests(int Nmin, int Nmax,int max, int min){
        //long init, init1, end, end1;

        long bfPreTime = 0;
        long bfCurrTime = 0;

        long fastPreTime = 0;
        long fastCurrTime = 0;

        long fastestPreTime;
        long fastestCurrTime = 0;

        int count = 0;

        int[] retVal = new int[3];

        System.out.printf("%-80s%-80s%-80s\n", "Brute Force", "Fast", "Fastest");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                "N", "Time Taken", "FOUND", "DR", "EDR",
                "Time Taken", "FOUND", "DR", "EDR",
                "Time Taken", "FOUND", "DR", "EDR");

        for(int n = Nmin; n < Nmax; n*=2) {
            long init;
            long end;
            int[] test = GenerateLists(n, max, min);
            bfPreTime = bfCurrTime;
            fastPreTime =  fastCurrTime;
            fastestPreTime = fastestCurrTime;


            //printArray(test);

            /////////////// BF
            //System.out.print("\n ThreeSumBF Testing \n");

            init = getCPUTime();
            retVal = ThreeSumBF(test);

            //) {
                //System.out.print("\n IT WAS FOUND \n");
            //} else {
                //System.out.print("\n  Sadly it was not  \n");
            //}

            end = getCPUTime();
            bfCurrTime = end-init;
            System.out.printf("%-20d%-20d", n, bfCurrTime);
            found(retVal);
            if(count == 0){
                System.out.printf("%-20s%-20s", "NA", "NA");
            } else {
                float bf = (float) bfCurrTime / bfPreTime;
                System.out.printf("%-20.3f", bf);
                System.out.printf("%-20.3f", (float) (Math.pow(n,3)/ Math.pow(n/2.0, 3)));
            }


            //System.out.printf("Brute Force Time is %d\n", end - init);

            ///////////// Improved
            //System.out.print("\n ThreeSumImproved Testing \n");
//            int[] test1 = GenerateLists(x, max, min);
            //printArray(test1);

            init = getCPUTime();
            retVal = ThreeSumImproved(test);
              //  System.out.print("\n IT WAS FOUND \n");
            //}
            //else {
                //System.out.print("\n  Sadly it was not  \n");
            //}

            end = getCPUTime();
            fastCurrTime = end - init;
            System.out.printf("%-20d", fastCurrTime);
            found(retVal);
            //System.out.printf("\nImproved time is %d \n", end - init);
            if(count == 0){
                System.out.printf("%-20s%-20s", "NA", "NA");
            } else{
                float fast = (float)  fastCurrTime/fastPreTime;
                System.out.printf("%-20.3f", fast);
                System.out.printf("%-20.3f",  (float) ( (Math.pow(n, 2)*log2(n)) / (Math.pow(n/2.0, 2)*log2(n/2)) )  ); /// EXPECTED: N2logN
            }


            //// FASTEST 3 SUM
            init = getCPUTime();
            retVal = ThreeSumFastest(test);

            //  System.out.print("\n IT WAS FOUND \n");
            //}
            //else {
            //System.out.print("\n  Sadly it was not  \n");
            //}

            end = getCPUTime();
            fastestCurrTime = end - init;
            System.out.printf("%-20d", fastestCurrTime);
            found(retVal);
            //System.out.printf("\nImproved time is %d \n", end - init);
            if(count == 0){
                System.out.printf("%-20s%-20s\n", "NA", "NA");
            } else{
                float fastest = (float)  fastestCurrTime/fastestPreTime;
                System.out.printf("%-20.3f", fastest);
                System.out.printf("%-20.3f\n",  (float) ( (Math.pow(n, 2)) / (Math.pow(n/2.0, 2)) )  ); /// EXPECTED: N2logN
            }


            count++;
        }
    }

    public static void main(String[] args) {
        VerificationTests(4, 10000000, 99999, -99999);
/*
        long init, end;
        int[] test = GenerateLists(100000, 100000, -100000);
        //printArray(test);

        /////////////// BF
        System.out.print("\n ThreeSumBF Testing \n");
        init = getCPUTime();
        if(ThreeSumBF(test)){
            System.out.print("\n IT WAS FOUND \n");
        } else{
            System.out.print("\n  Sadly it was not  \n");
        }
        end = getCPUTime();

        System.out.printf("Brute Force Time is %d\n", end-init);
*/
        ///////////// Improved
/*
        System.out.print("\n ThreeSumImproved Testing \n");
        int[] test1 = GenerateLists(10000, 9999, -9999);
        printArray(test1);

        System.out.print("\nStarting the timer\n");

        int[] arr = new int[3];
        long init = getCPUTime();
        arr = ThreeSumBF(test1);
        //arr = ThreeSumImproved(test1);
        long end = getCPUTime();
        System.out.print("BF: TOTAL TIME === " + (end-init) + "\n");

        long init1 = 0, end1 = 0;

         init1 = getCPUTime();
         arr = ThreeSumImproved(test1);
        //arr = ThreeSumImproved(test1);
         end1 = getCPUTime();
        System.out.print("IMPROVED: TOTAL TIME === " + (end1-init1) + "\n");


        long init2 = 0, end2 = 0; /// 2211586 3092957
        init2 = getCPUTime();
        arr = ThreeSumFastest(test1);
        end2 = getCPUTime();
        System.out.print("Fastest: TOTAL TIME === " + (end2-init2) + "\n");
*/

        /*
        if(ThreeSumImproved(test1) != null){
            System.out.print("\n IT WAS FOUND \n");
        } else{
            System.out.print("\n  Sadly it was not  \n");
        }
        long end = getCPUTime();
        System.out.printf("\nImproved time is %d \n", end - init);
*/


        /*
        int[] lol = GenerateLists(524288, 99999, -99999);
        int[] arr = new int[3];
        long init = getCPUTime();
        //arr = ThreeSumBF(lol);      // 14663907
        //arr = ThreeSumImproved(lol);  // 75666355
        arr = ThreeSumFastest(lol);   //  95547348

        long end = getCPUTime();
        System.out.print("FASTEST: TOTAL TIME === " + (end-init) + "\n");
*/


    }
}
