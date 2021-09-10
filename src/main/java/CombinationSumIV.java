import java.util.Arrays;

public class CombinationSumIV {

    /*
        Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
        The answer is guaranteed to fit in a 32-bit integer.

        Example.

        Input: nums = [1,2,3], target = 4
        Output: 7
        Explanation:
        The possible combination ways are:
        (1, 1, 1, 1)
        (1, 1, 2)
        (1, 2, 1)
        (1, 3)
        (2, 1, 1)
        (2, 2)
        (3, 1)
        Note that different sequences are counted as different combinations.
     */

    public static void main(String[] args) {
        int[] testNums = {1,2,3};
        int testTarget = 4;
        System.out.println("Solution I: " + Solution_I.combinationSum4(testNums, testTarget));
        System.out.println("Solution II: " + Solution_II.combinationSum4(testNums, testTarget));
        System.out.println("Solution III: " + Solution_III.combinationSum4(testNums, testTarget));
    }

    static class Solution_I {
        // Time: O(N^target)
        // Space: O(target)
        public static int combinationSum4(int[] nums, int target) {
            if(target==0){
                return 1;
            }
            int res = 0;
            for(int i:nums){
                if(i<=target){
                    res+=combinationSum4(nums,target - i);
                }
            }
            return res;
        }
    }

    static class Solution_II {
        static int[] dp ;
        // Time: O(N^target)
        // Space: O(target)
        public static int combinationSum4(int[] nums, int target) {
            dp=new int[target+1];
            Arrays.fill(dp,-1);
            dp[0] =1;

            helper(nums,target);
            return dp[target];
        }

        static int helper(int[] nums,int target){
            if(dp[target]>-1){
                return dp[target];
            }
            int res = 0;
            for(int i:nums){
                if(i<=target){
                    res+=helper(nums,target - i);
                }
            }
            dp[target]=res;
            return dp[target];
        }
    }

    static class Solution_III {
        static int[] dp ;
        public static int combinationSum4(int[] nums, int target) {
            dp=new int[target+1];
            dp[0] =1;

            for(int i = 1 ; i<=target ;i++){
                for(int n:nums){
                    if(i>=n){
                        dp[i] += dp[i-n];
                    }
                }
            }

            return dp[target];
        }
    }
}
