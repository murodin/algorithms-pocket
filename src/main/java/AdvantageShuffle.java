import java.util.*;

public class AdvantageShuffle {

    /*
        You are given two integer arrays nums1 and nums2 both of the same length.
        The advantage of nums1 with respect to nums2 is the number of indices i for which nums1[i] > nums2[i].
        Return any permutation of nums1 that maximizes its advantage with respect to nums2.

        Example 1.
        Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11]
        Output: [2,11,7,15]
        Example 2.
        Input: nums1 = [12,24,8,32], nums2 = [13,25,32,11]
        Output: [24,32,8,12]
     */

    public static void main(String[] args) {
        int[] nums1 = {2,7,11,15}, nums2 = {1,10,4,11};
        System.out.println("Solution: " + Arrays.toString(Solution.advantageCount(nums1, nums2)));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int[] advantageCount(int[] A, int[] B) {
            int n = A.length;
            //To stores original indexes of B
            Map<Integer, Queue<Integer>> indexes = new HashMap<>();
            for(int i = 0; i < n; i++){
                indexes.putIfAbsent(B[i], new LinkedList<>());
                indexes.get(B[i]).add(i);
            }

            //Sort the array
            Arrays.sort(A);
            Arrays.sort(B);

            int i = 0, j = 0;
            int[] result = new int[n];
            Arrays.fill(result, -1);

            //Ordering A[i] where possible or adding unused numbers in Queue
            Queue<Integer> unusedNums = new LinkedList<>();
            while(i < n && j < n){
                if(A[i] > B[j]) {
                    int ind = indexes.get(B[j]).poll();
                    result[ind]=A[i];
                    j++;
                } else {
                    unusedNums.add(A[i]);
                }
                i++;
            }

            //Fill the -1's with unusedNums
            for(int in = 0; in < n; in++) {
                if(result[in] == -1){
                    result[in] = unusedNums.poll();
                }
            }

            return result;
        }
    }
}
