import java.util.Arrays;

public class CheckingExistenceOfEdgeLengthLimitedPaths {
    /*
        An undirected graph of n nodes is defined by edgeList,
        where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
        Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j]
        whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
        Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.


        Example 1.
        Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
        Output: [false,true]
        Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
        For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
        For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
        Example 2.
        Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
        Output: [true,false]
        Exaplanation: The above figure shows the given graph.


        Constraints:

        2 <= n <= 105
        1 <= edgeList.length, queries.length <= 105
        edgeList[i].length == 3
        queries[j].length == 3
        0 <= ui, vi, pj, qj <= n - 1
        ui != vi
        pj != qj
        1 <= disi, limitj <= 109
        There may be multiple edges between two nodes.
     */

    public static void main(String[] args) {
        int n = 5;
        int[][] edgeList = {{0,1,10},{1,2,5},{2,3,9},{3,4,13}}, queries = {{0,4,14},{1,4,13}};
        System.out.println("Solution: " + Arrays.toString(Solution.distanceLimitedPathsExist(n, edgeList, queries)));
    }

    static class DisjointSetUnion{
        private final int[] parent;
        private final int[] rank;

        public DisjointSetUnion(int N){
            this.parent = new int[N];
            this.rank = new int[N];

            for(int i = 0; i < N; i++){
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public boolean areConnected(int u, int v){
            return (find(u) == find(v));
        }

        public void union(int u, int v){
            if(u != v){
                int p = find(u);
                int q = find(v);

                if(p != q){
                    if(rank[p] > rank[q]){
                        parent[q] = p;
                        rank[p] += rank[q];
                    }else{
                        parent[p] = q;
                        rank[q] += rank[p];
                    }
                }
            }
        }

        private int find(int u){
            int x = u;
            while(x != parent[x]){
                x = parent[x];
            }

            parent[u] = x;
            return x;
        }
    }

    static class Query implements Comparable<Query>{
        public int index, start, end, weight;

        public Query(int index, int start, int end, int weight){
            this.index = index;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Query query){
            return this.weight - query.weight;
        }
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
            DisjointSetUnion set = new DisjointSetUnion(n);

            Arrays.sort(edgeList, (x, y)->(x[2] - y[2]));  // arrange edges in ascending order of weights

            int i, Q = queries.length;
            int E = edgeList.length;
            Query[] queryArray = new Query[Q];
            for(i = 0; i < Q; i++){
                queryArray[i] = new Query(i, queries[i][0], queries[i][1], queries[i][2]);
            }

            Arrays.sort(queryArray);  // arrange queries in ascending order of threshold weights

            boolean[] result = new boolean[Q];
            int index = 0;
            for(i = 0; i < Q; i++){
                while(index < E && edgeList[index][2] < queryArray[i].weight){
                    set.union(edgeList[index][0], edgeList[index][1]);
                    ++index;
                }
                result[queryArray[i].index] = set.areConnected(queryArray[i].start, queryArray[i].end);
            }

            return result;
        }
    }
}
