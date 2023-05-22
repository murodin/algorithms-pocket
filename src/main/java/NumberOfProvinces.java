import java.util.ArrayList;

public class NumberOfProvinces {
    /*
        There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
        and city b is connected directly with city c, then city a is connected indirectly with city c.
        A province is a group of directly or indirectly connected cities and no other cities outside of the group.
        You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
        Return the total number of provinces.


        Example 1:


        Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
        Output: 2
        Example 2:


        Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
        Output: 3


        Constraints:

        1 <= n <= 200
        n == isConnected.length
        n == isConnected[i].length
        isConnected[i][j] is 1 or 0.
        isConnected[i][i] == 1
        isConnected[i][j] == isConnected[j][i]
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(N^2)
    // Space: O(N)
    class DSUbySize {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();
        public DSUbySize(int n) {
            for(int i = 0; i < n+1; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int find_parent(int u) {
            if(u == parent.get(u)) {
                return u;
            }
            int ultimate_parent = find_parent(parent.get(u));
            parent.set(u, ultimate_parent);
            return parent.get(u);
        }

        public void unionBySize(int u,int v) {
            int ult_pu = find_parent(u);
            int ult_pv = find_parent(v);
            if(ult_pu != ult_pv) {
                if(size.get(ult_pu) < size.get(ult_pv)) {
                    parent.set(ult_pu, ult_pv);
                    size.set(ult_pv, size.get(ult_pu) + size.get(ult_pv));
                } else {
                    parent.set(ult_pv, ult_pu);
                    size.set(ult_pu, size.get(ult_pu) + size.get(ult_pv));
                }
            }
        }
    }
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            int V = isConnected.length;
            DSUbySize ds = new DSUbySize(V);
            for(int i = 0; i < V; i++) {
                for(int j = 0; j < V; j++) {
                    if(isConnected[i][j] == 1) {
                        ds.unionBySize(i, j);
                    }
                }
            }

            int count = 0;
            for(int i=0; i < V; i++) {
                if(ds.parent.get(i) == i)
                    count++;
            }
            return count;
        }
    }
}
