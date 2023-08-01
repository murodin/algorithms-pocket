import java.util.ArrayList;
import java.util.List;

public class Combinations {
    /*
        Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

        You may return the answer in any order.

        Example 1:

        Input: n = 4, k = 2
        Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
        Explanation: There are 4 choose 2 = 6 total combinations.
        Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
        Example 2:

        Input: n = 1, k = 1
        Output: [[1]]
        Explanation: There is 1 choose 1 = 1 total combination.


        Constraints:

        1 <= n <= 20
        1 <= k <= n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.combine(4, 2));
    }

    // Time: O(N)
    // Space: O(N^2)
    static class Solution {
        public static List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> l = new ArrayList<>();
            int[] arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = i+1;
            }
            backTracking(0,res,l,k,arr);
            return res;
        }
        static void backTracking(int i, List<List<Integer>> res, List<Integer> l, int k, int arr[]){
            if( i == arr.length){
                if(l.size() == k){
                    res.add(new ArrayList<>(l));
                }
                return;
            }
            l.add(arr[i]);
            backTracking(i+1,res,l,k,arr);
            l.remove(l.size()-1);
            backTracking(i+1,res,l,k,arr);
        }
    }
}
