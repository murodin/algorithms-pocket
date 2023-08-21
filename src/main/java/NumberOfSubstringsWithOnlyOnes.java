public class NumberOfSubstringsWithOnlyOnes {
    /*
        Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.

        Example 1:
        Input: s = "0110111"
        Output: 9
        Explanation: There are 9 substring in total with only 1's characters.
        "1" -> 5 times.
        "11" -> 3 times.
        "111" -> 1 time.
        Example 2:
        Input: s = "101"
        Output: 2
        Explanation: Substring "1" is shown 2 times in s.
        Example 3:
        Input: s = "111111"
        Output: 21
        Explanation: Each substring contains only 1's characters.


        Constraints:

        1 <= s.length <= 105
        s[i] is either '0' or '1'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numSub("0110111"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int numSub(String s) {
            long count = 0 , res = 0 , mod = (int)1e9+7 ;
            for(int i = 0 ; i < s.length() ; i++){
                count += s.charAt(i)-'0';
                if(s.charAt(i) - '0' == 0){
                    res+=((count * (count+1))/2);
                    count = 0 ;
                }
            }
            res += ((count*(count+1))/2);
            return (int)(res%mod);
        }
    }
}
