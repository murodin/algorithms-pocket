import java.util.*;

public class OpenTheLock {
    /*
        You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
        The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
        The lock initially starts at '0000', a string representing the state of the 4 wheels.
        You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
        the wheels of the lock will stop turning and you will be unable to open it.

        Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock,
        or -1 if it is impossible.

        Example 1:

        Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
        Output: 6
        Explanation:
        A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
        Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
        because the wheels of the lock become stuck after the display becomes the dead end "0102".
        Example 2:

        Input: deadends = ["8888"], target = "0009"
        Output: 1
        Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
        Example 3:

        Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
        Output: -1
        Explanation: We cannot reach the target without getting stuck.


        Constraints:

        1 <= deadends.length <= 500
        deadends[i].length == 4
        target.length == 4
        target will not be in the list deadends.
        target and deadends[i] consist of digits only.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int openLock(String[] deadends, String target) {
            Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
            if (deadSet.contains("0000")) return -1;
            Queue<String> q = new LinkedList<>(Collections.singletonList("0000"));
            for (int steps = 0; !q.isEmpty(); ++steps) {
                for (int i = q.size(); i > 0; --i) {
                    String curr = q.poll();
                    assert curr != null;
                    if (curr.equals(target)) return steps;
                    for (String nei : neighbors(curr)) {
                        if (deadSet.contains(nei)) continue;
                        deadSet.add(nei); // Marked as visited
                        q.offer(nei);
                    }
                }
            }
            return -1;
        }
        static List<String> neighbors(String code) {
            List<String> result = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int x = code.charAt(i) - '0';
                for (int diff = -1; diff <= 1; diff += 2) {
                    int y = (x + diff + 10) % 10;
                    result.add(code.substring(0, i) + ("" + y) + code.substring(i + 1));
                }
            }
            return result;
        }
    }
}
