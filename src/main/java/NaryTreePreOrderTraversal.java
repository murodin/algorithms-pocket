import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NaryTreePreOrderTraversal {

    /*
        Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
        Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)

        Example.
        Input: root = [1,null,3,2,4,null,5,6]
        Output: [1,3,5,6,2,4]

     */
    public static void main(String[] args) {
        Node testRoot = new Node(12);
        System.out.println("Serial Nary PreOrder:" + Solution.preOrder(testRoot));
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    static class Solution {
        static List<Integer> result = new ArrayList<>();
        public static List<Integer> preOrder(Node root) {
            if(root == null) return result;
            preOrderHelper(root);
            return result;
        }

        public static void preOrderHelper(Node node) {
            if(node.children == null) return;
            result.add(node.val);
            for(Node child : node.children) {
                preOrderHelper(child);
            }
        }

        List<Integer> result2 = new ArrayList<>();
        public List<Integer> preOrder_II(Node root) {
            if(root == null) return result;

            Stack<Node> stack = new Stack<>();
            stack.push(root);

            while(!stack.isEmpty()) {
                Node curr = stack.pop();
                result2.add(curr.val);
                //Pushing in children in reverse order
                for(int i = curr.children.size()-1; i >= 0; i--) {
                    stack.push(curr.children.get(i));
                }
            }

            return result2;
        }
    }
}
