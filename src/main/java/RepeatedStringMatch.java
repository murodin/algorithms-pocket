public class RepeatedStringMatch {
    /*
        Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it.
        If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.
        Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".

        Example 1.
        Input: a = "abcd", b = "cdabcdab"
        Output: 3
        Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
        Example 2.
        Input: a = "a", b = "aa"
        Output: 2


        Constraints:

        1 <= a.length, b.length <= 104
        a and b consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.repeatedStringMatch("abcd", "cdabcdab"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int repeatedStringMatch(String a, String b) {
            StringBuilder copy = new StringBuilder();
            int count=0;
            while(copy.length()<b.length()){
                copy.append(a);
                count++;
            }
            if(copy.toString().contains(b))
                return count;
            if((copy + a).contains(b))
                return ++count;
            return -1;
        }
    }
}
