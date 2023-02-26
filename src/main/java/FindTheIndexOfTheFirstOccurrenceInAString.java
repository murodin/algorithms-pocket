public class FindTheIndexOfTheFirstOccurrenceInAString {
    /*
        Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
        Example 1.
        Input: haystack = "sadbutsad", needle = "sad"
        Output: 0
        Explanation: "sad" occurs at index 0 and 6.
        The first occurrence is at index 0, so we return 0.
        Example 2.
        Input: haystack = "leetcode", needle = "leeto"
        Output: -1
        Explanation: "leeto" did not occur in "leetcode", so we return -1.


        Constraints:

        1 <= haystack.length, needle.length <= 104
        haystack and needle consist of only lowercase English characters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.strStr("sadbutsad", "sad"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int strStr(String haystack, String needle) {
            int nl=needle.length();
            int i=0,j=i+nl;
            if (nl==haystack.length()) {
                if(needle.equals(haystack))
                    return 0;
                return -1;
            } else if(nl>haystack.length())
                return -1;

            while(j<=haystack.length()) {
                if(needle.equals(haystack.substring(i,j)))
                    return i;
                i++;
                j++;
            }
            return -1;
        }
    }
}
