import java.util.HashMap;
import java.util.Map;

public class FrogJump {
    /*
        A frog is crossing a river.
        The river is divided into some number of units, and at each unit,
        there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

        Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone.
        Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

        If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

        Example 1.
        Input: stones = [0,1,3,5,6,8,12,17]
        Output: true
        Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
        Example 2.
        Input: stones = [0,1,2,3,4,8,9,11]
        Output: false
        Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.

        Constraints:

        2 <= stones.length <= 2000
        0 <= stones[i] <= 231 - 1
        stones[0] == 0
        stones is sorted in a strictly increasing order.
     */

    public static void main(String[] args) {
        int[] stones = {0,1,2,3,4,8,9,11};
        System.out.println("Solution_I -> Frog can jump -> " + Solution_I.canCross(stones));
        System.out.println("Solution_II -> Frog can jump -> " + Solution_II.canCross(stones));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution_I {
        static boolean[][] dp;
        public static boolean canCross(int[] stones) {
            if(stones[1]!=1) return false;

            int n = stones.length;
            dp = new boolean[n][n];
            return helper(stones,0,1);
        }

        static boolean helper(int[] stones,int lastIndex,int currentIndex){
            if(currentIndex == stones.length - 1){
                return true;
            }

            if(dp[lastIndex][currentIndex]) return false;

            int lastJump = stones[currentIndex] - stones[lastIndex];

            int nextIndex = currentIndex + 1;

            while(nextIndex<stones.length && stones[nextIndex]<= stones[currentIndex] + lastJump + 1){
                int nextJump = stones[nextIndex] - stones[currentIndex];
                int jump = nextJump - lastJump;

                if(jump>=-1 && jump<=1){
                    if(helper(stones,currentIndex,nextIndex)){
                        return true;
                    }
                }
                nextIndex++;
            }

            dp[lastIndex][currentIndex] = true;

            return false;
        }
    }


    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_II {
        static Map<String,Boolean> dp =null;
        public static boolean canCross(int[] stones) {
            if(stones[1]!=1) return false;
            dp = new HashMap<>();
            return helper(stones,1,1);
        }

        static boolean helper(int[] stones, int index,int jump){
            if(index == stones.length - 1){
                return true;
            }
            if(dp.containsKey(index+"-"+jump)) return dp.get(index+"-"+jump);
            boolean ans = false;
            for(int j = Math.max(1,jump - 1); j<=jump+1;j++){
                if(j==0) continue;
                int newStone = stones[index] + j;
                int indexNew = exists(stones,newStone);
                if(indexNew!=-1){
                    ans |= helper(stones,indexNew,j);
                }
            }

            dp.put(index+"-"+jump,ans);

            return ans;
        }

        static int exists(int[] stone,int target){
            int left=0,right = stone.length-1;
            while(left<=right){
                int mid = left + (right - left)/2;
                if(stone[mid]==target) {
                    return mid;
                }
                else if(stone[mid]>target) right = mid-1;
                else left = mid+1;
            }

            return -1;
        }
    }
}
