import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.util.Arrays;


/**
 * Public APIs:
 * times() : it has conditions in it, the negatives and positives, then it calls the multiply function
 *           to do the computation
 * minus() : calls subtract()
 * sum()   : calls addition()
 *
 */



public class MyBigIntegers {
    /// an array of int

    // properties
    //private int[] value;      // int array
    //private int sign;       // -1 or +1


    /// generate lists
    static String GenerateString(int N){
        StringBuilder retVal = new StringBuilder();
        //Set<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < N; i++){
            retVal.append( (char) ((Math.random() * ((9 - 0) + 1)) + 48));
            //s.add((int) ((Math.random() * ((max - min) + 1) ) + min));
        }
        return retVal.toString();
    }
    
    private String value;
    // default constructor
    MyBigIntegers(){
        //MyBigIntegers myBigIntegers = new MyBigIntegers();
        value = new String("");
        //myBigIntegers.value = new int[0];
        //myBigIntegers.sign = 0;
    }

    // parameterized constructor: this contructor takes in a decimal value
    MyBigIntegers(String decVal){
        //MyBigIntegers myBigIntegers = new MyBigIntegers();
        this.value = decVal;
        //if(decVal.charAt(0) == '-') myBigIntegers.sign = -1;
        //else myBigIntegers.sign = +1;

        //int size = decVal.length() - 1;
        //myBigIntegers.value = new int[size];

    }

    String padding(int length, String string){
        //System.out.print("padding = " + length + "\n");
        char[] temp = new char[length+string.length()];
        int x = 0;

        if(string.charAt(0) == '-') {
            temp[0] = '-';
            x = 1;
        }
        for(int i = x; i < length+x; i++){
            temp[i] = '0';
        }

        int count = length+x;
        for(int i = x; i < string.length(); i++){
            temp[count] = string.charAt(i);

            count++;
        }

        //System.arraycopy(temp, length+1, string.toCharArray(), 0, string.length()-1);

        String ret = String.valueOf(temp);

        return ret;
    }

    // convert ascii numbers to int num
    int convert(char val){
        return (val - 48);
    }

    int findGiverIndex(int currIndex){
        for(int i = currIndex; i >= 0; i--){
            if(this.value.charAt(i) != '0') return i;
        }

        return currIndex;
    }

    // geeksforgeek
    String insertString(String originalString, char[] stringtobeInserted, int from, int to){
        // convert originalString to char array
        System.out.print("\n originalString is = " + originalString + "\n");
        System.out.print("\n stringtobeinserted = " + String.valueOf(stringtobeInserted) + "\n");
        // stringtobeinserted will always be a char array
        // then starting from the index write into the originalString
        // convert the char array to string and return it

        //char[] newString = new char[from-to];
        char[] originalArray = originalString.toCharArray();
        //char[] insertedArray = stringtobeInserted.toCharArray();


        for(int i = from; i < to; i++){
            originalArray[i] = stringtobeInserted[i];
        }
        //System.arraycopy();
        //System.arraycopy(stringtobeInserted, from, originalArray, from, to - from);
        System.out.print("\ninsertString:   " + String.valueOf(originalArray) +  "\n");

        return String.valueOf(originalArray);
    }


    /** PUblic APIs*/
    public MyBigIntegers sum(MyBigIntegers myBigIntegers){


        // both are +ve
        if((this.value.charAt(0) != '-') && (myBigIntegers.value.charAt(0) != '-')){
            return addition(myBigIntegers);
        }
        // this is +ve but
        else if((this.value.charAt(0) != '-') && (myBigIntegers.value.charAt(0) == '-')){
            myBigIntegers.value = myBigIntegers.value.substring(1);
            /// if bigint > this => negative    this + -bigint => this - bigint
            if(!greaterThan(myBigIntegers)){
                String s = "";
                s += "-";
                s += myBigIntegers.subtraction(this).value;
                MyBigIntegers ret = new MyBigIntegers();
                ret.value = s;
                return ret;
            }
            return subtraction(myBigIntegers);
        }
        // this is -ve
        else if((this.value.charAt(0) == '-') && (myBigIntegers.value.charAt(0) != '-')){
            this.value = this.value.substring(1);
            /// this > bigint => -this + bigint =>
            if(!myBigIntegers.greaterThan(this)){
                String s = "";
                s += "-";
                s +=  myBigIntegers.subtraction(this).value;
                MyBigIntegers ret = new MyBigIntegers();
                ret.value = s;
                return ret;
            }
            return myBigIntegers.subtraction(this);
        }
        else{
            // both are negative
            this.value = this.value.substring(1);
            myBigIntegers.value = myBigIntegers.value.substring(1);
            MyBigIntegers ret = new MyBigIntegers();
            ret.value += "-";
            ret.value += addition(myBigIntegers);
            return ret;
        }

    }

    public MyBigIntegers minus(MyBigIntegers myBigIntegers){
        // both are +ve
        if((this.value.charAt(0) != '-') && (myBigIntegers.value.charAt(0) != '-')){
            System.out.print("\n all positives\n");
            // this < mybiginteger => do -(mybigint - this)
            if(!greaterThan(myBigIntegers)){
                String s = "";
                s += "-";
                s += myBigIntegers.subtraction(this).value;
                myBigIntegers.value = s;
                return myBigIntegers;
            }

            return subtraction(myBigIntegers);
        }
        // this is +ve but
        // this -- bigint = this +
        else if((this.value.charAt(0) != '-') && (myBigIntegers.value.charAt(0) == '-')){
            myBigIntegers.value = myBigIntegers.value.substring(1);
            // some adjustment is required
            if(!greaterThan(myBigIntegers)){
                // this.value is smaller than mybiginteger.value
                System.out.print("\nthis must be negative\n");
            }

            return addition(myBigIntegers);
        }
        // this is -ve
        // -this - bigint =>
        else if((this.value.charAt(0) == '-') && (myBigIntegers.value.charAt(0) != '-')){
            this.value = this.value.substring(1); // x
            MyBigIntegers ret = new MyBigIntegers();
            ret.value += "-";
            System.out.print("\n About to add:: ret.val = " +this.value + " &&   " + myBigIntegers.value + "\n");
            ret.value += addition(myBigIntegers).value; // -(x+y)
            System.out.print("\n About to return:: ret.val = " + ret.value + "\n");
            return ret;
            //this.value = this.value.substring(1);
            //return myBigIntegers.subtraction(this);
        }
        else{
            // both are negative -15 - -5 = -15 + 5 => 5 - 15 => -10
            this.value = this.value.substring(1);
            myBigIntegers.value = myBigIntegers.value.substring(1);
            /// if bigint < this => -(this-bigint)
            if(!myBigIntegers.greaterThan(this)){
                String s = "";
                s += "-";
                s += subtraction(myBigIntegers).value;
                MyBigIntegers ret = new MyBigIntegers();
                ret.value = s;
                return ret;
            }
            return myBigIntegers.subtraction(this);
        }

    }

    public MyBigIntegers times(MyBigIntegers myBigIntegers){
        MyBigIntegers ret = new MyBigIntegers();
        boolean neg = false;

        // both are positive
        if((this.value.charAt(0) != '-') && (myBigIntegers.value.charAt(0) != '-')){
            //System.out.print("\nthis: both are positive\n");
            return this.multiplication(myBigIntegers);
        }
        // this.val i positive but x.val is not
        else if((this.value.charAt(0) != '-') && (myBigIntegers.value.charAt(0) == '-')){
            // answer will be negative
            neg = true;
            myBigIntegers.value = myBigIntegers.value.substring(1);
        }
        // x.val is positive but this.val is not
        else if((this.value.charAt(0) == '-') && (myBigIntegers.value.charAt(0) != '-')){
            neg = true;
            this.value = this.value.substring(1);
            //myBigIntegers.value = myBigIntegers.value.substring(1);
            //return ("-" + multiplication(myBigIntegers));
        }
        // both are negative
        else{
            this.value = this.value.substring(1);
            myBigIntegers.value = myBigIntegers.value.substring(1);
            return multiplication(myBigIntegers);
        }


       // if(neg){
            ret.value += "-";
            ret.value += multiplication(myBigIntegers).value;
            return ret;
        //}


    }


    /** FASTER IMPLEMENTATION  **/


    /** PRIVATE METHODS :Logic Part **/

    // greater than
    public boolean greaterThan(MyBigIntegers x){
        if(this.value.length() > x.value.length()){
            return true;
        }

        if(this.value.length() < x.value.length()){
            return false;
        }


        for(int i = 0; i < this.value.length(); i++){
            if(this.value.charAt(i) > x.value.charAt(i))
                return true;
            if(this.value.charAt(i) < x.value.charAt(i))
                return false;
        }

        return false;
    }

    // greater than equal to
    public boolean greaterThanEqualTo(MyBigIntegers x){
        if(this.value.length() >= x.value.length()){
            return true;
        }

        if(this.value.length() < x.value.length()){
            return false;
        }


        for(int i = 0; i < this.value.length(); i++){
            if(this.value.charAt(i) >= x.value.charAt(i))
                return true;
            if(this.value.charAt(i) < x.value.charAt(i))
                return false;
        }

        return false;
    }

    public boolean equalTo(MyBigIntegers x){
        int count = 0;
        if(this.value.length() == x.value.length()) {
            for (int i = 0; i < this.value.length(); i++) {
                //equal = false;
                if(this.value.charAt(i) == x.value.charAt(i)) count++;
            }
        }

        if(count == this.value.length()) return true;

        return false;
    }

    String Value(){
        return value;
    }

    String AbbreviatedValue(){
        if(this.value.length() < 12){
            return this.Value();
        }

        String retString = "";
        for(int i = 0; i < 5; i++){
            retString += this.value.charAt(i);
        }

        retString += "...";

        for(int i = this.value.length()-5; i < this.value.length(); i++){
            retString += this.value.charAt(i);
        }

        return retString;
    }

    void assign(MyBigIntegers x){
        this.value = x.value;
    }


    // Sum
    private MyBigIntegers addition(MyBigIntegers x){
        MyBigIntegers retval = new MyBigIntegers();
        String first = x.value;
        //System.out.print(value + "\n" + first);

        /// padding correction
        if(value.length() > x.value.length()){
            int pad = value.length() - x.value.length();
            first = x.value = padding(pad, x.value);
        } else{
            int pad = x.value.length() - value.length();
            value = padding(pad, value);
        }
        //System.out.print("\n" + value + "\n" + first);



        int start = value.length()-1;
        int f = 0;
        int second = 0;
        int third = 0;
        int carry = 0;
        int ans = 0;
        char[] carray = new char[value.length()];
        String sumStep = "";
        char[] bigCharArray = new char[value.length()+1];
        char temp;

        for(int i = start; i >= 0; i--){
            //System.out.print("\n" + value.charAt(i) + " + " + x.value.charAt(i) + "\n");
            f = convert(value.charAt(i));
            second = convert(x.value.charAt(i));
            third = f + second + carry;
            if(third >= 10){
                //System.out.print("\n carried \n");
                carry = 1;
                ans = third-10;
            } else{
                carry = 0;
                ans = third;
            }
          //  System.out.print("At " + i + " carry is " + carry + "\n");
            temp = (char) (ans+48);
            /*if(carry == 1 && (f == 9 || second == 9)){
                bigCharArray = Arrays.copyOfRange(carray, 1, start);
                bigCharArray[0] = '1';
            }*/

            //carray[i] = temp;
            sumStep = temp + sumStep;
            //System.out.print(temp);
            //System.out.print("\n" + f + " + " + second + " = " + temp + "   lol " + ans + "\n");
        }

        // adjusting when the
        //System.out.print("XOXXOXOXOXX::: " + carray[0] + carray[1]  + "\n");
        //System.out.print("\nthe last ans is = " + ans + "\n");
        //System.out.print("\nbefore the condition " + carry + "  " + ans + "\n");
        if(carry == 1 && (f == 9 || second == 9)){
            //System.out.print("outside of the loop: "  + "\n");
           // System.out.print("length is = " + carray.length + "\n");
            //int q = (carry + convert(value.charAt(0)) + convert(x.value.charAt(0)));
            //bigCharArray[0] = '1';
            char firstPos = '1';
            //ans -=10;
            //bigCharArray[1] = (char) (ans+48);
            char secondPos =  (char) (ans+48);
            //System.arraycopy(carray, 1, bigCharArray, 2, start);
            //System.out.print("  \n  sumStep is = " + sumStep + " && sumstep.substring " + sumStep.substring(1,start+1) + "\n");
            sumStep = "1" + secondPos + sumStep.substring(1,start+1);
            //retval.value = sumStep;//String.valueOf(bigCharArray);
        }
        else if(carry == 1){
            //System.out.print("\ninside the condition " + carry + "  " + sumStep + "\n");
            char carryPos = (char) (carry+48);
            sumStep = carryPos + sumStep;
        }



/*        String s = "";
        sumStep = sumStep.replaceFirst("^0*", "");
        if(sumStep.isEmpty()) sumStep = "";*/

/*
        /// remove 0s on the left
        String s = "";
        int i = sumStep.length()-1;
        while(i >= 1 && sumStep.charAt(i) == '0') i--;
        //if(i == -1) return "";
        System.out.print("i is " + i + "\n");
        while(i >= 0){
            s += sumStep.charAt(i--);
        }
        System.out.print(" output array in string is equal to  " + s + "  \n");
*/

        //else {
            retval.value = sumStep;//String.valueOf(carray);
        //}

//        System.out.print("\nretVal is:  " + retval.value + "\n");



        return retval;
    }

    // Minus
    private MyBigIntegers subtraction(MyBigIntegers x){
        MyBigIntegers retval = new MyBigIntegers();
        String sfirst = x.value;
        boolean greaterThan = true;
        System.out.print(value + "\n" + sfirst);


        // if this.value is smaller x.value then append '-' into the output array
        if(!greaterThan(x)){
            System.out.print("\n substraction(): IT IS NOT GRATER THAN\n");
            greaterThan = false;
        } else{
            System.out.print("\n subtraction(): IT IS  GRATER THAN\n");
            greaterThan = true;
        }


        /// padding correction
        if(value.length() > x.value.length()){
            int pad = value.length() - x.value.length();
            sfirst = x.value = padding(pad, x.value);
        } else{
            int pad = x.value.length() - value.length();
            value = padding(pad, value);
        }
        System.out.print("\n" + value + "\n" + sfirst);

        // start from the right
        //       this.value
        // -
        //        x.value



        int start = value.length() - 1;
        int first;
        int second;
        int third;
        int extra;
        int giversIndex;
        int carry = 0;

        int tempCount = 0;

        System.out.print("\n value of start is = " + start + "\n");
        char[] carray = new char[value.length()+1];
        char[] tempArray = new char[value.length()+1];
        char[] bigCharArray = new char[value.length()+1];

        for(int i = start; i >= 0; i--){
            /// convert values
            first = convert(this.value.charAt(i));
            second = convert(x.value.charAt(i));
            if(second > first){
                System.out.print("\nsecond > first \n");
                // find the givers index
                giversIndex = findGiverIndex(i);
                System.out.print("givers index is " + giversIndex + "\n");
                // update the array accordingly
                    // the array is updated
                tempCount = 0;
                for(int j = giversIndex; j < i; j++){
                    extra = convert(this.value.charAt(j)) + 10;
                    extra -= 1;
                    System.out.print("\nj is " + j + " extra is : " + extra + "\n");
                    tempArray[tempCount] = (char) (extra+48);
                    tempCount++;
                    //this.value.toCharArray();
                }
                first += 10;
                third = first - second;
                System.out.print("\nAbnormal: third is  = " + third + "\n");
                // put this tempArray into this.value
                this.value = insertString(this.value, tempArray, giversIndex, i);

            } else{
                third = first - second;
                System.out.print("\nnormal: third is :  "  + third + "\n");
            }

            //System.out.print("writing into it\n");
            carray[i] = (char) (third+48);
            //System.out.print("\n end of error \n");
        }


        retval.value = String.valueOf(carray);
        System.out.print("\n retval ||| " + retval.value + "\n");


        /*
        if(!greaterThan){
            String s = "";
            s += '-';
            s += retval.value;
            retval.value = s;
        }
        System.out.print("\n Final: retval:  " + retval.value + "\n");
        */

        return retval;
    }

    /// mulitplication
    private MyBigIntegers multiplication(MyBigIntegers x){
        MyBigIntegers retVal = new MyBigIntegers();
        // convert array into char array
        char[] topOperand = this.value.toCharArray();
        char[] bottomOperand = x.value.toCharArray();
        // create a output char array

        // how many times the loop will iterate
        int loopControl = (this.value.length() + 1) * x.value.length();

        //int[] outputArray = new int[this.value.length()+x.value.length()];
        int[] outputArray = new int[this.value.length()+x.value.length()];


        int carry = 0;
        int b;
        int t;
        int bottomVal;
        int topVal;
        int ans;
        int fIndex = 0;//x.value.length()-1;
        int sIndex = 0;//this.value.length()-1;
        //for(int i = 0; i < loopControl; i++){
        //}


       // System.out.print("x length = " + x.value.length() + " this length = " + this.value.length() + "\n");

        for( b = x.value.length()-1; b >= 0; b--){
            carry = 0;
            sIndex = 0;//x.value.length();
            bottomVal = convert(bottomOperand[b]);
         //   System.out.print("b is = " + b + "\n");
            for( t = this.value.length()-1; t >= 0; t--){
                topVal = convert(topOperand[t]);
                /*
                *       88
                * x     33
                * ----------
                *        4      carry = 2 ans = 4
                *       64      carry = 2 ans = 3*8 + 2 = 6
                *               64 is stored in the output array
                *               the first loop is incremented the index will be outputArray[fIndex+sIndex]
                *      64
                * +             the final result will be
                * ---------------
                *  */
                ans = (bottomVal*topVal) + carry + outputArray[sIndex+fIndex];
                //if(ans >= 10){
                carry = ans/10;
                ans %= 10;
                //}
                //System.out.printf("%d * %d = %d and the carry is %d \n", bottomVal, topVal, ans, carry);
                outputArray[sIndex+fIndex] = ans;
                sIndex++;
            }
            if(carry > 0){
                //int outLen = outputArray.length;
           //     System.out.print("findex = " + fIndex + " and sIndex = " + sIndex + "\n");
                outputArray[fIndex+sIndex] =  (carry);
            }

            fIndex++;
        }


        String s = "";
        int i = outputArray.length-1;
        while(i >= 1 && outputArray[i] == 0) {
            i--;
        }
        //if(i == -1) return "";
        while(i >= 0){
            //System.out.print("here\n");
            s += outputArray[i--];
        }
        //System.out.print(" output array in string is equal to  " + s + "  \n");
        /*int i;
        for(i = 0; i < outputArray.length; i++){
            System.out.print("checking = " + outputArray[i] + "\n");
            if(outputArray[i] != "0") break;
        }

        System.out.print("when there is no zero = " + i + "\n");
*/

        retVal.value = s; //Arrays.toString(outputArray);

        return retVal;
    }


    private MyBigIntegers subNumbersH(MyBigIntegers X, int m){
        System.out.print(X.value +  " m = " + m + "\n");
        MyBigIntegers retval = new MyBigIntegers();
        char[] temp = new char[m+1];
        char[] charX = X.value.toCharArray();
        // get numbers form the right
        if(m > X.value.length()) m = X.value.length();

        System.arraycopy(charX, 0, temp, 0, m);

        retval.value = String.valueOf(temp);

        return retval;
    }

    private MyBigIntegers subNumbersL(MyBigIntegers X, int m){
        System.out.print("Lower \n");
        MyBigIntegers retval = new MyBigIntegers();
        char[] temp = new char[m];
        char[] charX = X.value.toCharArray();
        int srcPos = m-1;
        // get numbers form the right
        if(m > X.value.length()) {
            m = 0;
            srcPos = 0;
        }
        else {
            m = X.value.length() - m;
            srcPos = m-1;
        }
        System.arraycopy(charX, srcPos, temp, 0, m  );

        retval.value = String.valueOf(temp);

        return retval;
    }


    //// V^p
    MyBigIntegers topow(MyBigIntegers p){
        MyBigIntegers valOne = new MyBigIntegers("1");
        MyBigIntegers retVal = new MyBigIntegers("1");
        for(MyBigIntegers i = new MyBigIntegers("1"); !(i.greaterThan(p)); i=i.sum(valOne) ){
            retVal = retVal.times(this);
        }

        return  retVal;
    }


    public MyBigIntegers karatsubaMultiply(MyBigIntegers X){
        MyBigIntegers r = new MyBigIntegers();
        MyBigIntegers valTen = new MyBigIntegers("10");
        BigInteger valTwo = new BigInteger("2");
        MyBigIntegers myValTwo = new MyBigIntegers("2");

        // divide the number
        int n = Integer.max(this.value.length(), X.value.length());
        double m = Math.ceil(n/2.0); /// m = 3

        /// use mybig int class for n and m
        BigInteger BigIntegerN;// = new MyBigIntegers();
        BigInteger l;// = new BigInteger();
        if(this.greaterThan(X)){
            BigIntegerN = new BigInteger(this.value);
        } else{
            BigIntegerN = new BigInteger(X.value);
        }
        l = BigIntegerN.divide(valTwo);
        MyBigIntegers base = new MyBigIntegers(l.toString());

        if(this.value.length() < 10 || X.value.length() < 10){
            return this.times(X);
        }

        // right shift
        MyBigIntegers x_H = subNumbersH(this, (int)m);// 0-3
        MyBigIntegers x_L = subNumbersL(this, (int)m); /// get m digits from right

        MyBigIntegers y_H = subNumbersH(X, (int)m);
        MyBigIntegers y_L = subNumbersL(X, (int)m);


        System.out.print("X_h = " + x_H.value + "  x_l = " + x_L.value + "  y_h = " + y_H.value + "  y_l = " + y_L.value + "\n");


        MyBigIntegers z0 = x_L.karatsubaMultiply(x_H);
        MyBigIntegers z1 = (x_L.sum(x_H)).karatsubaMultiply( y_L.sum(y_H) );
        MyBigIntegers z2 = x_H.karatsubaMultiply(y_H);


        /////// (z2 * 10^2)
        return (z2.karatsubaMultiply( valTen.topow(base.times(myValTwo)))).sum(((z1.minus(z2)).minus(z0)).karatsubaMultiply(valTen.topow(base))).sum(z0);
    }


    void printBigInt(){

        //if(x.sign == -1) System.out.print("-");
        //int length = value.length();
        //for(int i = 0; i < length; i++){
            System.out.print(value);
        //}

        System.out.print("\n");
    }

    public static long getCPUTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }

    public static void verificationTests(){
        MyBigIntegers A = new MyBigIntegers();
        MyBigIntegers B = new MyBigIntegers();
        System.out.print("Addition\n");
        System.out.printf("%-30s%-30s%-30s\n", "Operand 1", "Operand 2", "Result");
        for(int N = 1; N < 25; N++){
            A = new MyBigIntegers(GenerateString(N));
            B = new MyBigIntegers(GenerateString(N));

            System.out.printf("%-30s%-30s%-30s\n", A.Value(), B.Value(), (A.sum(B)).AbbreviatedValue()  );
        }

        System.out.print("\nMultiplication\n");
        System.out.printf("%-30s%-30s%-30s\n", "Operand 1", "Operand 2", "Result");
        for(int N = 1; N < 25; N++){
            A = new MyBigIntegers(GenerateString(N));
            B = new MyBigIntegers(GenerateString(N));

            System.out.printf("%-30s%-30s%-30s\n", A.Value(), B.Value(), (A.times(B)).AbbreviatedValue() );
        }
    }

    public static void resultTable(){
        MyBigIntegers A = new MyBigIntegers();
        MyBigIntegers B = new MyBigIntegers();
        MyBigIntegers C = new MyBigIntegers();
        int count = 0;

        long addCurrTime;
        long addPreTime = 0;
        long addEndTime;

        long multCurrTime;
        long multPreTime = 0;
        long multEndTime;


        System.out.print("\nAddition\n");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n" , "N", "First Operand", "Second Operator", "Time", "Doubling Ratio", "Expected Doubling Ratio");
        for(int N = 1; N < 10000; N*=2){
            A = new MyBigIntegers(GenerateString(N));
            B = new MyBigIntegers(GenerateString(N));

            addCurrTime = getCPUTime();
            C = A.sum(B);
            addEndTime = getCPUTime();
            if(count == 0) {
                System.out.printf("%-20d%-20s%-20s%-20.3f%-20s%-20s\n" , N, A.AbbreviatedValue(), B.AbbreviatedValue(), (float)(addEndTime-addCurrTime), "-", "-");
            } else {
                float dr = (float)(addCurrTime/(addPreTime));
                System.out.printf("%-20d%-20s%-20s%-20.3f%-20.3f%-20.3f\n" ,
                        N, A.AbbreviatedValue(), B.AbbreviatedValue(), (float)(addEndTime-addCurrTime), dr, (float)(1));
            }
            addPreTime = addCurrTime;
            count++;
        }


        count = 0;
        System.out.print("\nMultiplication\n");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n" , "N", "First Operand", "Second Operator", "Time", "Doubling Ratio", "Expected Doubling Ratio");
        for(int N = 1; N < 1000000; N*=2){
            A = new MyBigIntegers(GenerateString(N));
            B = new MyBigIntegers(GenerateString(N));

            multCurrTime = getCPUTime();
            C = A.times(B);
            multEndTime = getCPUTime();
            if(count == 0) {
                System.out.printf("%-20d%-20s%-20s%-20.3f%-20s%-20s\n" , N, A.AbbreviatedValue(), B.AbbreviatedValue(), (float)(multEndTime-multCurrTime), "-", "-");
            } else {
                float dr = (float) (Math.pow(multCurrTime, 2.0)/(Math.pow(multPreTime, 2.0)));
                float edr = (float)(Math.pow(N, 2.0)/ (Math.pow(N/2.0, 2.0)));

                System.out.printf("%-20d%-20s%-20s%-20.3f%-20.3f%-20.3f\n" ,
                        N, A.AbbreviatedValue(), B.AbbreviatedValue(), (float)(multEndTime-multCurrTime), dr, edr);
            }
            multPreTime = multCurrTime;
            count++;


        }
    }


    public static void main(String[] args) {
        /// some constants
/*

        MyBigIntegers valOne = new MyBigIntegers("1");
        MyBigIntegers valTwo = new MyBigIntegers("2");
        MyBigIntegers X = new MyBigIntegers("14");

        MyBigIntegers A = new MyBigIntegers("0");
        MyBigIntegers B = new MyBigIntegers("1");
        MyBigIntegers next = new MyBigIntegers();
        // if X.greaterThan
        if(!X.greaterThan(valTwo)) {
            System.out.print("fib = " + X.Value() + "\n");
        }

        // i.value
        for(MyBigIntegers i = new MyBigIntegers("2"); !(i.greaterThan(X)); i=i.sum(valOne)){
            next = A.sum(B);// + B;
            System.out.printf("%s + %s = %s", A.value, B.value, next.value);
            A = B;
            B = next;
            //System.out.printf("%s + %s = %s\n", A.Value(), B.Value(), next.Value());
        }


        System.out.print("\n fin loop answer = " + B.value + "\n");
*/
        MyBigIntegers A = new MyBigIntegers("999999999999999999999999999999");
        MyBigIntegers B = new MyBigIntegers("1200008975466");
        MyBigIntegers C = new MyBigIntegers("1");
        System.out.print(A.AbbreviatedValue());
/*
        C = A.sum(B);
        System.out.print("\n dsdsdsds =  " + C.Value() + "\n");

        if(B.greaterThan(A)){
            System.out.print("yes " + B.Value() + " is greate than " + A.Value() + "\n");
        } else{
            System.out.print("it is not big\n");
        }
*/


/*
        long s = getCPUTime();
        System.out.print("karatsuba function is called \n");
        MyBigIntegers l =  A.karatsubaMultiply(B);
        long e = getCPUTime();
        System.out.print("the result of karatsuba is = " + l.value + " and it took " + (e-s) + "\n");
*/

        //System.out.print("\nGenerate String  = " + GenerateString(10) + "\n");

        resultTable();



/*

        long a = getCPUTime();
        System.out.print("original function is called \n");
        MyBigIntegers t =  A.times(B);
        long z = getCPUTime();
        System.out.print("the result of original is = " + t.value + " and it took " + (z-a) + "\n");
*/
//        System.out.print(" time = " + A.times(B).value + "\n");

/*        MyBigIntegers retVal = new MyBigIntegers("1");
        MyBigIntegers temp = new MyBigIntegers();
        for(MyBigIntegers i = new MyBigIntegers("1"); !(i.greaterThan(B)); i=i.sum(C) ){
            temp = retVal.times(A);
            retVal = temp;
            System.out.print("ret =  " + retVal.value + "  i = " + i.value + "\n");
        }
        System.out.print("asasaret =  " + retVal.value + "\n");*/

        //System.out.print("\ntopow testing : " +  A.topow(B).value + "\n");


        //for (int i = )

/*
        for(MyBigIntegers i = new MyBigIntegers("1"); !(i.greaterThan(B)) ; i=i.sum(A)){
            System.out.print(i.Value());
        }
        System.out.print("\n");
*/

/*

        MyBigIntegers i = A;
        i=i.sum(A);
        i.printBigInt();
        i=i.sum(A);
        i.printBigInt();
        i=i.sum(A);
        i.printBigInt();
        i=i.sum(A);
        i.printBigInt();

        C = i.sum(A);
        //C = A.minus(B);
        C.printBigInt();
        System.out.print("\n the lenght of c.value is " + C.value.length() + "\n");
        B = C.sum(A);
        B.printBigInt();
*/

        //A = C;
        //A.printBigInt();

 //       A.times(B);


    }
}
