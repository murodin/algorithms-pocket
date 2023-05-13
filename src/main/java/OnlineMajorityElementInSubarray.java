import java.util.*;

public class OnlineMajorityElementInSubarray {
    /*
        Design a data structure that efficiently finds the majority element of a given subarray.

        The majority element of a subarray is an element that occurs threshold times or more in the subarray.

        Implementing the MajorityChecker class:

        MajorityChecker(int[] arr) Initializes the instance of the class with the given array arr.
        int query(int left, int right, int threshold) returns the element in the subarray arr[left...right] that occurs at least threshold times,
        or -1 if no such element exists.


        Example 1:

        Input
        ["MajorityChecker", "query", "query", "query"]
        [[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
        Output
        [null, 1, -1, 2]

        Explanation
        MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
        majorityChecker.query(0, 5, 4); // return 1
        majorityChecker.query(0, 3, 3); // return -1
        majorityChecker.query(2, 3, 2); // return 2


        Constraints:

        1 <= arr.length <= 2 * 104
        1 <= arr[i] <= 2 * 104
        0 <= left <= right < arr.length
        threshold <= right - left + 1
        2 * threshold > right - left + 1
        At most 104 calls will be made to query.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time
    /*
        Initialization: O(N)
        Query: O(logN)
     */
    // Space: O(N)
    class MajorityChecker {
        private int[] nums;
        private SegmentTreeNode treeRoot;
        private Map<Integer, List<Integer>> idxMap;

        public MajorityChecker(int[] nums) {
            this.nums = nums;
            this.idxMap = new HashMap<>();
            this.treeRoot = buildTree(0, nums.length - 1, nums);

            for (int i = 0; i < nums.length; ++i) {
                List<Integer> idxList = idxMap.get(nums[i]);
                if (idxList == null) {
                    idxList = new ArrayList<>();
                    idxMap.put(nums[i], idxList);
                }
                idxList.add(i);
            }
        }

        public int query(int left, int right, int threshold) {
            Pair cand = queryTree(treeRoot, left, right);
            if (cand.freq >= threshold) {
                return cand.val;
            }

            int leftIdx = Collections.binarySearch(idxMap.get(cand.val), left);
            if (leftIdx < 0) {
                leftIdx = -leftIdx - 1;
            }

            int rightIdx = Collections.binarySearch(idxMap.get(cand.val), right);
            if (rightIdx < 0) {
                rightIdx = -rightIdx - 2;
            }

            if (rightIdx - leftIdx + 1 >= threshold) {
                return cand.val;
            }

            return -1;
        }

        private SegmentTreeNode buildTree(int start, int end, int[] nums) {
            if (start == end) {
                return new SegmentTreeNode(start, end, new Pair(nums[start], 1));
            }

            SegmentTreeNode root = new SegmentTreeNode(start, end, null);

            int mid = start + (end - start) / 2;
            root.left = buildTree(start, mid, nums);
            root.right = buildTree(mid + 1, end, nums);
            root.pair = mergePair(root.left.pair, root.right.pair);

            return root;
        }

        private Pair queryTree(SegmentTreeNode root, int start, int end) {
            if (start <= root.start && root.end <= end) {
                return root.pair;
            }

            Pair res = new Pair(0, 0);
            int mid = root.start + (root.end - root.start) / 2;
            if (start <= mid) {
                res = mergePair(res, queryTree(root.left, start, end));
            }
            if (mid < end) {
                res = mergePair(res, queryTree(root.right, start, end));
            }

            return res;
        }

        private Pair mergePair(Pair pair1, Pair pair2) {
            if (pair1.val == pair2.val) {
                return new Pair(pair1.val, pair1.freq + pair2.freq);
            }

            if (pair1.freq > pair2.freq) {
                return new Pair(pair1.val, pair1.freq - pair2.freq);
            }

            return new Pair(pair2.val, pair2.freq - pair1.freq);
        }

        class SegmentTreeNode {
            int start;
            int end;
            Pair pair;
            SegmentTreeNode left;
            SegmentTreeNode right;

            SegmentTreeNode(int start, int end, Pair pair) {
                this.start = start;
                this.end = end;
                this.pair = pair;
                this.left = null;
                this.right = null;
            }
        }

        class Pair {
            int val;
            int freq;

            Pair(int val, int freq) {
                this.val = val;
                this.freq = freq;
            }
        }
    }
}
