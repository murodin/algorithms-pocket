import java.util.ArrayList;
import java.util.List;

public class LongestChunkedPalindromeDecomposition {
    /*
        You are given a string text. You should split it to k substrings (subtext1, subtext2, ..., subtextk) such that:
        subtexti is a non-empty string.
        The concatenation of all the substrings is equal to text (i.e., subtext1 + subtext2 + ... + subtextk == text).
        subtexti == subtextk - i + 1 for all valid values of i (i.e., 1 <= i <= k).
        Return the largest possible value of k.

        Example 1.
        Input: text = "ghiabcdefhelloadamhelloabcdefghi"
        Output: 7
        Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
        Example 2.
        Input: text = "merchant"
        Output: 1
        Explanation: We can split the string on "(merchant)".
        Example 3.
        Input: text = "antaprezatepzapreanta"
        Output: 11
        Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".


        Constraints:

        1 <= text.length <= 1000
        text consists only of lowercase English characters.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
        System.out.println("Solution II: " + Solution_II.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static int longestDecomposition(String text) {
            int n = text.length();
            for (int i = 0; i < n/2; i++)
                if (text.substring(0, i + 1).equals(text.substring(n-1-i, n)))
                    return 2+longestDecomposition(text.substring(i+1, n-1-i));
            return (n==0)?0:1;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        private static void findAllSubstrings(String text, List<String> list) {
            if (text == null || text.length() == 0) {
                return;
            }
            StringBuilder front = new StringBuilder();
            StringBuilder back = new StringBuilder();
            int i=0, j=text.length()-1;

            while (i<text.length() && j>i) {
                front.append(text.charAt(i));
                back.insert(0, text.charAt(j));

                if (front.compareTo(back) == 0) {
                    list.add(front.toString());
                    list.add(back.toString());
                    findAllSubstrings(text.substring(i+1, j), list);
                    return;
                }

                i++;j--;
            }

            list.add(text);
        }

        public static int longestDecomposition(String text) {
            List<String> list = new ArrayList();
            findAllSubstrings(text, list);
            return list.size();
        }
    }
}
