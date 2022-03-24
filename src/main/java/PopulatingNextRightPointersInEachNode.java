import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode {

    /*
        Given a binary tree

        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }
        Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

        Initially, all next pointers are set to NULL.

        Example 1.
        Input: root = [1,2,3,4,5,null,7]
        Output: [1,#,2,3,#,4,5,7,#]
        Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
        The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
        Example 2.
        Input: root = []
        Output: []

        Constraints:

        The number of nodes in the tree is in the range [0, 6000].
        -100 <= Node.val <= 100

        Follow-up:

        You may only use constant extra space.
        The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
     */

    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static Node connect(Node root) {
            if (root == null) return null;

            Queue<Node> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                int size = q.size();
                Node dummy = new Node(0);
                while (size-- > 0) {
                    Node node = q.remove();
                    dummy.next = node;
                    dummy = dummy.next;

                    if (node.left != null) q.add(node.left);

                    if (node.right != null) q.add(node.right);
                }
            }

            return root;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static Node connect(Node root) {
            if(root == null) return null;

            Node head = root;

            while (head != null) {
                Node dummy = new Node(0);
                Node temp = dummy;

                while (head != null) {
                    if(head.left != null) {
                        temp.next = head.left;
                        temp = temp.next;
                    }

                    if(head.right != null) {
                        temp.next = head.right;
                        temp = temp.next;
                    }

                    head = head.next;
                }
                head = dummy.next;
            }
            return root;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static Node connect(Node root) {
            if(root == null || root.right == null || root.left == null) return root;
            root.left.next = root.right;
            if(root.next != null) {
                root.right.next = root.left.next;
            }
            connect(root.right);
            connect(root.left);
            return root;
        }
    }
}
