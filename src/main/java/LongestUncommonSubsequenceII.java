public class LongestUncommonSubsequenceII {
    /*
        Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.
        An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
        A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
        For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc".
        Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).

        Example 1.
        Input: strs = ["aba","cdc","eae"]
        Output: 3
        Example 2.
        Input: strs = ["aaa","aaa","aa"]
        Output: -1

        Constraints:

        2 <= strs.length <= 50
        1 <= strs[i].length <= 10
        strs[i] consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findLUSlength(new String[] {"aba","cdc","eae"}));
    }

    // Time: O(MxN), where M and N is length of strings.
    // Space: O(1)
    static class Solution {
        public static int findLUSlength(String[] strs) {
            int maxLen = -1;
            for(int i = 0; i < strs.length ; i++){
                boolean flag = false ;
                int currLen = strs[i].length() ;
                for(int j = 0 ; j<strs.length; j++) {
                    if(i != j && check(strs[i], strs[j])) {
                        flag = true ;
                        break ;
                    }
                }
                if(!flag) {
                    maxLen = Math.max(maxLen , currLen);
                }
            }
            return maxLen ;
        }
        public static boolean check(String str1, String str2){
            int A = str1.length() , B = str2.length() ;
            while(A > 0 && B > 0) {
                int i = str1.length() - A ;
                int j = str2.length() - B ;
                if(str1.charAt(i) == str2.charAt(j)) {
                    A-- ;
                }
                B-- ;
            }
            return A == 0;
        }
    }
}
