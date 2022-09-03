import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersWithSameConsecutiveDifferences {
    /*
        Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
        Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
        You may return the answer in any order.

        Example 1.
        Input: n = 3, k = 7
        Output: [181,292,707,818,929]
        Explanation: Note that 070 is not a valid number, because it has leading zeroes.
        Example 2.
        Input: n = 2, k = 1
        Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

        Constraints:

        2 <= n <= 9
        0 <= k <= 9
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.numsSameConsecDiff(3, 7)));
    }

    // Time: O(2^N)
    // Space: O(N)
    static class Solution {
        public static int[] numsSameConsecDiff(int n, int k) {
            //Creating a list to store ans
            List<Integer> ans=new ArrayList<Integer>();
            //Using for loop as first digit cannot be 0
            for(int i=1;i<10;i++){
                digitDiff(n-1,k,ans,i);
            }
            //As return type is integer array creating a array
            int[] res=new int[ans.size()];
            int j=0;
            //Storing the elements of list into an integer array
            for(int num:ans){
                res[j++]=num;
            }
            //Returning the array
            return res;
        }
        public static void digitDiff(int n, int k, List<Integer> ans, int num){
            //If num equals 0, implies we have foyund one number
            if(n==0){
                ans.add(num);
                return;
            }
            //Accessing the last digit by num%10
            int digit=num%10;
            //If digit-k>=0 means we can take that digit
            if(digit-k>=0){
                digitDiff(n-1,k,ans,num*10+(digit-k));
            }
            //If digit+k<=9 means we can take that digit
            //There's a check that digit-k!=digit+k this case occurs to avoid repeated function calls when k is 0
            if(digit+k<=9 && digit-k!=digit+k){
                digitDiff(n-1,k,ans,num*10+(digit+k));
            }
        }
    }
}
