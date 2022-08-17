public class FirstUniqueCharacterInAString {
    /*
        Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

        Example 1.
        Input: s = "leetcode"
        Output: 0
        Example 2.
        Input: s = "loveleetcode"
        Output: 2
        Example 3.
        Input: s = "aabb"
        Output: -1

        Constraints:

        1 <= s.length <= 105
        s consists of only lowercase English letters
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.firstUniqChar("loveleetcode"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int firstUniqChar(String s) {
            // Stores the lowest index / first index
            int ans = Integer.MAX_VALUE;
            // Iterate from a to z which is 26 which makes it constant
            for(char c='a'; c<='z';c++){
                // indexOf will return first index of alphabet and lastIndexOf will return last index
                // if both are equal then it has occurred only once.
                // through this we will get all index's which are occurred once
                // but our answer is the lowest index
                int index = s.indexOf(c);
                if(index!=-1&&index==s.lastIndexOf(c)){
                    ans = Math.min(ans,index);
                }
            }
            // If and remains Integer.MAX_VALUE then there is no unique character
            return ans==Integer.MAX_VALUE?-1:ans;
        }
    }
}
