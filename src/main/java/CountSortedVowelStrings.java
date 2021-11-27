public class CountSortedVowelStrings {

    /*
        Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
        A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

        Example 1.
        Input: n = 1
        Output: 5
        Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
        Example 2.
        Input: n = 2
        Output: 15
        Explanation: The 15 sorted strings that consist of vowels only are
        ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
        Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
        Example 3.
        Input: n = 33
        Output: 66045
     */


    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.countVowelStrings(5));
        System.out.println("Solution II: " + Solution_II.countVowelStrings(5));
        System.out.println("Solution III: " + Solution_III.countVowelStrings(5));
    }

    // Time: O(5^N)
    // Space: O(N)
    static class Solution_I {
        static char[] ch=new char[]{'a','e','i','o','u'};
        public static int countVowelStrings(int n) {
            int ans=0;
            for(char c:ch){
                ans+=count(n-1,c);
            }

            return ans;
        }

        static int count(int length,char first){
            if(length==0){
                return 1;
            }
            int ans=0;
            for(char c:ch){
                if(first>=c){
                    ans+=count(length-1,c);
                }
            }

            return ans;
        }
    }

    // Time: O(5N)
    // Space: O(6N)
    static class Solution_II {
        public static int countVowelStrings(int n) {
            int[][] dp=new int[n+1][6];

            for(int i=1;i<=n;i++){
                for(int j=1;j<=5;j++){
                    dp[i][j]=dp[i][j-1]+(i>1?dp[i-1][j]:1);
                }
            }

            return dp[n][5];
        }
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution_III {
        public static int countVowelStrings(int n) {
            return (n+4)*(n+3)*(n+2)*(n+1)/24;
        }
    }
}
