import java.util.*;

public class EvaluateDivision {
    /*
        You are given an array of variable pairs equations and an array of real numbers values,
        where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
        You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
        Return the answers to all queries. If a single answer cannot be determined, return -1.0.
        Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

        Example 1.
        Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
        Explanation:
        Given: a / b = 2.0, b / c = 3.0
        queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
        return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
        Example 2.
        Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        Output: [3.75000,0.40000,5.00000,0.20000]
        Example 3.
        Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
        Output: [0.50000,2.00000,-1.00000,-1.00000]


        Constraints:

        1 <= equations.length <= 20
        equations[i].length == 2
        1 <= Ai.length, Bi.length <= 5
        values.length == equations.length
        0.0 < values[i] <= 20.0
        1 <= queries.length <= 20
        queries[i].length == 2
        1 <= Cj.length, Dj.length <= 5
        Ai, Bi, Cj, Dj consist of lower case English letters and digits.
     */

    public static void main(String[] args) {
        List<List<String>> equations = List.of(
                List.of("a","b"), List.of("b", "c")
        );
        List<List<String>> queries = List.of(
                List.of("a","c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x")
        );
        double[] values = {2.0,3.0};

        System.out.println("Solution I:" + Arrays.toString(Solution_I.calcEquation(equations, values, queries)));
        System.out.println("Solution II:" + Arrays.toString(Solution_II.calcEquation(equations, values, queries)));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        static public double[] calcEquation(List<List<String>> equations, double[] values,
                                     List<List<String>> queries) {

            double[] ans = new double[queries.size()];
            // graph.get(A).get(B) := A / B
            Map<String, Map<String, Double>> graph = new HashMap<>();

            // construct the graph
            for (int i = 0; i < equations.size(); ++i) {
                final String A = equations.get(i).get(0);
                final String B = equations.get(i).get(1);
                graph.putIfAbsent(A, new HashMap<>());
                graph.putIfAbsent(B, new HashMap<>());
                graph.get(A).put(B, values[i]);
                graph.get(B).put(A, 1.0 / values[i]);
            }

            for (int i = 0; i < queries.size(); ++i) {
                final String A = queries.get(i).get(0);
                final String C = queries.get(i).get(1);
                if (!graph.containsKey(A) || !graph.containsKey(C))
                    ans[i] = -1.0;
                else
                    ans[i] = divide(graph, A, C, new HashSet<>());
            }

            return ans;
        }

        // return A / C
        private static double divide(Map<String, Map<String, Double>> graph, final String A, final String C,
                                     Set<String> seen) {
            if (A.equals(C))
                return 1.0;

            seen.add(A);

            for (final String B : graph.get(A).keySet()) {
                if (seen.contains(B))
                    continue;
                final double res = divide(graph, B, C, seen); // B / C
                if (res > 0)                                  // valid result
                    return graph.get(A).get(B) * res;           // A / C = (A / B) * (B / C)
            }

            return -1.0; // invalid result
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
       static class node{
            String d;
            double v;
            node(String d , double v)
            {
                this.d = d;
                this.v = v;
            }
        }
        static double result;
        static int count;
        public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
            double[] ans = new double[queries.size()];
            HashMap<String , ArrayList<node>> adj = new HashMap<>();
            String x , y;

            for(int i=0 ; i<equations.size() ; i++){
                x = equations.get(i).get(0);
                y = equations.get(i).get(1);

                if(!adj.containsKey(x))
                    adj.put(x , new ArrayList<>());
                if(!adj.containsKey(y))
                    adj.put(y , new ArrayList<>());

                adj.get(x).add(new node(y , values[i]));
                adj.get(y).add(new node(x , 1.0/values[i]));
            }

            HashSet<String> visited = new HashSet<>();
            for(int i=0 ; i<ans.length ; i++){
                visited = new HashSet<>();
                x = queries.get(i).get(0);
                y = queries.get(i).get(1);

                if(!adj.containsKey(x))
                    ans[i] = -1.0;
                else{
                    result = -1.0;
                    count = 0;
                    util(adj , visited , x , y, 1.0);

                    if(count<=1)   // If Single ans can be determined i.e. we can reach y in 1 or less ways
                        ans[i] = result;
                    else     // If there are multiple ways to reach  y then single ans can not be determined and so we will put -1.0
                        ans[i] = -1.0;
                }
            }
            return ans;
        }

        static void util(HashMap<String , ArrayList<node>> adj ,HashSet<String> visited, String s , String d, double mul) {
            if(s.equals(d)){
                count++;
                result = mul;
            }
            visited.add(s);
            for(node x : adj.get(s)){
                if(!visited.contains(x.d))
                    util(adj, visited, x.d, d, mul*x.v);
            }
        }
    }
}
