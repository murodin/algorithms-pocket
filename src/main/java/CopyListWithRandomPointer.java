import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    /*
        A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

        Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
        where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

        For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
        Return the head of the copied linked list.
        The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
        val: an integer representing Node.val
        random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
        Your code will only be given the head of the original linked list.
        Example 1.
        Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Example 2.
        Input: head = [[1,1],[2,1]]
        Output: [[1,1],[2,1]]
        Example 3.
        Input: head = [[3,null],[3,0],[3,null]]
        Output: [[3,null],[3,0],[3,null]]
        Example 4.
        Input: head = []
        Output: []
        Explanation: The given linked list is empty (null pointer), so return null.
     */

    public static void main(String[] args) {
        System.out.println("Worked...");
    }


    // Definition for a Node.
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static Node copyRandomList(Node head) {
            if(head==null) return null;

            Map<Node,Node> map=new HashMap<>();

            Node curr=head;

            while(curr!=null){
                map.put(curr, new Node(curr.val));
                curr=curr.next;
            }

            for(Node key:map.keySet()){
                Node newNode=map.get(key);
                newNode.next=map.get(key.next);
                newNode.random=map.get(key.random);
            }

            return map.get(head);
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static Node copyRandomList(Node head) {
            if(head==null) return null;

            Node temp=head;

            while(temp!=null){
                Node next=temp.next;
                Node random=temp.random;
                temp.next=new Node(temp.val, next, random);
                temp=temp.next.next;
            }

            //Update the random
            Node newHead=head.next;
            temp=head;

            while(temp!=null){
                Node next=temp.next;
                if(next.random!=null) next.random=next.random.next;
                temp=next.next;
            }

            //Unwinding the list
            temp=head;

            while(temp!=null){
                Node copy=temp.next;
                temp.next=copy.next;
                if(copy.next!=null) copy.next=temp.next.next;
                temp=temp.next;
            }

            return newHead;
        }
    }
}
