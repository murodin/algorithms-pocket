import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    /*
        Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
        An input string is valid if:
        Open brackets must be closed by the same type of brackets.
        Open brackets must be closed in the correct order.

        Example 1.
        Input: s = "()"
        Output: true
        Example 2.
        Input: s = "()[]{}"
        Output: true
        Example 3.
        Input: s = "(]"
        Output: false
        Example 4.
        Input: s = "([)]"
        Output: false
        Example 5.
        Input: s = "{[]}"
        Output: true
     */

    public static void main(String[] args) {
        String testStr = "{[]}";
        System.out.println("Solution I: " + Solution_I.isValid(testStr));
        System.out.println("Solution II: " + Solution_II.isValid(testStr));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        static Map<Character, Character> map;
        public static boolean isValid(String s) {
            map = new HashMap<>();
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');
            Stack<Character> st=new Stack<>();
            for(int i=0;i<s.length();i++) {
                char c = s.charAt(i);
                if(map.containsValue(c)) {
                    st.push(c);
                } else {
                    if(st.isEmpty() ||  map.get(c) != st.pop()) {
                        return false;
                    }
                }
            }
            return st.isEmpty();
        }
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution_II {
        static Map<Character,Character> map=new HashMap<>();
        public static boolean isValid(String s) {
            map.put('}','{');
            map.put(')','(');
            map.put(']','[');

            //Stack<Character> st=new Stack<>();
            int top=-1;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);

                if(map.containsValue(c)){
                    //st.push(c);
                    top=i;
                } else{
                    if(top==-1 || map.get(c)!=s.charAt(top)){
                        return false;
                    } else{
                        top=getTop(s,top-1);
                    }
                }
            }

            return top==-1;
        }

        static int getTop(String s,int index){
            int right=0;

            while(index>=0){
                char ch=s.charAt(index);
                // ch = is closing bracket
                if(map.containsKey(ch)) {
                    right++;
                } else{
                    right--;
                }

                if(right<0){
                    return index;
                }
                index--;
            }

            return -1;
        }
    }
}
