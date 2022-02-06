import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    /*
        Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
        A palindrome string is a string that reads the same backward as forward.

        Example 1.
        Input: s = "aab"
        Output: [["a","a","b"],["aa","b"]]
        Example 2.
        Input: s = "a"
        Output: [["a"]]

        Constraints:

        1 <= s.length <= 16
        s contains only lowercase English letters.
     */

    public static void main(String[] args) {
        String test = "aab";
        System.out.println("Palindrome Partitioning: " + Solution.partition(test));
    }

    // Time: O(N*2^N)
    // Space: O(N)
    static class Solution {
        static List<List<String>> result = new ArrayList<>();
        public static List<List<String>> partition(String s) {
            dfs(s, 0, new ArrayList<>());
            return result;
        }
        private static void dfs(String s, int start, List<String> list) {
            if(s.length() == start) {
                result.add(new ArrayList<>(list));
                return;
            }

            for(int i=start; i<s.length(); i++) {
                if(isPalindrome(s, start, i)) {
                    list.add(s.substring(start, i+1));
                    dfs(s, i+1, list);
                    list.remove(list.size()-1);
                }
            }
        }

        private static boolean isPalindrome(String s, int start, int end) {
            while (start<end) {
                if(s.charAt(start) != s.charAt(end)) return false;
                start++;
                end--;
            }
            return true;
        }
    }
}
