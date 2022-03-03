import java.util.ArrayList;

public class LinkedListRandomNode {

    /*
        Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
        Implement the Solution class:
        Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
        int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be chosen.

        Example 1.
        Input
        ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
        [[[1, 2, 3]], [], [], [], [], []]
        Output
        [null, 1, 3, 2, 2, 3]

        Explanation
        Solution solution = new Solution([1, 2, 3]);
        solution.getRandom(); // return 1
        solution.getRandom(); // return 3
        solution.getRandom(); // return 2
        solution.getRandom(); // return 2
        solution.getRandom(); // return 3
        // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.

        Constraints:

        The number of nodes in the linked list will be in the range [1, 104].
        -104 <= Node.val <= 104
        At most 104 calls will be made to getRandom.

        Follow up:

        What if the linked list is extremely large and its length is unknown to you?
        Could you solve this efficiently without using extra space?
     */

    public static void main(String[] args) {
        System.out.println("Done..");
    }

    /*
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(head);
     * int param_1 = obj.getRandom();
     */

    /*
     * Definition for singly-linked list.
     */
     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    // Space: O(N)
    class Solution_I {
        ArrayList<Integer> arr = new ArrayList<>();

        // Time: O(N)
        public Solution_I(ListNode head) {
            while (head != null) {
                arr.add(head.val);
                head = head.next;
            }
        }

        // Time: O(1)
        public int getRandom() {
            return arr.get((int) (Math.random() * arr.size()));
        }
    }

    // Reservoir Sampling
    // Space: O(1)
    class Solution_II {
        ListNode head = null;

        // Time: O(1)
        public Solution_II(ListNode head) {
            this.head = head;
        }

        // Time: O(N)
        public int getRandom() {
            ListNode curr = head;
            int i = 1;
            int res = 0;

            while (curr != null) {
                if(Math.random() < 1.0/i) {
                    res = curr.val;
                }
                curr = curr.next;
                i++;
            }
            return res;
        }
    }
}

