public class FruitIntoBaskets {
    /*
        You are visiting a farm that has a single row of fruit trees arranged from left to right.
        The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
        You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

        You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
        Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right.
        The picked fruits must fit in one of your baskets.
        Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
        Given the integer array fruits, return the maximum number of fruits you can pick.

        Example 1.
        Input: fruits = [1,2,1]
        Output: 3
        Explanation: We can pick from all 3 trees.
        Example 2.
        Input: fruits = [0,1,2,2]
        Output: 3
        Explanation: We can pick from trees [1,2,2].
        If we had started at the first tree, we would only pick from trees [0,1].
        Example 3.
        Input: fruits = [1,2,3,2,2]
        Output: 4
        Explanation: We can pick from trees [2,3,2,2].
        If we had started at the first tree, we would only pick from trees [1,2].


        Constraints:

        1 <= fruits.length <= 105
        0 <= fruits[i] < fruits.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.totalFruit(new int[]{1,2,3,2,2}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int totalFruit(int[] fruits) {
            int[] counts = new int[fruits.length];
            int max = 0;
            int currMax = 0;
            int typeCount = 0;
            int start = 0;

            for(int i = 0; i < fruits.length; i++) {
                if(counts[fruits[i]] == 0) typeCount++;
                counts[fruits[i]]++;
                currMax++;

                while(typeCount > 2 && start < i) {
                    counts[fruits[start]]--;
                    if(counts[fruits[start]] == 0) typeCount--;
                    start++;
                    currMax--;
                }

                max = Math.max(max, currMax);
            }

            return max;
        }
    }
}
