import java.util.*;

public class RestoreTheArrayFromAdjacentPairs {
    /*
        There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
        You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
        It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
        either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
        Return the original array nums. If there are multiple solutions, return any of them.

        Example 1:

        Input: adjacentPairs = [[2,1],[3,4],[3,2]]
        Output: [1,2,3,4]
        Explanation: This array has all its adjacent pairs in adjacentPairs.
        Notice that adjacentPairs[i] may not be in left-to-right order.
        Example 2:

        Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
        Output: [-2,4,1,-3]
        Explanation: There can be negative numbers.
        Another solution is [-3,1,4,-2], which would also be accepted.
        Example 3:

        Input: adjacentPairs = [[100000,-100000]]
        Output: [100000,-100000]


        Constraints:

        nums.length == n
        adjacentPairs.length == n - 1
        adjacentPairs[i].length == 2
        2 <= n <= 105
        -105 <= nums[i], ui, vi <= 105
        There exists some nums that has adjacentPairs as its pairs.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.restoreArray(new int[][]{{4, -2}, {1, 4}, {-3, 1}})));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int[] restoreArray(int[][] adjacentPairs) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(var prs : adjacentPairs){
                int u = prs[0], v = prs[1];
                graph.putIfAbsent(u, new ArrayList<>());
                graph.putIfAbsent(v, new ArrayList<>());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            HashSet<Integer> visitedVert = new HashSet<>();
            Deque<Integer> que = new ArrayDeque<>();
            que.offerLast(adjacentPairs[0][0]);
            int lastVertVist = -1;
            while(!que.isEmpty()){
                var currVert = que.pollFirst();
                lastVertVist = currVert;
                visitedVert.add(currVert);
                for(var neigh : graph.get(currVert)){
                    if(!visitedVert.contains(neigh))
                        que.offerLast(neigh);
                }
            }
            visitedVert.clear();
            List<Integer> orgArr = new ArrayList<>();
            buildArray(lastVertVist, graph, orgArr, visitedVert);
            return orgArr.stream().mapToInt(x->x).toArray();
        }
        private static void buildArray(int src, Map<Integer, List<Integer>> graph, List<Integer> orgArr, HashSet<Integer> visitedVert){
            if(visitedVert.contains(src)){
                return;
            }
            visitedVert.add(src);
            orgArr.add(src);
            for(var neigh : graph.get(src)){
                if(!visitedVert.contains(neigh))
                    buildArray(neigh, graph, orgArr, visitedVert);
            }

        }
    }
}
