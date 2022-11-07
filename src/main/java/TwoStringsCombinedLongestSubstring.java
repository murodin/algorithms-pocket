import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TwoStringsCombinedLongestSubstring {
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.sol(0, 0, 10));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String sol(int AA, int AB, int BB) {
            Map<String, Integer> map = new HashMap<>();
            map.put("AA", AA);
            map.put("AB", AB);
            map.put("BB", BB);
            return helper(AA, AB, BB, map).toString();
        }

        private static StringBuilder helper(int AA, int AB, int BB, Map<String, Integer> map) {
            StringBuilder sb = new StringBuilder();
            int aa = 0, bb = 0, ab = 0;
            int max = Math.max(AA, BB);
            max = Math.max(max, AB);
            while(max > 0) {
                for (String key : map.keySet()) {
                    if(Objects.equals(key, "AA") && AA != aa && !sb.toString().endsWith("A")) {
                        sb.append(key);
                        map.put("AA", AA - 1);
                        aa++;
                    } else if(Objects.equals(key, "BB") && BB != bb && !sb.toString().endsWith("B")) {
                        sb.append(key);
                        map.put("BB", BB - 1);
                        bb++;
                    } else if(Objects.equals(key, "AB") && AB != ab && !sb.toString().endsWith("A")) {
                        sb.append(key);
                        map.put("AB", AB - 1);
                        ab++;
                    }
                }
                max--;
            }
            return sb;
        }
    }
}
