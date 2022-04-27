import java.util.*;

public class SmallestStringWithSwaps {
    /*
        You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
        You can swap the characters at any pair of indices in the given pairs any number of times.
        Return the lexicographically smallest string that s can be changed to after using the swaps.

        Example 1.
        Input: s = "dcab", pairs = [[0,3],[1,2]]
        Output: "bacd"
        Explaination:
        Swap s[0] and s[3], s = "bcad"
        Swap s[1] and s[2], s = "bacd"
        Example 2.
        Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
        Output: "abcd"
        Explaination:
        Swap s[0] and s[3], s = "bcad"
        Swap s[0] and s[2], s = "acbd"
        Swap s[1] and s[2], s = "abcd"
        Example 3:

        Input: s = "cba", pairs = [[0,1],[1,2]]
        Output: "abc"
        Explaination:
        Swap s[0] and s[1], s = "bca"
        Swap s[1] and s[2], s = "bac"
        Swap s[0] and s[1], s = "abc"


        Constraints:

        1 <= s.length <= 10^5
        0 <= pairs.length <= 10^5
        0 <= pairs[i][0], pairs[i][1] < s.length
        s only contains lower case English letters.
     */

    public static void main(String[] args) {
        var pairs = List.of(
                List.of(0,3), List.of(1,2), List.of(0,2)
        );
        System.out.println("Solution_I: " + Solution_I.smallestStringWithSwaps("dcab", pairs));
        System.out.println("Solution_II: " + Solution_II.smallestStringWithSwaps("dcab", pairs));
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution_I {
        static int[] parent;
        static int[] rank;
        public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int n = s.length();
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n;i++){//initialization
                parent[i] = i;
                rank[i] = 1;
            }

            for(List<Integer> l : pairs){//as if pairs are 0,1 and then 1,3 then it means we can also swap 0,3 so they are transistive in nature so group them using DSU
                union(l.get(0) , l.get(1));//grouping the indices at which we can swap
            }

            HashMap<Integer,PriorityQueue<Character>> map = new HashMap<>();//parent->available swaps
            //for each char we will have a priority queue in front of it with available char swap as lexogr
            for(int i = 0; i < n; i++){
                int parent = find(i);
                PriorityQueue<Character> temp = new PriorityQueue<>();
                if(map.containsKey(parent)){
                    temp = map.get(parent);
                }
                temp.add(s.charAt(i));
                map.put(parent, temp);
            }

            char[] ans = s.toCharArray();
            for(int i = 0 ; i < n;i++){
                ans[i] = map.get(find(i)).poll();//first find parent for this i then pop the first small char
            }
            return new String(ans);
        }

        private static int find(int x){
            if(parent[x] == x)
                return x;
            return parent[x] = find(parent[x]);
        }

        private static void union(int a, int b){
            a = find(a);
            b = find(b);
            if(a != b){
                if(rank[a] > rank[b]){
                    parent[b] = a;
                }else if(rank[b] > rank[a]){
                    parent[a] = b;
                }else{
                    parent[b] = a;
                    rank[a]++;
                }
            }
        }
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution_II {
        public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            int[] root = new int[s.length()];
            char[] ans = s.toCharArray();
            for (int i = 0; i < root.length; i++) {
                root[i] = i;
            }
            for (List<Integer> pair : pairs) {
                union(root, pair.get(0), pair.get(1));
            }

            Map<Integer, List<Integer>> group = new HashMap<>();
            for (int i = 0; i < root.length; i++) {
                int p = find(root, i);
                List<Integer> list = group.getOrDefault(p, new ArrayList<>());
                list.add(i);
                group.put(p, list);
            }

            for (int key : group.keySet()) {
                List<Integer> list = group.get(key);
                char[] temp = new char[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    temp[i] = ans[list.get(i)];
                }
                Arrays.sort(temp);
                for (int i = 0; i < list.size(); i++) {
                    ans[list.get(i)] = temp[i];
                }
            }
            return String.valueOf(ans);
        }

        private static int find(int[] root, int i) {
            if (root[i] != i) {
                return root[i] = find(root, root[i]);
            }
            return i;
        }

        private static void union(int[] root, int i, int j) {
            int ii = find(root, i);
            int jj = find(root, j);
            root[ii] = Math.min(ii, jj);
            root[jj] = Math.min(ii, jj);
            return;
        }
    }
}
