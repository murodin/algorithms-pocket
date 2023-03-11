public class ConvertSortedListToBinarySearchTree {
    /*
        Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.

        Example 1.
        Input: head = [-10,-3,0,5,9]
        Output: [0,-3,9,-10,null,5]
        Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
        Example 2.
        Input: head = []
        Output: []


        Constraints:

        The number of nodes in head is in the range [0, 2 * 104].
        -105 <= Node.val <= 105
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Definition for singly-linked list.
     **/
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Definition for a binary tree node.
     **/
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
    }

    class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if(head==null)
                return null;
            if(head.next==null)
                return new TreeNode(head.val);
            ListNode slow=head;
            ListNode fast=head.next.next;
            while(fast!=null && fast.next!=null){
                slow=slow.next;
                fast=fast.next.next;
            }
            TreeNode res=new TreeNode(slow.next.val);
            ListNode righthalf=slow.next.next;
            slow.next=null;
            res.left=sortedListToBST(head);
            res.right=sortedListToBST(righthalf);
            return res;
        }
    }
}
