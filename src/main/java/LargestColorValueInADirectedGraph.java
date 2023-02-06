import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class LargestColorValueInADirectedGraph {
    /*
        There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
        You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed).
        You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
        A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k.
        The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
        Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

        Example 1.
        Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
        Output: 3
        Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
        Example 2.
        Input: colors = "a", edges = [[0,0]]
        Output: -1
        Explanation: There is a cycle from 0 to 0.


        Constraints:

        n == colors.length
        m == edges.length
        1 <= n <= 105
        0 <= m <= 105
        colors consists of lowercase English letters.
        0 <= aj, bj < n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.largestPathValue("abaca", new int[][]{{0,1},{0,2},{2,3},{3,4}}));
    }

    // Time: O(V+E)
    // Space: O(N^2)
    static class Solution {
        public static int largestPathValue(String colors, int[][] edges) {
            ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
            int n=colors.length();
            char[] color=colors.toCharArray();

            for(int i=0;i<n;i++)
                graph.add(i,new ArrayList<>());
            int[] inDegree = new int[n];
            for(int[] edge:edges){
                int u=edge[0];
                int v=edge[1];
                inDegree[v]++;
                graph.get(u).add(v);
            }
            int[][] map=new int[n][26];
            Queue<Integer> que=new ArrayDeque<>();
            for(int i=0;i<n;i++){
                if(inDegree[i]==0){
                    que.add(i);
                    map[i][color[i]-'a']=1;
                }
            }

            int res=0;
            int seen=0;
            while(que.size()>0){
                int node=que.remove();
                seen++;
                int max=getMax(map[node]);
                res=Math.max(res,max);
                for(int nbr:graph.get(node)) {
                    for(int i=0;i<26;i++){
                        map[nbr][i]=Math.max(map[nbr][i],map[node][i] + (color[nbr]-'a'==i?1:0));
                    }
                    inDegree[nbr]--;
                    if(inDegree[nbr]==0) {
                        que.add(nbr);
                    }
                }
            }
            return seen==n?res:-1;
        }

        private static int getMax(int[] num){
            int max=num[0];
            for(int n:num){
                max=Math.max(n,max);
            }
            return max;
        }
    }
}
