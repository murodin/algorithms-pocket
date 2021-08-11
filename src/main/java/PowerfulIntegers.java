import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PowerfulIntegers {

    /*
        Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or equal to bound.
        An integer is powerful if it can be represented as x^i + y^j for some integers i >= 0 and j >= 0.
        You may return the answer in any order. In your answer, each value should occur at most once.

        Example 1:

        Input: x = 2, y = 3, bound = 10
        Output: [2,3,4,5,7,9,10]
        Explanation:
        2 = 2^0 + 3^0
        3 = 2^1 + 3^0
        4 = 2^0 + 3^1
        5 = 2^1 + 3^1
        7 = 2^2 + 3^1
        9 = 2^3 + 3^0
        10 = 2^0 + 3^2
     */

    public static void main(String[] args) {
        System.out.println("Powerful Integers: " + Solution.powerfulIntegers(2, 3, 10));

    }

    static class Solution {
        public static List<Integer> powerfulIntegers(int x, int y, int bound) {
            List<Integer> px = new ArrayList<>();
            List<Integer> py = new ArrayList<>();
            int powx = x, powy = y;
            px.add(1);
            py.add(1);

            if(x != 1) {
                while (powx < bound) {
                    px.add(powx);
                    powx *= x;
                }
            }

            if(y != 1) {
                while (powy < bound) {
                    py.add(powy);
                    powy *= y;
                }
            }

            Set<Integer> results = new HashSet<>();
            for(int i: px) {
                for(int j: py) {
                    if(i+j <= bound)
                        results.add(i+j);
                }
            }

            return results.stream().collect(Collectors.toList());
        }
    }
}
