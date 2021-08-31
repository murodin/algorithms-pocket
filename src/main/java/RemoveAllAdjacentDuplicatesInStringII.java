import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInStringII {
    /*
        You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them,
        causing the left and the right side of the deleted substring to concatenate together.
        We repeatedly make k duplicate removals on s until we no longer can.
        Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

        Example 1.

        Input: s = "abcd", k = 2
        Output: "abcd"
        Explanation: There's nothing to delete.

        Example 2.

        Input: s = "deeedbbcccbdaa", k = 3
        Output: "aa"
        Explanation:
        First delete "eee" and "ccc", get "ddbbbdaa"
        Then delete "bbb", get "dddaa"
        Finally delete "ddd", get "aa"
     */
    public static void main(String[] args) {
        String testStr = "deeedbbcccbdaa";
        System.out.println("removeDuplicatesI: " + Solution.removeDuplicatesI(testStr, 3));
        System.out.println("removeDuplicatesII: " + Solution.removeDuplicatesII(testStr, 3));
        System.out.println("removeDuplicatesIII: " + Solution.removeDuplicatesIII(testStr, 3));
    }

    static class Solution {
        // Time: O(N*K)
        // Space: O(N)
        public static String removeDuplicatesI(String s, int k) {
            Stack<Character> main = new Stack<>();

            for(char c: s.toCharArray()){
                Stack<Character> temp = new Stack<>();
                temp.push(c);

                while(!main.isEmpty() && main.peek()==c){
                    temp.push(main.pop());
                }

                if(temp.size()!=k){
                    while(!temp.isEmpty()){
                        main.push(temp.pop());
                    }
                }
            }

            StringBuilder sb= new StringBuilder();

            while(!main.isEmpty()){
                sb.append(main.pop());
            }

            return sb.reverse().toString();
        }

        // Time: O(N)
        // Space: O(N)
        public static String removeDuplicatesII(String s, int k) {
            Stack<int[]> main = new Stack<>();

            for(char c: s.toCharArray()){
                if(!main.isEmpty() && main.peek()[0] == c){
                    main.peek()[1]++;
                }
                else{
                    main.push(new int[]{c,1});
                }

                if(main.peek()[1]==k){
                    main.pop();
                }
            }

            StringBuilder sb= new StringBuilder();

            while(!main.isEmpty()){
                int[] top = main.pop();

                while(top[1]-->0)
                    sb.append((char) top[0]);
            }

            return sb.reverse().toString();
        }

        // Time: O(N)
        // Space: O(N)
        public static String removeDuplicatesIII(String s, int k) {
            int count = 1;
            for(int i=1;i<s.length();i++){
                if(s.charAt(i)==s.charAt(i-1)){
                    count++;
                }
                else{
                    count=1;
                }

                if(count==k){
                    String reduced = s.substring(0,i-k+1) + s.substring(i+1);
                    return removeDuplicatesIII(reduced,k);
                }
            }

            return s;
        }
    }

}
