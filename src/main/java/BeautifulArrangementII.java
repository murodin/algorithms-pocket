import java.util.Arrays;

public class BeautifulArrangementII {

    /*
        Given two integers n and k, construct a list answer that contains n different positive integers ranging from 1 to n and obeys the following requirement:

        Suppose this list is answer = [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
        Return the list answer. If there multiple valid answers, return any of them.

        Example 1.
        Input: n = 3, k = 1
        Output: [1,2,3]
        Explanation: The [1,2,3] has three different positive integers ranging from 1 to 3, and the [1,1] has exactly 1 distinct integer: 1

        Example 2.
        Input: n = 3, k = 2
        Output: [1,3,2]
        Explanation: The [1,3,2] has three different positive integers ranging from 1 to 3, and the [2,1] has exactly 2 distinct integers: 1 and 2.
     */
    public static void main(String[] args) {
        Arrays.stream(Solution.constructArray(9, 4))
                .boxed()
                .forEach( e -> System.out.print(" " + e));
    }

    static class Solution {
        // Time: O(N)
        // Space: O(N)
        public static int[] constructArray(int n, int k) {
            int[] result =new int[n];

            int high = n , low = 1;
            int index = 0;

            result[index++] = low++;

            boolean isHigh = false;

            while(k>1){
                result[index++] = high--;
                k--;
                isHigh = true;
                if(k>1){
                    result[index++] = low++;
                    k--;
                    isHigh = false;
                }
            }

            //increasing or decreasing
            while(index<n){
                if(isHigh){
                    result[index++] = high--;
                }
                else{
                    result[index++] = low++;
                }
            }

            return result;
        }
    }
}
