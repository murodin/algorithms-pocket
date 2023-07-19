public class MinimumNumberOfMovesToMakePalindrome {
    /*
        You are given a string s consisting only of lowercase English letters.
        In one move, you can select any two adjacent characters of s and swap them.
        Return the minimum number of moves needed to make s a palindrome.
        Note that the input will be generated such that s can always be converted to a palindrome.

        Example 1:

        Input: s = "aabb"
        Output: 2
        Explanation:
        We can obtain two palindromes from s, "abba" and "baab".
        - We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
        - We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
        Thus, the minimum number of moves needed to make s a palindrome is 2.
        Example 2:

        Input: s = "letelt"
        Output: 2
        Explanation:
        One of the palindromes we can obtain from s in 2 moves is "lettel".
        One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
        Other palindromes such as "tleelt" can also be obtained in 2 moves.
        It can be shown that it is not possible to obtain a palindrome in less than 2 moves.


        Constraints:

        1 <= s.length <= 2000
        s consists only of lowercase English letters.
        s can be converted to a palindrome using a finite number of moves.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minMovesToMakePalindrome("letelt"));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int minMovesToMakePalindrome(String s) {
            int ans = 0;
            int start = 0 , end = s.length() - 1;
            char ch[] = s.toCharArray();

            while(start < end) {
                int r = end;
                if(ch[start] == ch[end]) {
                    start++;
                    end--;
                    continue;
                }

                while(ch[start] != ch[r]) {
                    r--;
                }
                if(start == r) {
                    swap(ch,r,r+1);
                    ans++;
                } else {
                    while(r<end) {
                        swap(ch,r,r+1);
                        ans++;
                        r++;
                    }
                }
            }
            return ans;
        }

        private static void swap(char ch[], int i, int j) {
            char c = ch[i];
            ch[i] = ch[j];
            ch[j] = c;

        }
    }
}
