//import MyBigIntegers.java;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Lab0x05 {

    /// loop fibonacci big
    static MyBigIntegers fibLoopBig(MyBigIntegers X){
        /// some constants
        MyBigIntegers valOne = new MyBigIntegers("1");
        MyBigIntegers valTwo = new MyBigIntegers("2");


        MyBigIntegers A = new MyBigIntegers("0");
        MyBigIntegers B = new MyBigIntegers("1");
        MyBigIntegers next = new MyBigIntegers();
        // if X.greaterThan
        if(!X.greaterThan(valTwo)) {
            return X;
        }

        // i.value
        for(MyBigIntegers i = new MyBigIntegers("2"); !(i.greaterThan(X)) /*<= X*/; i=i.sum(valOne)){
            next = A.sum(B);// + B;
            A = B;
            B = next;
            //System.out.printf("%s + %s = %s\n", A.Value(), B.Value(), next.Value());
            //System.out.printf("%s + %s = %s", A.Value(), B.Value(), next.Value());
        }

        return B;
    }


    /// Matrix fibonacci big
    static MyBigIntegers fibMatrixBig(MyBigIntegers X){
        // big int constant
        final MyBigIntegers valZero = new MyBigIntegers("0");
        final MyBigIntegers valOne = new MyBigIntegers("1");
        final MyBigIntegers valTwo = new MyBigIntegers("2");



        final MyBigIntegers[][] M = {{valOne, valOne}, {valOne, valZero}};
        MyBigIntegers[][] temp = {{valOne, valOne}, {valOne, valZero}};
        MyBigIntegers[][] next = new MyBigIntegers[2][2];

        System.out.print("M = " +  M[0][0].Value().length() + "\n");

        MyBigIntegers tempNext = new MyBigIntegers();

        //System.out.printf("M is  {{%d, %d}, {%d, %d}} \n",
        //       M[0][0], M[1][0], M[0][1], M[1][1]);

        //if(X == 0 || X == 1) return X;

        if(!X.greaterThan(valTwo)) {
            return X;
        }
        if(X.equalTo(valTwo)) return valTwo;

        MyBigIntegers subConst = new MyBigIntegers();
        subConst = X.minus(valOne);

        System.out.print("SubConst = " + subConst.Value() + "\n");

        if(  (valTwo.greaterThan(X)) ) System.out.print("So its trueeee \n");



        /// for(MyBigIntegers i = new MyBigIntegers("2"); !(i.greaterThan(X)); i=i.sum(valOne))
        for(MyBigIntegers i = new MyBigIntegers("3"); !(i.greaterThan(X)); i=i.sum(valOne)){
            //for(MyBigIntegers a = new MyBigIntegers("0"); !(a.greaterThan(valTwo)); a=a.sum(valOne)){
                //for(MyBigIntegers b = new MyBigIntegers("0"); !(b.greaterThan(valTwo)); b=b.sum(valOne)){
            if(i.greaterThan(X)){
                System.out.print("\nSTOP\n");
            } else{
                System.out.printf("\nContinue: %s\n", i.Value());
            }
            for(int a = 0; a < 2; a++){
                System.out.print("\n First Loop \n");
                for(int b = 0; b < 2; b++){
                    System.out.print("\n  Second Loop  \n");
                    next[a][b] = valZero;
                    for(int c = 0; c < 2; c++){
                        System.out.print("POP | M = " +  M[0][0].Value().length() + "    i  is = " + i.Value() + "\n");
                        System.out.print("\n HERE temp = " + temp[a][c].Value() + "   M = " + M[c][b].Value() + "  next = " + next[a][b].Value() + "\n");
                        tempNext = (temp[a][c]).karatsubaMultiply(M[c][b]);//times(M[c][b]);
                        next[a][b] = (next[a][b]).sum(tempNext);
                        //next[a][b] += //(temp[a][c]).times(M[c][b]);
                    }

                    System.out.printf("next[%d][%d] = %s \n", a, b, (next[a][b]).Value() );
                }
            }

            // System.arraycopy(next[r], 0, temp[r], 0, 2);
            for(int r = 0; r < 2; r++){
                System.out.print("copying loop: " + next[r][0].Value() + " " + next[r][1].Value() + "\n");
                System.arraycopy(next[r], 0, temp[r], 0, 2);
/*                for(int col = 0; col < 2; col++){
                    temp[r][col] = next[r][col];
                }*/
            }

            //System.arraycopy();
            //temp = next;

        }

        System.out.printf("\ntemp[0][0] = %s, temp[0][1] = %s, temp[1][0] = %s, temp[1][1] = %s \n", temp[0][0].Value(), temp[0][1].Value(), temp[1][0].Value(), temp[1][1].Value());

        return temp[0][0];
    }


    /**
    ///// built in Big int implementation for matrix
    static BigInteger bfibMatrix(BigInteger X){
        // big int constant

        final BigInteger valZero = new BigInteger("0");
        final BigInteger valOne = new BigInteger("1");
        final BigInteger valTwo = new BigInteger("2");

        final BigInteger[][] M = {{valOne, valOne}, {valOne, valZero}};
        BigInteger[][] temp = {{valOne, valOne}, {valOne, valZero}};
        BigInteger[][] next = new BigInteger[2][2];

        //System.out.print("M = " +  M[0][0].toString().length() + "\n");

        BigInteger tempNext; = new BigInteger();

        //System.out.printf("M is  {{%d, %d}, {%d, %d}} \n",
        //       M[0][0], M[1][0], M[0][1], M[1][1]);

        //if(X == 0 || X == 1) return X;

        if(X.compareTo(valTwo) < 0 ) {
            return X;
        }
        //if(X.compareTo(valTwo) <= 0 )
        if(X.compareTo(valTwo) == 0 ) return valTwo;


        BigInteger subConst = X.subtract(valOne);

        //System.out.print("SubConst = " + subConst.toString() + "\n");

        //if( valTwo.compareTo(X) > 0  /*(valTwo.greaterThan(X))) System.out.print("So its trueeee \n");



        /// for(MyBigIntegers i = new MyBigIntegers("2"); !(i.greaterThan(X)); i=i.sum(valOne))
        for(BigInteger i = new BigInteger("3"); i.compareTo(X) <= 0 !(i.greaterThan(X)); i=i.add(valOne)){
            //for(MyBigIntegers a = new MyBigIntegers("0"); !(a.greaterThan(valTwo)); a=a.sum(valOne)){
            //for(MyBigIntegers b = new MyBigIntegers("0"); !(b.greaterThan(valTwo)); b=b.sum(valOne)){

            if( i.compareTo(X) > 0  /*i.greaterThan(X)  ){
                System.out.print("\nSTOP\n");
            } else{
                System.out.printf("\nContinue: %s\n", i.toString());
            }

            for(int a = 0; a < 2; a++){
                //System.out.print("\n First Loop \n");
                for(int b = 0; b < 2; b++){
                    //System.out.print("\n  Second Loop  \n");
                    next[a][b] = valZero;
                    for(int c = 0; c < 2; c++){
                        //System.out.print("POP | M = " +  M[0][0].toString().length() + "    i  is = " + i.toString() + "\n");
                        //System.out.print("\n HERE " + temp[a][c].toString() + " * " + M[c][b].toString() + "\n");// + "   M = " + M[c][b].toString() + "  next = " + next[a][b].toString() + "\n");
                        tempNext = (temp[a][c]).multiply(M[c][b]);
                        next[a][b] = (next[a][b]).add(tempNext);
                        //next[a][b] += //(temp[a][c]).times(M[c][b]);
                    }

                    //System.out.printf("next[%d][%d] = %s \n", a, b, (next[a][b]).toString() );
                }
            }

            // System.arraycopy(next[r], 0, temp[r], 0, 2);
            for(int r = 0; r < 2; r++){
                //System.out.print("copying loop: " + next[r][0].toString() + " " + next[r][1].toString() + "\n");
                System.arraycopy(next[r], 0, temp[r], 0, 2);
/*                for(int col = 0; col < 2; col++){
                    temp[r][col] = next[r][col];
                }
            }

            //System.arraycopy();
            //temp = next;

        }

        //System.out.printf("\ntemp[0][0] = %s, temp[0][1] = %s, temp[1][0] = %s, temp[1][1] = %s \n", temp[0][0].toString(), temp[0][1].toString(), temp[1][0].toString(), temp[1][1].toString());

        return temp[0][0];
    }
*/

    static BigInteger bfibMatrix(int X){
        BigInteger[] matrix = {BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO};      ///// using 1d array instead of 2d
        if(X < 0){
            System.out.print("ERROR: Value must not be less than 0\n");
            return BigInteger.ZERO;
        }
        return matrixPower(matrix, X)[1];

    }


    static BigInteger[] matrixPower(BigInteger[] matrix, int X){
        BigInteger[] result = {BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE};
        while(X != 0){
            if(X%2 != 0){
                result = matrixMultiply(result, matrix);
            }
            X /= 2;
            matrix = matrixMultiply(matrix, matrix);
        }
        return result;
    }

    static BigInteger[] matrixMultiply(BigInteger[] A, BigInteger[] B){
        return new BigInteger[]{
                A[0].multiply(B[0]).add(A[1].multiply(B[2])),     /// row1 col1
                A[0].multiply(B[1]).add(A[1].multiply(B[3])),     /// row1 col2
                A[2].multiply(B[0]).add(A[3].multiply(B[2])),     /// row2 col1
                A[2].multiply(B[1]).add(A[3].multiply(B[3]))      /// row2 col2
        };
    }


    /// fib formula
    static long fibConstant(int X){
        double goldenMean   = (1+Math.sqrt(5))/2;
        double goldenNumber = (1-Math.sqrt(5))/2;

        double retVal = (Math.pow(goldenMean, X) - Math.pow(goldenNumber, X))/Math.sqrt(5);

        return (long)retVal;
    }

    /// fib formula big   <<<<< uses BUILT-IN CLASS
    static BigDecimal fibConstantBig(BigInteger X){
        ///
        BigDecimal valOne = new BigDecimal("1");
        BigDecimal valTwo = new BigDecimal("2");
        BigDecimal valFive = new BigDecimal("5");
        MathContext mathContext = new MathContext(10);

        BigDecimal goldenMean =   (valOne.add(valFive.sqrt(mathContext))).divide(valTwo); // 1 + sqrt(5)  / 2
        BigDecimal goldenNumber =   (valOne.subtract( valFive.sqrt(mathContext))).divide(valTwo);

        BigDecimal retVal = (goldenMean.pow(X.intValue()).subtract(goldenNumber.pow(X.intValue()))).divide(valFive.sqrt(mathContext), 0, RoundingMode.UP);


        return retVal;
    }


    /// generate lists
    static String GenerateString(int N){
        StringBuilder retVal = new StringBuilder();
        //Set<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < N; i++){
            retVal.append( (char) ((Math.random() * ((9) + 1)) + 48));
            //s.add((int) ((Math.random() * ((max - min) + 1) ) + min));
        }
        return retVal.toString();
    }


    public static long getCPUTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }

    public static double log2(double N){
        return  (Math.log(N)/Math.log(2));
    }

    static String bigDecAbbreviated(BigDecimal x){
        if(x.toString().length() > 12){
            StringBuilder retString = new StringBuilder();
            for(int i = 0; i < 5; i++){
                retString.append(x.toString().charAt(i));
            }

            retString.append("...");

            for(int i = x.toString().length()-5; i < x.toString().length(); i++){
                retString.append(x.toString().charAt(i));
            }

            return retString.toString();
        }

        return x.toString();
    }


    static String bigIntAbbreviated(BigInteger x){
        if(x.toString().length() > 12){
            StringBuilder retString = new StringBuilder();
            for(int i = 0; i < 5; i++){
                retString.append(x.toString().charAt(i));
            }

            retString.append("...");

            for(int i = x.toString().length()-5; i < x.toString().length(); i++){
                retString.append(x.toString().charAt(i));
            }

            return retString.toString();
        }

        return x.toString();
    }



    static void VerificationTests(){
        MyBigIntegers test = new MyBigIntegers("5065");
        BigInteger X = new BigInteger("5065");


        System.out.print("\nFib Loop\n");
        System.out.printf("%-20s%-20s\n", "X", "Answer");
        for(int x = 217; x < 237; x++){
            test = new MyBigIntegers(String.valueOf(x));
            System.out.printf("%-20s%-20s\n",  test.Value(), fibLoopBig(test).AbbreviatedValue());
        }



        System.out.print("\nFib Matrix\n");
        System.out.printf("%-20s%-20s\n", "X", "Answer");
        for(int i = 77; i < 100; i++){
           // X = new BigInteger(String.valueOf(i)/*GenerateString(N)*/);
            System.out.printf("%-20s%-20s\n", X.toString(), bfibMatrix(i).toString());
        }


        System.out.print("\nFib Constant\n");
        System.out.printf("%-20s%-20s\n", "X", "Answer");
        for(int i = 63; i < 80; i++){
            //X = new BigInteger(String.valueOf(i)/*GenerateString(N)*/);
            System.out.printf("%-20d%-20d\n", i, fibConstant(i));
        }


        System.out.print("\nBig Fib Constant\n");
        System.out.printf("%-20s%-20s\n", "X", "Answer");
        for(int i = 63; i < 80; i++){
            X = new BigInteger(String.valueOf(i)/*GenerateString(N)*/);
            System.out.printf("%-20s%-20s\n", X.toString(), fibConstantBig(X).toString());
        }



    }


    static void resultTable(){
        MyBigIntegers A = new MyBigIntegers();
        BigInteger B;// = new BigInteger();

        MyBigIntegers resultLoop = new MyBigIntegers();
        int incr = 1;
        int preIncr = 0;
        int N;

        long fLoopCurr;
        long fLoopPre = 0;
        long fLoopStart;
        long fLoopEnd;

        float fLoopE1R = 0;   // Expected +1 Ratio

        long fMatrixCurr;
        long fMatrixPre = 0;
        long fMatrixStart;
        long fMatrixEnd;

        float fMatrixE1R = 0;
        BigInteger resultMatrix;

        long fConstCurr;
        long fConstPre = 0;
        long fConstStart;
        long fConstEnd;

        float fConstE1R = 0;
        BigDecimal resultConst;

        int count = 2;

        System.out.print("\nBigFibLoop\n");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "N(length of input)", "X(input)", "Answer", "Time",
                "Actual 10x Ratio", "Expected 10x Ratio", "Expected +1 Ratio");

        for(int X = 1; X <= 100000; X+=incr){
            incr = (int) Math.pow(10, (int)Math.log10(X));
            N = (int)Math.log10(incr)+1;

            A = new MyBigIntegers(String.valueOf(X));
            fLoopStart = getCPUTime();
            resultLoop = fibLoopBig(A);
            fLoopEnd = getCPUTime();
            fLoopCurr = fLoopEnd - fLoopStart;
            System.out.printf("%-20d%-20d%-20s%-20.3f", N/*(int)Math.log10(incr)+1*/, X, resultLoop.AbbreviatedValue(),
                    (float)(fLoopCurr));
            if(count <= 11){
                System.out.printf("%-20s%-20s%-20s\n",  "-", "-", "-");
            }
            else{
                //System.out.print("\nCurr = " + fLoopCurr + "  Pre = " + fLoopPre + "    " + fLoopCurr/fLoopPre + "\n");
                float fLoopBR = (float)(fLoopCurr/fLoopPre);
                float fLoopEBR = (float)(Math.pow(  Math.log10(X), 2) / ( Math.pow((  Math.log10(X/10.0) ), 2))); // T~X^2

                System.out.printf("%-20.3f%-20.3f", fLoopBR, fLoopEBR);
            }

            //float fLoopE1R = 0;
            if(preIncr != incr && count > 11){
                fLoopE1R = (float)(Math.pow( (Math.log10(incr)+1), 2) /( Math.pow( (Math.log10(preIncr)+1) , 2)));
                //System.out.print("\n incr =  " + incr + "  preIncr = " + preIncr  + "\n");
                System.out.printf("%-20.3f\n", fLoopE1R);
            }
            else if(count > 11) System.out.printf("%-20.3f\n", fLoopE1R);

            /// 10x is exactly like 2x ratio but we divide by 10
            ///
            /// 1+ ratio exactly the same but it will the same for the same digits.

            fLoopPre = fLoopCurr;
            preIncr = incr;
            count++;
        }

        /**   Matrix   */
        count = 2;
        preIncr = 0;
        incr = 1;
        System.out.print("\nBigFibMatrix\n");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "N", "X", "Answer", "Time",
                "10x Ratio", "Expected 10x Ratio", "Expected +1 Ratio");

        for(int X = 1; X <= 100000; X+=incr){
            incr = (int) Math.pow(10, (int)Math.log10(X));
            N = (int)Math.log10(incr)+1;

            //B = new BigInteger(String.valueOf(X));
            fMatrixStart = getCPUTime();
            resultMatrix = bfibMatrix(X);
            fMatrixEnd = getCPUTime();
            fMatrixCurr = fMatrixEnd - fMatrixStart;
            System.out.printf("%-20d%-20d%-20s%-20.3f", N/*(int)Math.log10(incr)+1*/, X, bigIntAbbreviated(resultMatrix),
                    (float)(fMatrixCurr));
            if(count <= 11){
                System.out.printf("%-20s%-20s%-20s\n",  "-", "-", "-");
            }
            else{
                //System.out.print("\nCurr = " + fLoopCurr + "  Pre = " + fLoopPre + "    " + fLoopCurr/fLoopPre + "\n");
                float fMatrixBR = (float)(fMatrixCurr/fMatrixPre);
                System.out.printf("%-20.3f", fMatrixBR);


                float error = (((float) log2(Math.log10(X/10.0)+1 )));
                if(error == 0){
                    System.out.printf("%-20s", "-");
                } else{
                    float fMatrixEBR = (float)(  ((log2(N/*(int)Math.log10(X)+1*/ ) ) ) / error ); // T~log_2(log_10(X))
                    System.out.printf("%-20.3f", fMatrixEBR);
                }
            }

            //float fLoopE1R = 0;
            if(preIncr != incr && count > 11){
                fMatrixE1R = (float)(Math.pow( (Math.log10(incr))+1, 2) /( Math.pow( (Math.log10(preIncr))+1 , 2)));
                //System.out.print("\n incr =  " + incr + "  preIncr = " + preIncr  + "\n");
                System.out.printf("%-20.3f\n", fMatrixE1R);
            }
            else if(count > 11) System.out.printf("%-20.3f\n", fMatrixE1R);

            /// 10x is exactly like 2x ratio but we divide by 10
            ///
            /// 1+ ratio exactly the same but it will the same for the same digits.

            fMatrixPre = fMatrixCurr;
            preIncr = incr;
            count++;
        }




        /**  Constant  */
        incr = 1;
        preIncr = 0;
        count = 2;
        System.out.print("\nBigFibConstant\n");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "N", "X", "Answer", "Time",
                "10x Ratio", "Expected 10x Ratio", "Expected +1 Ratio");
        for(int X = 1; X <= 100000; X+=incr){
            incr = (int) Math.pow(10, (int)Math.log10(X));
            N = (int)Math.log10(incr)+1;

            B = new BigInteger(String.valueOf(X));
            fConstStart = getCPUTime();
            resultConst = fibConstantBig(B);
            fConstEnd = getCPUTime();
            fConstCurr = fConstEnd - fConstStart;
            System.out.printf("%-20d%-20d%-20s%-20.3f", N/*(int)Math.log10(incr)+1*/, X, bigDecAbbreviated(resultConst),
                    (float)(fConstCurr));
            if(count <= 11){
                System.out.printf("%-20s%-20s%-20s\n",  "-", "-", "-");
            }
            else{
                //System.out.print("\nCurr = " + fLoopCurr + "  Pre = " + fLoopPre + "    " + fLoopCurr/fLoopPre + "\n");
                float fConstBR = (float)(fConstCurr/fConstPre);
                System.out.printf("%-20.3f", fConstBR);


                float error = (((float) Math.log10(X/10.0)+1 ));
                if(error == 0){
                    System.out.printf("%-20s", "-");
                } else{
                    float fConstEBR = (float)( N /error ); // T~log_2(log_10(X))
                    System.out.printf("%-20.3f", fConstEBR);
                }
            }

            //float fLoopE1R = 0;
            /// 1+ rATIO
            if(preIncr != incr && count > 11){
                fConstE1R = (float)(N);
                //System.out.print("\n incr =  " + incr + "  preIncr = " + preIncr  + "\n");
                System.out.printf("%-20.3f\n", fConstE1R);
            }
            else if(count > 11) System.out.printf("%-20.3f\n", fConstE1R);

            /// 10x is exactly like 2x ratio but we divide by 10
            ///
            /// 1+ ratio exactly the same but it will the same for the same digits.

            fConstPre = fConstCurr;
            preIncr = incr;
            count++;
        }




    }


    public static void main(String[] args) {

        MyBigIntegers test = new MyBigIntegers("12");
        BigInteger X = new BigInteger("12");
        //MyBigIntegers test1 = new MyBigIntegers("");

        //System.out.print("output = " + fibLoopBig(test).Value() + "\n");

//        System.out.print("matrix = " + fibMatrixBig(test).Value() + "\n");

        //System.out.print("built in = " + bfibMatrix(X).toString() + "\n");

        //if(test.equalTo(test1)) System.out.print("\b its EQQQQQQQQQQQQQQQQQUALL \n");
        //System.out.print("output = " + fibLoopBig(test).Value() + "\n");

//        System.out.print("\nfibformula = " + fibFormula(10) + "\n");
        resultTable();
        //System.out.print("5065 = " + bfibMatrix(X).toString() + "\n");
        //System.out.print("Loop 5065 = " + fibLoopBig(test).Value() + "\n");





    }
}
