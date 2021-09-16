import java.util.Arrays;

public class RussianDollEnvelopes {

    /*
        You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
        One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
        Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
        Note: You cannot rotate an envelope.

        Example 1.
        Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
        Output: 3
        Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

        Example 2.
        Input: envelopes = [[1,1],[1,1],[1,1]]
        Output: 1
     */

    public static void main(String[] args) {
        int[][] testEnvs = {{5,4}, {6,5}, {6,7}, {2,3}, {7,6}};
        System.out.println("Solution: " + Solution.maxEnvelopes(testEnvs));
        System.out.println("Solution II: " + Solution_II.maxEnvelopes(testEnvs));
        System.out.println("Solution III: " + Solution_III.maxEnvelopes(testEnvs));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes,(a,b) -> a[0]-b[0]);

            int[] dp = new int[envelopes.length];
            int maxlen = 1;
            dp[0] = 1;

            for(int i=1;i<dp.length;i++){
                dp[i] = 1;

                for(int j=0;j<i;j++){
                    if(envelopes[i][0]!=envelopes[j][0] && envelopes[i][1]>envelopes[j][1]){
                        dp[i] = Math.max(dp[i],dp[j]+1);
                    }
                }

                maxlen = Math.max(maxlen,dp[i]);
            }
            return maxlen;
        }
    }

    // Time: O(N)
    // Space: O(NLog(N))
    static class Solution_II {
        public static  int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes,(a,b) -> a[0]==b[0]?b[1]-a[1]:a[0]-b[0]);

            int[] dp = new int[envelopes.length];
            int maxlen = 0;

            System.out.println("DP:" + Arrays.toString(dp));
            for(int i=0; i<dp.length; i++){
                //find the index of height of envelope[i]
                System.out.println("maxlen:" + maxlen + " env:" + envelopes[i][1]);
                int index = binarySearch ( dp, 0, maxlen, envelopes[i][1]);
                System.out.println("index:" + index);
                dp[index] = envelopes[i][1];

                if(index == maxlen){
                    maxlen++;
                }

            }
            System.out.println("DP:" + Arrays.toString(dp));
            return maxlen;
        }

        static int binarySearch(int[] arr , int start, int end, int target){
            while(start < end){
                int mid = start + (end - start)/2;

                if(arr[mid] == target){
                    return mid;
                } else if(arr[mid] > target){
                    end = mid;
                } else {
                    start = mid +1 ;
                }
            }
            return start;
        }
    }

    // Time: O(N)
    // Space: O(NLog(N))
    static class Solution_III {
        public static int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes,(a, b) -> a[0]==b[0]?b[1]-a[1]:a[0]-b[0]);

            int[] dp = new int[envelopes.length];
            int maxlen = 0;

            for(int i=0; i<dp.length; i++){
                //find the index of heioght of envelope[i]
                int index = binarySearch( dp, 0, maxlen, envelopes[i][1]);
                dp[index] = envelopes[i][1];

                if(index == maxlen){
                    maxlen++;
                }
            }
            return maxlen;
        }

        static int binarySearch(int[] arr , int start, int end, int target){
            int index = Arrays.binarySearch(arr,start,end,target);
            if(index < 0) {
                index = -(index+1);
            }
            return index;
        }
    }

}
