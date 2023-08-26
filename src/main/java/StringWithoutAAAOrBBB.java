public class StringWithoutAAAOrBBB {
    /*
        Given two integers a and b, return any string s such that:

        s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
        The substring 'aaa' does not occur in s, and
        The substring 'bbb' does not occur in s.


        Example 1:

        Input: a = 1, b = 2
        Output: "abb"
        Explanation: "abb", "bab" and "bba" are all correct answers.
        Example 2:

        Input: a = 4, b = 1
        Output: "aabaa"


        Constraints:

        0 <= a, b <= 100
        It is guaranteed such an s exists for the given a and b.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.strWithout3a3b(1,2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String strWithout3a3b(int A, int B) {
            StringBuilder res = new StringBuilder(A + B);
            char a = 'a', b = 'b';
            int i = A, j = B;
            if (B > A) {
                a = 'b';
                b = 'a';
                i = B;
                j = A;
            }
            while (i-- > 0) {
                res.append(a);
                if (i > j) {
                    res.append(a);
                    --i;
                }
                if (j-- > 0) res.append(b);
            }
            return res.toString();
        }

    }
}
