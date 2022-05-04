import java.util.HashMap;
import java.util.Map;

public class CountGoodMeals {
    /*
        A good meal is a meal that contains exactly two different food items with a sum of deliciousness equal to a power of two.
        You can pick any two different foods to make a good meal.

        Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the ith item of food,
         return the number of different good meals you can make from this list modulo 10^9 + 7.

        Note that items with different indices are considered different even if they have the same deliciousness value.

        Example 1.
        Input: deliciousness = [1,3,5,7,9]
        Output: 4
        Explanation: The good meals are (1,3), (1,7), (3,5) and, (7,9).
        Their respective sums are 4, 8, 8, and 16, all of which are powers of 2.
        Example 2.
        Input: deliciousness = [1,1,1,3,3,3,7]
        Output: 15
        Explanation: The good meals are (1,1) with 3 ways, (1,3) with 9 ways, and (1,7) with 3 ways.


        Constraints:

        1 <= deliciousness.length <= 105
        0 <= deliciousness[i] <= 220
     */

    public static void main(String[] args) {
        int[] deliciousness = {1,3,5,7,9};
        System.out.println("Solution I: " + Solution_I.countPairs(deliciousness));
        System.out.println("Solution II: " + Solution_II.countPairs(deliciousness));
    }

    // Time: O(N)
    // Space:  O(N)
    static class Solution_I {
        public static int countPairs(int[] d) {
            int mod = (int)1e9+7;
            int count = 0;
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int val : d){
                int power = 1;
                for(int j = 0; j <= 21; j++){
                    if(power - val >= 0 && map.containsKey(power - val)){
                        count += map.get(power - val);
                        count = count % mod;
                    }
                    power *= 2;
                }
                map.put(val, map.getOrDefault(val, 0) + 1);
            }
            return count;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static int countPairs(int[] deliciousness) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int n : deliciousness){ // freq table
                map.merge(n, 1, Integer::sum);
            }

            long ans = 0;
            for (int key : map.keySet()){
                int i = 1, cur = map.get(key);
                while(key > i - key) i <<= 1; // only check those no less than the current key number
                while(i <= (1 << 21)){
                    ans += (key != i - key?
                            (long) cur * map.getOrDefault(i - key, 0) : // choose one from each pile
                            ((long) cur * (cur - 1)) / 2); // edge case -> choose two from the same pile
                    i <<= 1;
                }
            }

            return (int)(ans % (int)(1e9+7));
        }
    }
}
