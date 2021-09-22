import java.util.Arrays;

public class ReorderedPowerOf2 {

    /*
        You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
        Return true if and only if we can do this so that the resulting number is a power of two.

        Example 1.
        Input: n = 1
        Output: true
        Example 2.
        Input: n = 10
        Output: false
        Example 3.
        Input: n = 16
        Output: true
        Example 4.
        Input: n = 24
        Output: false
        Example 5.
        Input: n = 46
        Output: true

     */

    public static void main(String[] args) {
        System.out.println("Test 1:" + Solution.reorderedPowerOf2(1));
        System.out.println("Test 10:" + Solution.reorderedPowerOf2(10));
        System.out.println("Test 16:" + Solution.reorderedPowerOf2(16));
        System.out.println("Test 24:" + Solution.reorderedPowerOf2(24));
        System.out.println("Test 46:" + Solution.reorderedPowerOf2(46));
    }


    // Time: O(Log^2N)
    // Space: O(LogN)
    static class Solution {
        public static boolean reorderedPowerOf2(int N) {
            int[] countN = count(N);
            int num = 1;

            for(int i=0;i<31;i++){
                if(Arrays.equals(countN,count(num))){
                    return true;
                }
                num = num<<1;
            }
            return false;
        }

        static int[] count(int N){
            int[] arr = new int[10];
            while(N>0){
                arr[N%10]++;
                N/=10;
            }
            return arr;
        }
    }

}
