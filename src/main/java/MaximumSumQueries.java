import java.util.Arrays;

public class MaximumSumQueries {
    /*
        You are given two 0-indexed integer arrays nums1 and nums2, each of length n, and a 1-indexed 2D array queries where queries[i] = [xi, yi].
        For the ith query, find the maximum value of nums1[j] + nums2[j] among all indices j (0 <= j < n), where nums1[j] >= xi and nums2[j] >= yi,
        or -1 if there is no j satisfying the constraints.

        Return an array answer where answer[i] is the answer to the ith query.

        Example 1:

        Input: nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
        Output: [6,10,7]
        Explanation:
        For the 1st query xi = 4 and yi = 1, we can select index j = 0 since nums1[j] >= 4 and nums2[j] >= 1.
        The sum nums1[j] + nums2[j] is 6, and we can show that 6 is the maximum we can obtain.

        For the 2nd query xi = 1 and yi = 3, we can select index j = 2 since nums1[j] >= 1 and nums2[j] >= 3.
        The sum nums1[j] + nums2[j] is 10, and we can show that 10 is the maximum we can obtain.

        For the 3rd query xi = 2 and yi = 5, we can select index j = 3 since nums1[j] >= 2 and nums2[j] >= 5.
        The sum nums1[j] + nums2[j] is 7, and we can show that 7 is the maximum we can obtain.

        Therefore, we return [6,10,7].
        Example 2:

        Input: nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
        Output: [9,9,9]
        Explanation: For this example, we can use index j = 2 for all the queries since it satisfies the constraints for each query.
        Example 3:

        Input: nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
        Output: [-1]
        Explanation: There is one query in this example with xi = 3 and yi = 3. For every index, j, either nums1[j] < xi or nums2[j] < yi. Hence, there is no solution.


        Constraints:

        nums1.length == nums2.length
        n == nums1.length
        1 <= n <= 105
        1 <= nums1[i], nums2[i] <= 109
        1 <= queries.length <= 105
        queries[i].length == 2
        xi == queries[i][1]
        yi == queries[i][2]
        1 <= xi, yi <= 109
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class Node {
        int start, end;
        int val;
        Node left;
        Node right;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.val = Integer.MIN_VALUE;
        }
    }

    class SegmentTree {
        private Node root;
        public SegmentTree() {
            this.root = new Node(1, 1000000000);
        }

        public void pointUpdate(int point, int val) {
            this.pointUpdateHelper(this.root, 1, 1000000000, point, val);
        }

        public int rangeMax(int left) {
            return this.rangeMaxHelper(this.root, 1, 1000000000, left, 1000000000);
        }

        private void pointUpdateHelper(Node curr, int left, int right, int point, int val) {
            if(left == right && left == point) {
                curr.val = Math.max(curr.val, val);
                return;
            }

            if(point < left || point > right)
                return;

            int mid = (left + right)/2;
            Node leftC = getOrCreateLeftChild(curr, left, mid);
            Node rightC = getOrCreateRightChild(curr, mid + 1, right);
            pointUpdateHelper(leftC, left, mid, point, val);
            pointUpdateHelper(rightC, mid + 1, right, point, val);
            curr.val = Math.max(leftC.val, rightC.val);
        }

        private int rangeMaxHelper(Node curr, int left, int right, int l, int r) {
            if(l > right || left > r)
                return Integer.MIN_VALUE;

            if(left >= l && right <= r)
                return curr.val;

            int mid = (left + right)/2;
            Node leftC = getOrCreateLeftChild(curr, left, mid);
            Node rightC = getOrCreateRightChild(curr, mid + 1, right);
            return Math.max(rangeMaxHelper(leftC, left, mid, l, r), rangeMaxHelper(rightC, mid + 1, right, l, r));
        }

        private Node getOrCreateLeftChild(Node curr, int start, int end) {
            if(curr.left != null)
                return curr.left;

            curr.left = new Node(start, end);
            return curr.left;
        }

        private Node getOrCreateRightChild(Node curr, int start, int end) {
            if(curr.right != null)
                return curr.right;

            curr.right = new Node(start, end);
            return curr.right;
        }
    }

    class Solution {
        public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
            int n = nums1.length;
            int m = queries.length;
            int[] res = new int[m];
            int[][] q = new int[m][3];
            int[][] tuples = new int[n][2];

            for(int i = 0; i<m; i++) {
                q[i][0] = queries[i][0];
                q[i][1] = queries[i][1];
                q[i][2] = i;
            }

            for(int i = 0; i<n; i++) {
                tuples[i][0] = nums1[i];
                tuples[i][1] = nums2[i];
            }

            Arrays.sort(q, (a, b) -> a[0] - b[0]);
            Arrays.sort(tuples, (a,b) -> a[0] - b[0]);

            // Segment tree for range max
            SegmentTree seg = new SegmentTree();

            int i = n-1, j = m-1;
            while(j >= 0) {
                while(i >= 0 && tuples[i][0] >= q[j][0]) {
                    seg.pointUpdate(tuples[i][1], tuples[i][0] + tuples[i][1]);
                    i--;
                }

                int max = seg.rangeMax(q[j][1]);
                max = max == Integer.MIN_VALUE? -1: max;
                res[q[j][2]] = max;
                j--;
            }

            return res;
        }
    }
}
