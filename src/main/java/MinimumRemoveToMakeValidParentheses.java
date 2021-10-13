public class MinimumRemoveToMakeValidParentheses {

    /*
        Given a string s of '(' , ')' and lowercase English characters.
        Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
        Formally, a parentheses string is valid if and only if:
        It is the empty string, contains only lowercase characters, or
        It can be written as AB (A concatenated with B), where A and B are valid strings, or
        It can be written as (A), where A is a valid string.

        Example 1.
        Input: s = "lee(t(c)o)de)"
        Output: "lee(t(c)o)de"
        Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
        Example 2.
        Input: s = "a)b(c)d"
        Output: "ab(c)d"
        Example 3.
        Input: s = "))(("
        Output: ""
        Explanation: An empty string is also valid.
        Example 4.
        Input: s = "(a(b(c)d)"
        Output: "a(b(c)d)"
     */

    public static void main(String[] args) {
        System.out.println("Min Remove :" + Solution.minRemoveToMakeValid("a)b(c)d(e"));
    }

    // Time: O(len(s))
    // Space: O(len(s))
    static class Solution {
        public static String minRemoveToMakeValid(String s) {
            char[] ch=s.toCharArray();

            //left to right
            //)---> (
            int count=0;

            for(int i=0;i<ch.length;i++){
                if(ch[i]=='('){
                    count++;
                } else if(ch[i]==')'){
                    if(count>0){
                        count--;
                    } else{
                        ch[i]=0;
                    }
                }
            }

            //right->left
            // ( ----> )
            count=0;
            for(int i=ch.length-1;i>=0;i--){
                if(ch[i]==')'){
                    count++;
                } else if(ch[i]=='('){
                    if(count>0){
                        count--;
                    } else{
                        ch[i]=0;
                    }
                }
            }

            StringBuilder sb=new StringBuilder();
            for(char c:ch){
                if(c!=0){
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }
}
