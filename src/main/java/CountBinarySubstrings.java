public class CountBinarySubstrings {

    /*
        Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
        Substrings that occur multiple times are counted the number of times they occur.

        Example.
        Input: s = "00110011"
        Output: 6
        Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
        Notice that some of these substrings repeat and are counted the number of times they occur.
        Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

     */
    public static void main(String[] args) {
        String testString = "00110011";
        System.out.println("Result: " + Solution.countBinarySubstrings(testString));
    }

    static class Solution {
        // Time: O(N)
        // Space: O(1)
        public static int countBinarySubstrings(String s) {
            int count = 0, i = 1, prev = 0, curr = 1;
            while(i < s.length()) {
                //11000110
                if(s.charAt(i-1) != s.charAt(i)) {
                    count+=Math.min(prev, curr);
                    prev = curr;
                    curr = 1;
                }
                else {
                    curr++;
                }
                i++;
            }

            return count+=Math.min(prev, curr);
        }
    }
}
