import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    /*
        You are given an integer array nums, and you have to return a new counts array.
        The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

        Example 1.
        Input: nums = [5,2,6,1]
        Output: [2,1,1,0]
        Explanation:
        To the right of 5 there are 2 smaller elements (2 and 1).
        To the right of 2 there is only 1 smaller element (1).
        To the right of 6 there is 1 smaller element (1).
        To the right of 1 there is 0 smaller element.
        Example 2.
        Input: nums = [-1]
        Output: [0]
        Example 3.
        Input: nums = [-1,-1]
        Output: [0,0]


        Constraints:

        1 <= nums.length <= 105
        -104 <= nums[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countSmaller(new int[] {5,2,6,1}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        private static class TreeNode {
            int value;
            TreeNode left;
            TreeNode right;
            int countOfVisitedToLeft;
            int countOfVisitHere;

            public TreeNode(int value, TreeNode left, TreeNode right) {
                this.value = value;
                this.left = left;
                this.right = right;
                countOfVisitedToLeft = 0;
                countOfVisitHere = 0;
            }
        }

        public static List<Integer> countSmaller(int[] nums) {
            LinkedList<Integer> result = new LinkedList<>(); // n space for result
            int[] sorted = Arrays.copyOf(nums, nums.length); //n space for copy
            Arrays.sort(sorted); // n log n for sort
            TreeNode root = createBinarySearchTree(sorted); // n for create tree, log n space on stack, n space on tree
            for (int i=nums.length-1; i>=0; i--) { //n
                result.addFirst(findAndAddForLeft(root,nums[i],0));//log n for find
            }
            return result;
        }

        private static int findAndAddForLeft(TreeNode node, int value, int sum) {
            if (node.value == value) {
                node.countOfVisitHere++;
                return node.countOfVisitedToLeft+sum;
            } else if (value > node.value) {
                //search right
                return findAndAddForLeft(node.right, value, sum+node.countOfVisitedToLeft+node.countOfVisitHere);
            } else {
                //search left, but first add to countOfVisitedToLeft
                node.countOfVisitedToLeft++;
                return findAndAddForLeft(node.left, value, sum);
            }

        }

        private static TreeNode createBinarySearchTree(int[] array) {
            int k = (array.length-1)/2;
            return new TreeNode(array[k],
                    createBinarySearchTreeHelper(array,0,k-1, array[k], true),//left
                    createBinarySearchTreeHelper(array,k+1,array.length-1, array[k], false));
        }

        private static TreeNode createBinarySearchTreeHelper(int[] array, int left, int right, int value, boolean isLeft) {
            if (left > right) {
                return null;
            }
            int k = (left+right)/2;
            if (array[k] == value) {
                //value is same as parent, dont add a new node, instead skip all nodes between and continue to next node;
                if (isLeft) {
                    return createBinarySearchTreeHelper(array, left,k-1, value, true);
                } else {
                    return createBinarySearchTreeHelper(array,k+1,right, value, false);
                }
            } else {
                return new TreeNode(array[k],
                        createBinarySearchTreeHelper(array,left,k-1,array[k], true),//left
                        createBinarySearchTreeHelper(array,k+1,right,array[k], false));
            }
        }

    }
}
