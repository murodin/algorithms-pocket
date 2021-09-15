import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {

    /*
        Given the head of a singly linked list, return true if it is a palindrome.
        Example 1.
        Input: head = [1,2,2,1]
        Output: true

        Example 2.
        Input: head = [1,2]
        Output: false

     */

    public static void main(String[] args) {
        ListNode node_3 = new ListNode(1);
        ListNode node_2 = new ListNode(2, node_3);
        ListNode node_1 = new ListNode(2, node_2);
        ListNode head = new ListNode(1, node_1);

        System.out.println("is Palindrome: " + Solution.isPalindrome(head));
        System.out.println("is Palindrome: " + Solution_II.isPalindrome(head));
    }

    /* Definition for singly-linked list. */
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static public boolean isPalindrome(ListNode head) {
            if(head == null || head.next == null) return true;
            List<Integer> l = new ArrayList<>();
            while(head != null){
                l.add(head.val);
                head = head.next;
            }
            int start = 0, end = l.size()-1;
            while(start < end){
                if(l.get(start) != l.get(end)) return false;
                start++;end--;
            }
            return true;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        static public boolean isPalindrome(ListNode head) {
            //Find Mid
            ListNode fast = head;
            ListNode slow = head;

            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // 1, ||| 2.  ||||  3 --> slow = 2, fast = 3
            if(fast != null) slow = slow.next;

            //Reverse the list start from slow.
            ListNode revHead = reverse(slow);

            while(revHead != null) {
                if(revHead.val != head.val) return false;
                else {
                    revHead = revHead.next;
                    head = head.next;
                }
            }
            return true;
        }

        static public ListNode reverse(ListNode head) {
            ListNode prev = null;
            while(head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }
    }
}
