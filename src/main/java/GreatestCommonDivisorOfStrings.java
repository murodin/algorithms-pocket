public class GreatestCommonDivisorOfStrings {
    /*
        For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
        Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

        Example 1.
        Input: str1 = "ABCABC", str2 = "ABC"
        Output: "ABC"
        Example 2.
        Input: str1 = "ABABAB", str2 = "ABAB"
        Output: "AB"
        Example 3.
        Input: str1 = "LEET", str2 = "CODE"
        Output: ""

        Constraints:

        1 <= str1.length, str2.length <= 1000
        str1 and str2 consist of English uppercase letters.
     */
    public static void main(String[] args) {
        System.out.println("Solutino: " + Solution.gcdOfStrings("ABCABC", "ABC"));
    }

    // Time:O(N)
    // Space: O(1)
    static class Solution {
        public static String gcdOfStrings(String str1, String str2) {
            if (!(str1+str2).equals(str2+str1))  return "";
            int len1=str1.length();
            int len2=str2.length();
            int gcd=1;
            int min=Math.min(len1,len2);
            for(int i=2;i<=min;i++){
                if(len1%i==0 &&len2%i==0){
                    gcd=i;
                }
            }
            return str2.substring(0,gcd);
        }
    }
}
