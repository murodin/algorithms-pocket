import java.util.ArrayList;
import java.util.List;

public class CountWaysToBuildRoomsInAnAntColony {
    /*
        You are an ant tasked with adding n new rooms numbered 0 to n-1 to your colony.
        You are given the expansion plan as a 0-indexed integer array of length n, prevRoom, where prevRoom[i] indicates
        that you must build room prevRoom[i] before building room i, and these two rooms must be connected directly. Room 0 is already built,
        so prevRoom[0] = -1. The expansion plan is given such that once all the rooms are built, every room will be reachable from room 0.
        You can only build one room at a time, and you can travel freely between rooms you have already built only if they are connected.
        You can choose to build any room as long as its previous room is already built.
        Return the number of different orders you can build all the rooms in. Since the answer may be large, return it modulo 109 + 7.

        Example 1:
        Input: prevRoom = [-1,0,1]
        Output: 1
        Explanation: There is only one way to build the additional rooms: 0 → 1 → 2
        Example 2:
        Input: prevRoom = [-1,0,0,1,2]
        Output: 6
        Explanation:
        The 6 ways are:
        0 → 1 → 3 → 2 → 4
        0 → 2 → 4 → 1 → 3
        0 → 1 → 2 → 3 → 4
        0 → 1 → 2 → 4 → 3
        0 → 2 → 1 → 3 → 4
        0 → 2 → 1 → 4 → 3


        Constraints:

        n == prevRoom.length
        2 <= n <= 105
        prevRoom[0] == -1
        0 <= prevRoom[i] < n for all 1 <= i < n
        Every room is reachable from room 0 once all the rooms are built.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.waysToBuildRooms(new int[]{-1,0,0,1,2}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        static int mod = 1000_000_007;
        public static int waysToBuildRooms(int[] prevRoom) {
            int n = prevRoom.length;
            List[] children = new List[n];
            for(int i=0;i<n;i++) {
                children[i] = new ArrayList<>();
            }
            for(int i=1;i<n;i++) {
                children[prevRoom[i]].add(i);
            }
            int[] count = new int[n];
            dfs(0, children, count);
            int[] fact = new int[n+1], ifact = new int[n+1];
            fact[0] = 1;
            ifact[0] = 1;
            for(int i=1;i<=n;i++) {
                fact[i] = modMul(fact[i-1], i);
                ifact[i] = modMul(ifact[i-1], modExp(i, mod-2));
            }

            return helper(0, children, count, fact, ifact);
        }
        public static int helper(int root, List<Integer>[] children, int[] count, int[] fact, int[] ifact) {
            int res = fact[count[root]-1];
            List<Integer> list = children[root];
            for(Integer i: list) {
                int sub = helper(i, children, count, fact, ifact);
                res = modMul(res, sub);
                res = modMul(res, ifact[count[i]]);
            }
            return res;
        }
        public static int dfs(int root, List<Integer>[] children, int[] count) {
            List<Integer> list = children[root];
            int res = 1;
            for(Integer i: list) {
                count[i] = dfs(i, children, count);
                res += count[i];
            }
            count[root] = res;
            return res;
        }
        public static int modMul(int a, int b) {
            return (int)(((long)a*b) % mod);
        }

        public static int modExp(int i, int j) {
            if(j == 0) return 1;
            int res = modExp(i, j/2);
            if((j & 1) == 1) return modMul(i, modMul(res, res));
            return modMul(res, res);
        }
    }
}
