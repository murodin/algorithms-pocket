import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeKSortedLists {

    /*
        You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
        Merge all the linked-lists into one sorted linked-list and return it.

        Example 1.
        Input: lists = [[1,4,5],[1,3,4],[2,6]]
        Output: [1,1,2,3,4,4,5,6]
        Explanation: The linked-lists are:
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        merging them into one sorted list:
        1->1->2->3->4->4->5->6
        Example 2.
        Input: lists = []
        Output: []
        Example 3.
        Input: lists = [[]]
        Output: []
     */

    public static void main(String[] args) {
        System.out.println("Hello World...");
    }


    /*
     * Definition for singly-linked list.
     */
     public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
         public static ListNode mergeKLists(ListNode[] lists) {
            if(lists==null || lists.length==0) return null;

            ListNode head=new ListNode(0);
            ListNode temp=head;
            List<Integer> l=new ArrayList<>();

            for(ListNode list:lists){
                while(list!=null){
                    l.add(list.val);
                    list=list.next;
                }
            }
            Collections.sort(l);
            for(int val:l){
                temp.next=new ListNode(val);
                temp=temp.next;
            }
            return head.next;
        }
    }

    // Time: O(NK)
    // Space: O(1)
    static class Solution_II {
        public static ListNode mergeKLists(ListNode[] lists) {
            if(lists==null || lists.length==0) return null;

            ListNode head=new ListNode(0);
            ListNode temp=head;

            while(true){
                int p=0;
                for(int i=0;i<lists.length;i++){
                    if(lists[p]==null || (lists[i]!=null && lists[p].val>lists[i].val)){
                        p=i;
                    }
                }

                if(lists[p]==null){
                    break;
                }
                temp.next=new ListNode(lists[p].val);
                temp=temp.next;
                lists[p]=lists[p].next;
            }

            return head.next;
        }
    }

    // Time: O(NLogK)
    // Space: O(1)
    static class Solution_III {
        public static ListNode mergeKLists(ListNode[] lists) {
            if(lists==null || lists.length==0) return null;

            int interval=1;
            while(interval<lists.length){
                for(int i=0;i+interval<lists.length;i=i+interval*2){
                    lists[i]=mergeTwoLists(lists[i],lists[i+interval]);
                }
                interval*=2;
            }
            return lists[0];
        }

        static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1==null) return l2;
            if(l2==null) return l1;

            ListNode newHead=new ListNode(l1.val);
            ListNode temp=newHead;
            while(l2!=null && l1!=null){
                if(l1.val<=l2.val){
                    temp.next=new ListNode(l1.val);
                    l1=l1.next;
                }else{
                    temp.next=new ListNode(l2.val);
                    l2=l2.next;
                }
                temp=temp.next;
            }

            if(l1!=null){
                temp.next=l1;
            }
            else{
                temp.next=l2;
            }
            return newHead.next;
        }
    }
}
