import java.util.Arrays;

public class NumberOfOperationsToMakeNetworkConnected {
    /*
        There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi]
        represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
        You are given an initial computer network connections. You can extract certain cables between two directly connected computers,
        and place them between any pair of disconnected computers to make them directly connected.
        Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

        Example 1.
        Input: n = 4, connections = [[0,1],[0,2],[1,2]]
        Output: 1
        Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
        Example 2.
        Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
        Output: 2
        Example 3.
        Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
        Output: -1
        Explanation: There are not enough cables.

        Constraints:

        1 <= n <= 105
        1 <= connections.length <= min(n * (n - 1) / 2, 105)
        connections[i].length == 2
        0 <= ai, bi < n
        ai != bi
        There are no repeated connections.
        No two computers are connected by more than one cable.
     */
    public static void main(String[] args) {
        int[][] connections = new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println("Solution: " + Solution.makeConnected(6, connections));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int makeConnected(int n, int[][] connections) {
            if(connections.length < n-1)
                return -1;
            int[] parent = new int[n];
            int[] rank = new int[n];
            for(int i = 0; i<n; i++){
                parent[i] = i;
            }
            Arrays.fill(rank, 1);
            for (int[] connection : connections) {
                union(parent, rank, connection[0], connection[1]);
            }
            int countOfUnconnectedComputers =0;
            for(int i = 0; i< parent.length; i++){
                if(parent[i] == i)
                    countOfUnconnectedComputers++;
            }
            countOfUnconnectedComputers--;
            return countOfUnconnectedComputers;
        }

        private static void union(int[] parent, int[] rank, int x, int y){
            int parentX = find(parent, x);
            int parentY = find(parent, y);

            if(parentX==parentY)
                return;

            if(rank[parentX] > rank[parentY]){
                parent[parentY] = parent[parentX];
            }else if(rank[parentX] < rank[parentY]){
                parent[parentX] = parent[parentY];
            }else{
                parent[parentY] = parent[parentX];
                rank[parentX]++;
            }
        }

        private static int find(int[] parent, int x){
            if(parent[x] ==x)
                return x;
            return parent[x] = find(parent, parent[x]);
        }
    }
}
