import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandOfStraights {
    /*
        Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
        Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.

        Example 1:
        Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
        Output: true
        Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
        Example 2:
        Input: hand = [1,2,3,4,5], groupSize = 4
        Output: false
        Explanation: Alice's hand can not be rearranged into groups of 4.

        Constraints:

        1 <= hand.length <= 104
        0 <= hand[i] <= 109
        1 <= groupSize <= hand.length


        Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3));
    }

    // Time: O(MxN)
    // Space: O(N)
    static class Solution {
        public static boolean isNStraightHand(int[] hand, int groupSize) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int card : hand) {
                countMap.put(card, countMap.getOrDefault(card, 0) + 1);
            }
            Arrays.sort(hand);
            for (int k : hand) {
                if (countMap.get(k) == 0) {
                    continue;
                }
                for (int j = 0; j < groupSize; j++) {
                    int currCard = k + j;
                    if (countMap.getOrDefault(currCard, 0) == 0) {
                        return false;
                    }
                    countMap.put(currCard, countMap.get(currCard) - 1);
                }
            }
            return true;
        }
    }
}
