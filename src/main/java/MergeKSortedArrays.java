import java.util.Arrays;

public class MergeKSortedArrays {

    /*
            Merge sorted arrays in sorted array.

            Example 1.
            int[][] test = [[1,3], [0,3,11], [2,5,6], [4,7]]
            Output: [1,2,3,4,5,6,7]
     */

    public static void main(String[] args) {
        int[][] test = {{1,3}, {0,3,11}, {2,5,6}, {4,7}};
        System.out.println("Solution I - Sorted Array: "+ Arrays.toString(Solution_I.mergeSorted(test)));
        System.out.println("Solution II - Sorted Array: "+ Arrays.toString(Solution_I.mergeSorted(test)));
    }

    // Time: O(K), K where total Array size in Array
    // Space: O(K)
    static class Solution_I {
        static public int[] mergeSorted(int[][] arr) {
            int[] result = new int[0];
            for (int[] ints : arr) {
                result = mergeHelper(result, ints);
            }
            return result;
        }

        private static int[] mergeHelper(int[] a, int[] b) {
            int i = a.length;
            int j = b.length;

            int[] merged = new int[i + j];

            int iPosition, jPosition, mergedPosition;
            iPosition = jPosition = mergedPosition = 0;

            while(iPosition < i && jPosition < j) {
                if (a[iPosition] < b[jPosition]) {
                    merged[mergedPosition++] = a[iPosition++];
                } else {
                    merged[mergedPosition++] = b[jPosition++];
                }
            }

            while (iPosition < i) {
                merged[mergedPosition++] = a[iPosition++];
            }

            while (jPosition < j) {
                merged[mergedPosition++] = b[jPosition++];
            }

            return merged;
        }
    }

    // Time: O(K*Max(Arr[i])), K where total Array size in Array
    // Space: O(1)
    static class Solution_II {
        static public int[] mergeSorted(int[][] arr) {
            for (int i=1; i<arr.length; i++) {
                mergeHelper(arr[0], arr[0].length, arr[i], arr[i].length);
            }
            return arr[0];
        }

        private static void mergeHelper(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m-1, p2 = n-1, i = m+n-1;
            while(p2 >= 0){
                if(p1 >= 0 && nums1[p1] > nums2[p2]){

                    nums1[i--] = nums1[p1--];
                }
                else {
                    nums1[i--] = nums2[p2--];
                }
            }
        }
    }
}
