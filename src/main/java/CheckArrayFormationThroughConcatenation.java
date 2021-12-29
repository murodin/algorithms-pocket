import java.util.HashMap;
import java.util.Map;

public class CheckArrayFormationThroughConcatenation {

    /*
        You are given an array of distinct integers arr and an array of integer arrays pieces,
        where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].

        Return true if it is possible to form the array arr from pieces. Otherwise, return false.

        Example 1.
        Input: arr = [15,88], pieces = [[88],[15]]
        Output: true
        Explanation: Concatenate [15] then [88]
        Example 2.
        Input: arr = [49,18,16], pieces = [[16,18,49]]
        Output: false
        Explanation: Even though the numbers match, we cannot reorder pieces[0].
        Example 3.
        Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
        Output: true
        Explanation: Concatenate [91] then [4,64] then [78]


        Constraints:

            1 <= pieces.length <= arr.length <= 100
            sum(pieces[i].length) == arr.length
            1 <= pieces[i].length <= arr.length
            1 <= arr[i], pieces[i][j] <= 100
            The integers in arr are distinct.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.canFormArray(new int[]{15,88}, new int[][]{{88},{15}}));
        System.out.println("Solution II: " + Solution_II.canFormArray(new int[]{15,88}, new int[][]{{88},{15}}));
    }

    //Approach 1
    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static boolean canFormArray(int[] arr, int[][] pieces) {
            Map<Integer, int[]> map = new HashMap<>();
            for(int[] n : pieces) {
                map.put(n[0], n);
            }
            int i = 0;
            for(; i < arr.length; i++) {
                if(map.containsKey(arr[i])) {
                    int[] piece = map.get(arr[i]);
                    for(int n : piece){
                        if(arr[i] == n) i++;
                        else return false;
                    }
                }
                else {
                    return false;
                }
                i--;
            }
            return true;
        }
    }

    //Approach 2
    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static boolean canFormArray(int[] arr, int[][] pieces) {
            int n = arr.length;
            int[] ind = new int[101];
            for(int i = 0; i < arr.length; i++) {
                ind[arr[i]] = i+1;
            }

            for(int[] piece : pieces) {
                if(ind[piece[0]] == 0) return false;
                for(int i = 1; i < piece.length; i++) {
                    if(ind[piece[i]] - ind[piece[i-1]] != 1) return false;
                }
            }

            return true;
        }
    }
}
