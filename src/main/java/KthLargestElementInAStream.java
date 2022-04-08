import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    /*
        Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
        Implement KthLargest class:

        KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
        int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.

        Example 1.
        Input
        ["KthLargest", "add", "add", "add", "add", "add"]
        [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
        Output
        [null, 4, 5, 5, 8, 8]

        Explanation
        KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
        kthLargest.add(3);   // return 4
        kthLargest.add(5);   // return 5
        kthLargest.add(10);  // return 5
        kthLargest.add(9);   // return 8
        kthLargest.add(4);   // return 8

        Constraints:

        1 <= k <= 104
        0 <= nums.length <= 104
        -104 <= nums[i] <= 104
        -104 <= val <= 104
        At most 104 calls will be made to add.
        It is guaranteed that there will be at least k elements in the array when you search for the kth element.
     */

    public static void main(String[] args) {
        KthLargest k = new KthLargest(3, new int[] {4, 5, 8, 2});
        System.out.println("Add 3: " + k.add(3));
        System.out.println("Add 5: " + k.add(5));
        System.out.println("Add 10: " + k.add(10));
        System.out.println("Add 9: " + k.add(9));
        System.out.println("Add 4: " + k.add(4));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class KthLargest {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int k = 0;
        public KthLargest(int k, int[] nums) {
            this.k=k;
            for(int i:nums){
                pq.add(i);
            }
        }
        public int add(int val) {
            pq.add(val);
            while(pq.size() > k){
                pq.poll();
            }
            return pq.peek();
        }
    }
}
