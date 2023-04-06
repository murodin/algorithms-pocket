import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MiniParser {
    /*
        Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.
        Each element is either an integer or a list whose elements may also be integers or other lists.

        Example 1.
        Input: s = "324"
        Output: 324
        Explanation: You should return a NestedInteger object which contains a single integer 324.
        Example 2.
        Input: s = "[123,[456,[789]]]"
        Output: [123,[456,[789]]]
        Explanation: Return a NestedInteger object containing a nested list with 2 elements:
        1. An integer containing value 123.
        2. A nested list containing two elements:
            i.  An integer containing value 456.
            ii. A nested list with one element:
                 a. An integer containing value 789


        Constraints:

        1 <= s.length <= 5 * 104
        s consists of digits, square brackets "[]", negative sign '-', and commas ','.
        s is the serialization of valid NestedInteger.
        All the values in the input are in the range [-106, 106].
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time:
    // Space:
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     */
     public class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        // NestedInteger(int value);
        public NestedInteger(int value) {

        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return null;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }

    class Solution {
        public NestedInteger deserialize(String s) {
            Deque<NestedInteger> stack = new ArrayDeque<>();
            NestedInteger cur = new NestedInteger();
            for (int i = 0; i < s.length(); i++){
                char ch = s.charAt(i);
                if (ch == '['){
                    stack.push(cur);
                    cur = new NestedInteger();
                } else if (ch == '-' || Character.isDigit(ch)){
                    int j = i + 1;
                    while(j < s.length() && Character.isDigit(s.charAt(j))){
                        ++j;
                    }
                    cur.add(parse(i, j, s));
                    i = j - 1;
                } else if (ch == ']'){
                    assert stack.peek() != null;
                    stack.peek().add(cur);
                    cur = stack.pop();
                }
            }
            return cur.getList().get(0);
        }

        private NestedInteger parse(int lo, int hi, String s){
            return new NestedInteger(Integer.parseInt(s.substring(lo, hi)));
        }
    }
}
