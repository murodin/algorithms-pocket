import java.util.Arrays;

public class OrderlyQueue {
    /*
        You are given a string s and an integer k. You can choose one of the first k letters of s and append it at the end of the string..
        Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.

        Example 1.
        Input: s = "cba", k = 1
        Output: "acb"
        Explanation:
        In the first move, we move the 1st character 'c' to the end, obtaining the string "bac".
        In the second move, we move the 1st character 'b' to the end, obtaining the final result "acb".
        Example 2.
        Input: s = "baaca", k = 3
        Output: "aaabc"
        Explanation:
        In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
        In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".


        Constraints:

        1 <= k <= s.length <= 1000
        s consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.orderlyQueue("baaca", 3));
    }

    // Time: O(NLog(N))
    // Space: O(N)
    static class Solution {
        public static String orderlyQueue(String s, int k) {
            if(k>1){
                char[] tempArray = s.toCharArray();
                Arrays.sort(tempArray);
                return new String(tempArray);
            } else {
                String ans  = s;
                for(int i=0;i< s.length(); i++){
                    s = s.substring(1) + s.charAt(0);
                    if(ans.compareTo(s) >0){
                        ans = s;
                    }
                }
                return ans;
            }
        }
    }
}
