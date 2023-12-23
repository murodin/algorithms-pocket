import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextGreaterNodeInLinkedList {
    /*
        You are given the head of a linked list with n nodes.
        For each node in the list, find the value of the next greater node.
        That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
        Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.

        Example 1:
        Input: head = [2,1,5]
        Output: [5,5,0]
        Example 2:
        Input: head = [2,7,4,3,5]
        Output: [7,0,5,5,0]


        Constraints:

        The number of nodes in the list is n.
        1 <= n <= 104
        1 <= Node.val <= 109
     */
    public static void main(String[] args) {
        System.out.println("Done...");
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

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int[] nextLargerNodes(ListNode head) {
            List<Integer> list = new ArrayList<>();
            Stack<Integer> s = new Stack<>();
            ListNode curr = head;
            int count = 0;
            while(curr != null){
                count++;
                list.add(curr.val);
                curr = curr.next;
            }
            int[] ans = new int[count];
            for(int i = list.size()-1; i>=0; i--){
                while(!s.isEmpty() && s.peek()<=list.get(i)){
                    s.pop();
                }
                if(s.isEmpty()){
                    ans[i] = 0;
                } else{
                    ans[i] = s.peek();
                }
                s.push(list.get(i));
            }
            return ans;
        }
    }
}
