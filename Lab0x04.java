import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Lab0x04 {
    public static long[] table;

    public static int log2(int N){
        return (int) (Math.log(N)/Math.log(2));
    }

    public static long getCPUTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }

    /// normal fibonacci
    static long fibRecur(int X){
        if(X == 0 || X == 1) return X;
        else return (fibRecur(X-1) + fibRecur(X-2));
    }

   /*
    static void tableInit(int X){
        table = new int[X+1];
        for(int i = 0; i <= X; i++){
            if (i < 2) table[i] = i;
            else{
                table[i] = table[i-1] + table[i-2];
            }
        }
    }
    */
    static void tableInit(int X){
        table = new long[ X+1];
        for(int i = 0; i <= X; i++){
            table[i] = 0;
        }
    }

    /// cache fibonacci
    static long fibCache(int X){
        tableInit(X);
        //if(X == 0 || X == 1) return X;
        //else{
            return fibCacheHelper(X);
        //}
    }

    static long fibCacheHelper(int X){
        if(X == 0 || X == 1) return X;
        else if(table[X] != 0){
            return table[X];
        } else{
            table[X] = fibCacheHelper(X-1) + fibCacheHelper(X-2);
            return table[X];
        }
    }

    /// loop fibonacci
    static long fibLoop(int X){
        long A = 0;
        long B = 1;
        long next;
        if(X < 2) return X;
        for(int i = 2; i <= X; i++){
            next = A + B;
            A = B;
            B = next;
        }

        return B;
    }

    static long exponent(int X, int index){
        long total = X;
        for(int i = 0; i < index; i++){
            total *= X;
        }
        return total;

    }

    static long toPower(int X, int p){
        //int bin[] = new int[40];
        int index = 0;
        long total = X;
        while(p > 0){
            /*bin[index++] = */
            //System.out.print("Total = " + total + "\n");
            if((p % 2) == 1){
                total *= exponent(X, index);
            }
            p = p / 2;
            index++;
        }
/*        for(int i = 0; i < index; i++){
            System.out.print(bin[i]);
            if(bin[i] == 1) total *= (X^i);
        }
        System.out.println();*/
        return total;
    }

    /// Matrix fibonacci
    static long fibMatrix(int X){
        long[][] M = {{1, 1}, {1, 0}};
        long[][] temp = {{1, 1}, {1, 0}};
        long[][] next = new long[2][2];

        //System.out.printf("M is  {{%d, %d}, {%d, %d}} \n",
         //       M[0][0], M[1][0], M[0][1], M[1][1]);

        if(X == 0 || X == 1) return X;

        for(int i = 1; i <= (X-2); i++){
            // get each element of row
            //System.out.printf("temp[0][0] = %d, temp[1][0] = %d, temp[0][1] = %d and temp[1][1] = %d \n",
            //                               temp[0][0], temp[1][0], temp[0][1], next[1][1]);
            // temp will change by row
            // M will change by its column
/*
            next[0][0] = (temp[0][0]*M[0][0]) + (temp[0][1]*M[1][0]);  /// first row first col  2*1 + 1*1 = 3

            next[1][0] = (temp[0][0]*M[0][1]) + (temp[0][1]*M[1][1]);  /// first row second col 2*1 + 1*0 = 2
            System.out.printf("next[1][0]: %d + %d = %d\n", (temp[0][0]*M[0][1]), (temp[0][1]*M[1][1]), next[1][0]);

            next[0][1] = (temp[1][0]*M[0][0]) + (temp[1][1]*M[1][0]);  /// second row first col
            next[1][1] = (temp[1][0]*M[0][1]) + (temp[1][1]*M[1][1]);  /// second row second col

            temp = next;
*/
            for(int a = 0; a < 2; a++){
                for(int b = 0; b < 2; b++){
                    next[a][b] = 0;
                    for(int c = 0; c < 2; c++){
                        next[a][b] += (temp[a][c] * M[c][b]);
                    }
                    //System.out.printf("next[%d][%d] = %d \n", a, b, next[a][b]);
                }
            }

            // System.arraycopy(next[r], 0, temp[r], 0, 2);
            for(int r = 0; r < 2; r++){
                System.arraycopy(next[r], 0, temp[r], 0, 2);
/*                for(int col = 0; col < 2; col++){
                    temp[r][col] = next[r][col];
                }*/
            }
            //System.arraycopy();

            //temp = next;

        }

        return temp[0][0];
    }


    /**
    * Code from: https://nayuki.io/res/fast-fibonacci-algorithm/FastFibonacci.java
    * */
    static long fibMatrixFast(int X){
        long[] matrix = {1, 1, 1, 0};      ///// using 1d array instead of 2d
        if(X < 0){
            System.out.print("ERROR: Value must not be less than 0\n");
            return -1;
        }
        return matrixPower(matrix, X)[1];
    }

    static long[] matrixPower(long[] matrix, int X){
        long[] result = {1, 0, 0, 1};
        while(X != 0){
            if(X%2 != 0){
                result = matrixMultiply(result, matrix);
            }
            X /= 2;
            matrix = matrixMultiply(matrix, matrix);
        }
        return result;
    }

    /** 0, 1, 2, 3
     *    A             B
     * 0    1        0     1
     * 2    3        2     3
     *
     * */

    static long[] matrixMultiply(long[] A, long[] B){
        return new long[]{
                A[0]*B[0] + A[1]*B[2],     /// row1 col1
                A[0]*B[1] + A[1]*B[3],     /// row1 col2
                A[2]*B[0] + A[3]*B[2],     /// row2 col1
                A[2]*B[1] + A[3]*B[3]      /// row2 col2
        };
    }



    static void Tests(int X){
        int N;
        int count = 0;

        long recurPreTime = 0;
        long recurCurrTime = 0;

        long cachePreTime = 0;
        long cacheCurrTime = 0;

        long loopPreTime = 0;
        long loopCurrTime = 0;

        long matrixPreTime = 0;
        long matrixCurrTime = 0;


        // print headings
        System.out.printf("%30s%60s%60s%60s\n", "FibRecur", "FibCache", "FibLoop", "FibMatrix");
        System.out.printf("%s%10s%20s%20s%20s%20s%20s%20s%20s%20s%20s%20s%20s%20s\n",
                        "X", "N", "Time", "DR", "EDR", "Time", "DR", "EDR", "Time", "DR", "EDR", "Time", "DR", "EDR"
        );
        for(int x = 1; x <= X; x++){
            recurPreTime = recurCurrTime;
            cachePreTime = cacheCurrTime;
            loopPreTime = loopCurrTime;
            matrixPreTime = matrixCurrTime;

            N = (int) Math.ceil(log2(x+1));

            System.out.printf("%d%10d", x, N);

            long init = getCPUTime();
            fibRecur(x);
            long end = getCPUTime();
            recurCurrTime = (end-init);
            System.out.printf("%20d", recurCurrTime);

            //// ratios
            ////// exponential growth 2^(x/2)
            if(count == 0 || (x%2!=0) ) {
                System.out.printf("%20d%20d", 0, 0);
            }
            else{
                System.out.printf("%20.3f%20.3f", (float)recurCurrTime/recurPreTime, (float)toPower(2, (x))/toPower(2, (x/2)) );
            }


            long init1 = getCPUTime();
            fibCache(x);
            long end1 = getCPUTime();
            cacheCurrTime = end1-init1;
            System.out.printf("%20d", cacheCurrTime);

            /// linear time X
            if(count == 0 || (x%2!=0) ) {
                System.out.printf("%20d%20d", 0, 0);
            }
            else{
                System.out.printf("%20.3f%20.3f", (float)cacheCurrTime/cachePreTime, (float)x/x/2);
            }

            //System.out.print("POwer === " + toPower(7, 6));
            long init2 = getCPUTime();
            fibLoop(x);
            long end2 = getCPUTime();
            loopCurrTime = end2 - init2;
            System.out.printf("%20d", loopCurrTime);

            /// LInear time X
            if(count == 0 || (x%2!=0) ) {
                System.out.printf("%20d%20d", 0, 0);
            }
            else{
                System.out.printf("%20.3f%20.3f", (float)loopCurrTime/loopPreTime, (float)x/x/2);
            }


            long init3 = getCPUTime();
            fibMatrix(x);
            long end3 = getCPUTime();
            matrixCurrTime = end3 - init3;
            System.out.printf("%20d",  matrixCurrTime );


            /// T(X)~log2(X)
            if(count == 0 || (x%2!=0) ) {
                System.out.printf("%20d%20d", 0, 0);
            }
            else{
                System.out.printf("%20.3f%20.3f", (float)matrixCurrTime/matrixPreTime, (float)log2(x)/log2(x/2));
            }


            System.out.println();
            count++;
        }


    }


    public static void main(String[] args) {
        Tests(12);
        //System.out.print( "very fast boiii:  " + fibMatrixFast(100) + "\n");
/*

        long init = getCPUTime();
        System.out.print("FibRecur === " + fibRecur(50) + "     ");
        long end = getCPUTime();
        System.out.print("   Time for fibrecur:   " +  (end-init) + "\n" );


        long init1 = getCPUTime();
        System.out.print("FibCache === " + fibCache(50));
        long end1 = getCPUTime();
        System.out.print("    Time for fibcache:   " +  (end1-init1) + "\n"  );

        //System.out.print("POwer === " + toPower(7, 6));
        long init2 = getCPUTime();
        System.out.print("FibLoop === " + fibLoop(50));
        long end2 = getCPUTime();
        System.out.print("    Time for fibLoop:   " +  (end2-init2) + "\n"  );


        long init3 = getCPUTime();
        System.out.print("FibMatrix === " + fibMatrix(50));
        long end3 = getCPUTime();
        System.out.print("    Time for fibmatrix:   " +  (end3-init3) + "\n"  );

        System.out.print("Matrix = " + fibMatrix(50) + "\n");
*/

    }
}
