public class CountTheRepetitions {
    /*
        We define str = [s, n] as the string str which consists of the string s concatenated n times.
        For example, str == ["abc", 3] =="abcabcabc".
        We define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1.
        For example, s1 = "abc" can be obtained from s2 = "abdbec" based on our definition by removing the bolded underlined characters.
        You are given two strings s1 and s2 and two integers n1 and n2. You have the two strings str1 = [s1, n1] and str2 = [s2, n2].

        Return the maximum integer m such that str = [str2, m] can be obtained from str1.

        Example 1:
        Input: s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
        Output: 2
        Example 2:
        Input: s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
        Output: 1


        Constraints:

        1 <= s1.length, s2.length <= 100
        s1 and s2 consist of lowercase English letters.
        1 <= n1, n2 <= 106
     */
    public static void main(String[] args) {
        System.out.println(Solution.getMaxRepetitions( "acb", 4,  "ab", 2));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
            int[] next = new int[s2.length() + 1];
            int[] count = new int[s2.length() + 1];
            int j = 0, cnt = 0;
            for (int k = 1; k <= n1; k++) {
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        j++;
                        if (j == s2.length()) {
                            j = 0;
                            cnt++;
                        }
                    }
                }
                count[k] = cnt;
                next[k] = j;
                for (int start = 0; start < k; start++) {
                    if (next[start] == j) {
                        int prefixCount = count[start];
                        int patternCount = (count[k] - count[start]) * ((n1 - start) / (k - start));
                        int suffixCount = count[start + (n1 - start) % (k - start)] - count[start];
                        return (prefixCount + patternCount + suffixCount) / n2;
                    }
                }
            }
            return count[n1] / n2;
        }
    }
}
