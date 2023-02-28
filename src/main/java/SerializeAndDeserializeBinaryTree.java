import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SerializeAndDeserializeBinaryTree {
    /*
        Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
        or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
        Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
        You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
        Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format,
        so please be creative and come up with different approaches yourself.

        Example 1.
        Input: root = [1,2,3,null,null,4,5]
        Output: [1,2,3,null,null,4,5]
        Example 2.
        Input: root = []
        Output: []


        Constraints:

        The number of nodes in the tree is in the range [0, 104].
        -1000 <= Node.val <= 1000
     */
    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        // Codec ser = new Codec();
        // Codec deser = new Codec();
        // TreeNode ans = deser.deserialize(ser.serialize(root));
        System.out.println("Done.");
    }

    /**
     * Definition for a binary tree node.
     * */
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    public class Codec {
        private static final String spliter = ",";
        private static final String NN = "X";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NN).append(spliter);
            } else {
                sb.append(node.val).append(spliter);
                buildString(node.left, sb);
                buildString(node.right,sb);
            }
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>(Arrays.asList(data.split(spliter)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            String val = nodes.remove();
            if (val.equals(NN)) return null;
            else {
                TreeNode node = new TreeNode(Integer.parseInt(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }
}
