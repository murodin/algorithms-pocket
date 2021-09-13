public class OnesAndZeroes {

    /*
        You are given an array of binary strings strs and two integers m and n.
        Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
        A set x is a subset of a set y if all elements of x are also elements of y.

        Example 1.
        Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
        Output: 4
        Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
        Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
        {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.

        Example 2.
        Input: strs = ["10","0","1"], m = 1, n = 1
        Output: 2
        Explanation: The largest subset is {"0", "1"}, so the answer is 2.
     */

    public static void main(String[] args) {
        String[] testStr = {"10","0001","111001","1","0"};
        System.out.println("Solution: " + Solution.findMaxForm(testStr, 5, 3));
        System.out.println("Solution_II: " + Solution_II.findMaxForm(testStr, 5, 3));
    }

    // Time: O(2^N*N)
    // Space: O(N*M*Len)
    static class Solution {
        static int[][][] dp;
        public static int findMaxForm(String[] strs, int m, int n) {
            dp= new int[m+1][n+1][strs.length];
            return helper(strs,m,n,0);
        }

        static int helper(String[] strs, int zero, int one, int index){
            if(index==strs.length || zero + one == 0){
                return 0;
            }

            if(dp[zero][one][index]>0) {
                return dp[zero][one][index];
            }

            int[] count = count(strs[index]);

            //consider changes the zero ad one
            int consider=0;

            if(zero >=count[0] && one>=count[1]) {
                consider = 1 + helper(strs,zero-count[0],one-count[1],index+1);
            }

            int skip = helper(strs,zero,one,index+1);

            //skip
            dp[zero][one][index]=Math.max(consider,skip);;
            return dp[zero][one][index];

        }

        static int[] count(String s){
            int[] count=new int[2];
            for(char c: s.toCharArray()) {
                count[c-'0']++;
            }
            return count;
        }
    }

    // Time: O(N*M*Len)
    // Space: O(N*M)
    static class Solution_II {
        static int[][] dp;
        public static int findMaxForm(String[] strs, int m, int n) {
            dp= new int[m+1][n+1];

            for(String s:strs){
                int[] count = count(s);
                //zero m-count[0]  ---- 0
                //one n - count[1] ---- 0
                for(int zero=m;zero>=count[0];zero--){
                    for(int one=n; one>=count[1]; one--){
                        dp[zero][one] = Math.max(dp[zero-count[0]][one-count[1]] +1 , dp[zero][one]);
                    }
                }
            }


            return dp[m][n];
        }

        static int[] count(String s){
            int[] count=new int[2];
            for(char c: s.toCharArray()){
                count[c-'0']++;
            }
            return count;
        }
    }
}
