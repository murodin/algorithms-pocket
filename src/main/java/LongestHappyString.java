public class LongestHappyString {
    /*
        A string s is called happy if it satisfies the following conditions:

        s only contains the letters 'a', 'b', and 'c'.
        s does not contain any of "aaa", "bbb", or "ccc" as a substring.
        s contains at most a occurrences of the letter 'a'.
        s contains at most b occurrences of the letter 'b'.
        s contains at most c occurrences of the letter 'c'.
        Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings,
        return any of them. If there is no such string, return the empty string "".

        A substring is a contiguous sequence of characters within a string.



        Example 1:

        Input: a = 1, b = 1, c = 7
        Output: "ccaccbcc"
        Explanation: "ccbccacc" would also be a correct answer.
        Example 2:

        Input: a = 7, b = 1, c = 0
        Output: "aabaa"
        Explanation: It is the only correct answer in this case.


        Constraints:

        0 <= a, b, c <= 100
        a + b + c > 0
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestDiverseString(1, 1, 7));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String longestDiverseString(int a, int b, int c) {
            int totalSize = a + b + c;
            int A = 0;
            int B = 0;
            int C = 0;
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<totalSize; i++) {
                if ((a>=b && a>=c && A<2) || (B==2 && a>0) || (C==2 && a>0)) {
                    sb.append("a");
                    a -= 1;
                    A += 1;
                    B = 0;
                    C = 0;
                }
                else if ((b>=a && b>=c && B<2) || (A==2 && b>0) || (C==2 && b>0)) {
                    sb.append("b");
                    b -= 1;
                    B += 1;
                    A = 0;
                    C = 0;
                }
                else if ((c>=a && c>=b && C<2) || (A==2 && c>0) || (B==2 && c>0)) {
                    sb.append("c");
                    c -= 1;
                    C += 1;
                    A = 0;
                    B = 0;
                }
            }
            return sb.toString();
        }
    }
}
