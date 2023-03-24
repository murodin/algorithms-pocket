import java.util.ArrayList;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    /*
        There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree).
        Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
        Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
        This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
        Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
        It's guaranteed that each city can reach city 0 after reorder.

        Example 1.
        Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
        Output: 3
        Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
        Example 2.
        Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
        Output: 2
        Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
        Example 3.
        Input: n = 3, connections = [[1,0],[2,0]]
        Output: 0

        Constraints:

        2 <= n <= 5 * 104
        connections.length == n - 1
        connections[i].length == 2
        0 <= ai, bi <= n - 1
        ai != bi
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minReorder(6, new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minReorder(int n, int[][] connections) {
            ArrayList<int[]>[] a = new ArrayList[n];
            for(int i=0;i<n;i++){
                a[i] = new ArrayList<>();
            }
            for(int[] x:connections){
                a[x[0]].add(new int[]{x[1],1});
                a[x[1]].add(new int[]{x[0],0});
            }
            boolean[] vis = new boolean[n];
            return dfs(a,0,vis);
        }

        private static int dfs(ArrayList<int[]>[] a, int x, boolean[] vis){
            vis[x]=true;
            int change=0;
            for(int[] y:a[x]){
                if(!vis[y[0]]){
                    change += dfs(a,y[0],vis)+y[1];
                }
            }
            return change;
        }
    }
}
