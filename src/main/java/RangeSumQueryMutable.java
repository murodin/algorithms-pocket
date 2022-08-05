public class RangeSumQueryMutable {
    /*
        Given an integer array nums, handle multiple queries of the following types:
        Update the value of an element in nums.
        Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
        Implement the NumArray class:
        NumArray(int[] nums) Initializes the object with the integer array nums.
        void update(int index, int val) Updates the value of nums[index] to be val.
        int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


        Example 1.
        Input
        ["NumArray", "sumRange", "update", "sumRange"]
        [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
        Output
        [null, 9, null, 8]

        Explanation
        NumArray numArray = new NumArray([1, 3, 5]);
        numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1, 2, 5]
        numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8


        Constraints:

        1 <= nums.length <= 3 * 104
        -100 <= nums[i] <= 100
        0 <= index < nums.length
        -100 <= val <= 100
        0 <= left <= right < nums.length
        At most 3 * 104 calls will be made to update and sumRange.
     */

    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time:
    // Space:
    static class Maths {
        public static int min(int x, int y){
            return x < y ? x : y;
        }

        public static int max(int x, int y){
            return x > y ? x : y;
        }
    }

    class SegmentTree {
        int size;
        int[] tree;

        SegmentTree(int[] nums){
            this.size = nums.length;
            this.tree = new int [4 * this.size];
        }

        public void buildTree(int root, int[] nums, int tl, int tr){
            if(tl == tr){
                tree[root] = nums[tl];
                return;
            }

            int mid = (tl + tr) / 2;
            buildTree(2 * root + 1, nums, tl, mid);
            buildTree(2 * root + 2, nums, mid + 1, tr);

            tree[root] = tree[2 * root + 1] + tree[2 * root + 2];
        }

        public void updateValue(int root, int tl, int tr, int index, int value){
            if(tl == tr){
                tree[root] = value;
                return;
            }

            int mid = (tl + tr) / 2;
            if(index <= mid){
                updateValue(2 * root + 1, tl, mid, index, value);
            }
            else{
                updateValue(2 * root + 2, mid + 1, tr, index, value);
            }

            tree[root] = tree[2 * root + 1] + tree[2 * root + 2];
        }

        public int sumRange(int root, int tl, int tr, int l, int r){
            if(l == tl && r == tr){
                return tree[root];
            }

            if(l > r){
                return 0;
            }

            int mid = (tl + tr) / 2;
            int leftSubtree = sumRange(2 * root + 1, tl, mid, l, Maths.min(mid, r));
            int rightSubtree = sumRange(2 * root + 2, mid + 1, tr, Maths.max(mid + 1, l), r);

            return leftSubtree + rightSubtree;
        }

    }

    class NumArray {
        SegmentTree s;

        public NumArray(int[] nums) {
            s = new SegmentTree(nums);
            s.buildTree(0, nums, 0, nums.length - 1);
        }

        public void update(int index, int value) {
            s.updateValue(0, 0, s.size - 1, index, value);
        }

        public int sumRange(int left, int right) {
            return s.sumRange(0, 0, s.size - 1, left, right);
        }
    }
}
