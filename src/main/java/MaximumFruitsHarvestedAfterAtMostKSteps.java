import java.util.HashMap;
import java.util.Map;

public class MaximumFruitsHarvestedAfterAtMostKSteps {
    /*
        Fruits are available at some positions on an infinite x-axis.
        You are given a 2D integer array fruits where fruits[i] = [positioni, amounti] depicts amounti fruits at the position positioni.
        fruits is already sorted by positioni in ascending order, and each positioni is unique.
        You are also given an integer startPos and an integer k. Initially, you are at the position startPos.
        From any position, you can either walk to the left or right.
        It takes one step to move one unit on the x-axis, and you can walk at most k steps in total.
         For every position you reach, you harvest all the fruits at that position, and the fruits will disappear from that position.

        Return the maximum total number of fruits you can harvest.

        Example 1.
        Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4
        Output: 9
        Explanation:
        The optimal way is to:
        - Move right to position 6 and harvest 3 fruits
        - Move right to position 8 and harvest 6 fruits
        You moved 3 steps and harvested 3 + 6 = 9 fruits in total.
        Example 2.
        Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
        Output: 14
        Explanation:
        You can move at most k = 4 steps, so you cannot reach position 0 nor 10.
        The optimal way is to:
        - Harvest the 7 fruits at the starting position 5
        - Move left to position 4 and harvest 1 fruit
        - Move right to position 6 and harvest 2 fruits
        - Move right to position 7 and harvest 4 fruits
        You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14 fruits in total.
        Example 3:


        Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2
        Output: 0
        Explanation:
        You can move at most k = 2 steps and cannot reach any position with fruits.


        Constraints:

        1 <= fruits.length <= 105
        fruits[i].length == 2
        0 <= startPos, positioni <= 2 * 105
        positioni-1 < positioni for any i > 0 (0-indexed)
        1 <= amounti <= 104
        0 <= k <= 2 * 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxTotalFruits(new int[][] {{2,8},{6,3},{8,6}}, 5, 4));
    }

    // Time: O(N+K)
    // Space: O(N+K)
    static class Solution {
        public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
            Map<Integer,Integer> frtMap = new HashMap<>();
            for(int[] frtpos: fruits){
                int pos = frtpos[0];
                int frt = frtpos[1];
                frtMap.put(pos, frt);
            }

            int maxFruits = 0;
            // to the left and right
            int[] toLft = new int[k+1];
            int[] toRgt = new int[k+1];

            toLft[0] = frtMap.getOrDefault(startPos, 0);
            toRgt[0] = frtMap.getOrDefault(startPos, 0);
            maxFruits = Math.max(toLft[0], maxFruits);
            for(int i = 1; i <= k; i++){
                // if you travel left linearly - and get Max
                int lftPos = startPos - i;
                toLft[i] = toLft[i-1] + frtMap.getOrDefault(lftPos, 0);
                maxFruits = Math.max(maxFruits, toLft[i]);

                // if you travel right linearly - and get Max
                int rgtPos = startPos + i;
                toRgt[i] = toRgt[i-1] + frtMap.getOrDefault(rgtPos, 0);
                maxFruits = Math.max(maxFruits, toRgt[i]);
            }

            // now a combination of lft and rgt
            for(int i = 1; i < (k+1)/2; i++){
                // stretch left i and back - to come back after going i
                // - you have to go 2*i just to get to startpos
                // - hence the k - 2*i
                int curVal = toLft[i] + toRgt[k - 2*i] - toRgt[0];
                maxFruits = Math.max(maxFruits, curVal);

                // stretch right i and back
                // same comment as above if you go i right, just to reach startpos 2*i is wasted on left
                // however, if the reward is high enough - account for that
                curVal = toRgt[i] + toLft[k-2*i] - toLft[0];
                maxFruits = Math.max(maxFruits, curVal);
            }
            return maxFruits;
        }
    }
}
