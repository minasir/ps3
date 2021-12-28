/* Misha Nasir 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */
import java.util.*;

public class BigInt{
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20; // size of array to be set aka 20 single digit elements in array 
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;


    // custom constructor 
    public BigInt(int[] arr){ 
        this.digits = new int[SIZE]; // set digits to size of 20 
        if(arr == null || arr.length > SIZE){ 
            throw new IllegalArgumentException("Array is empty or too large");
        }
        int zeros = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0){ // if an array of only zeros, then significance is 1
                numSigDigits = 1;
                zeros++;
            }
           else{
               break;
           }
       }
        if (zeros == arr.length){
            numSigDigits = 1;
            return;
          }
     if(zeros != 20){
        int decrement = digits.length-1;
        for (int i = arr.length-1; i >= 0; i--) {
            if(arr[i] < 0 | arr[i] > 9){
                throw new IllegalArgumentException("Invalid inputs, should be between 0 and 9");
            }
            else{
                if (arr[i] >= 0 | arr[i] <= 9) { // if arr[i] is between 0 or 9 then set it to right most bit of digits 
                    digits[decrement--]=arr[i];
                    }
                }           
            }
         numSigDigits = arr.length - zeros;
        }
    }

    public String toString(){
        String str = "";
         //int i = digits.length-numSigDigits
        for(int i = digits.length-numSigDigits; i < digits.length; i++){
            str = str + digits[i];
        }
       // System.out.println("testing :)");
        return str; 
    }
    
    public BigInt(int n){ 
        if(n < 0){
           throw new IllegalArgumentException("Number cannot be negative");
        } 
        if (n == 0){ // corner case 
            this.digits = new int[SIZE];
            this.numSigDigits = 1;  
            return;
        }
        this.digits = new int[SIZE];
        int i = SIZE - 1; // 19
        int num = 0; 

    //    if(n==0){
    //        this.numSigDigits=1; 
    //    }

        while(n != 0){
            this.digits[i]= n % 10;
            n /= 10;
            i -=1;
            num +=1;
        }
        numSigDigits = num; //test 5
    }
    
    public int compareTo(BigInt other){
        if(other == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        for(int i = 0; i < SIZE; i++){
            if(this.digits[i]< other.digits[i]){  
                return -1; 
            }
            else if(this.digits[i]> other.digits[i]){
                return 1;
            }
        }
       // System.out.println("Testing");
            return 0; 
        }

        public BigInt add(BigInt other){
            if(other == null){
                throw new IllegalArgumentException("Array cannot be empty!");
            }
            int[] arr = new int[SIZE]; 
            int carry = 0;
            for(int i = SIZE-1; i >=0; i--){
                int sum = this.digits[i]+other.digits[i]+carry;
                carry = sum/10;
                arr[i] = sum%10;
                if(i > 0 && carry == 1){
                    arr[i-1] +=1;
                }
            }
            if(carry == 1){
                throw new ArithmeticException();
            }
            return new BigInt(arr);
        }

    public BigInt mul(BigInt other){
        if(other == null){
            throw new IllegalArgumentException();
        }
        BigInt y = new BigInt();        
        int carry = 0;
        if(carry == 1){
            throw new ArithmeticException("Overflow!!!");
        }
       
        return y;
    }

    // accesor method to get numSigDigits -- makes it available to other methods now 
    public int getNumSigDigits(){
        return this.numSigDigits;
    }

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }
    
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class");
        System.out.println();

       
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 1,2,3,4,5,6,7};
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();

        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        
        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        //System.out.println(Arrays.toString(b2.digits));
        //System.out.println(b2.numSigDigits);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();

       
        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
 
        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        
    }
}
