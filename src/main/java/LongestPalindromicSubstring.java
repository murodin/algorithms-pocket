public class LongestPalindromicSubstring {

    /*
        Given a string s, return the longest palindromic substring in s.

        Example 1.
        Input: s = "babad"
        Output: "bab"
        Note: "aba" is also a valid answer.
        Example 2.
        Input: s = "cbbd"
        Output: "bb"
        Example 3.
        Input: s = "a"
        Output: "a"
        Example 4.
        Input: s = "ac"
        Output: "a"
     */

    public static void main(String[] args) {
        System.out.println("Longest Palindromic Substring: " + Solution.longestPalindrome("babad"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String longestPalindrome(String s) {
            int n=s.length();
            char[] newStr=new char[2*n+1];
            int i=0;
            newStr[i++]='#';
            for(char c:s.toCharArray()){
                newStr[i++]=c;
                newStr[i++]='#';
            }

            int p[]=new int[2*n+1];
            int center=0,right=0;

            int longestLength=0,longestCenter=0;

            for(i=0;i<newStr.length;i++){

                int mirror= 2*center-i;

                if(right>i){
                    p[i]=Math.min(p[mirror],right-i);
                }

                int a=i + (p[i]+1);
                int b=i - (p[i]+1);

                while(b>=0 && a<newStr.length && newStr[a]==newStr[b]){
                    b--;
                    a++;
                    p[i]++;
                }

                if(p[i]>=longestLength){
                    longestCenter=i;
                    longestLength=p[i];
                }

                if(i+p[i]>right){
                    center=i;
                    right=i+p[i];
                }
            }

            String st=new String(newStr);
            return st.substring(longestCenter-longestLength,longestCenter+longestLength).replace("#","");
        }
    }
}
