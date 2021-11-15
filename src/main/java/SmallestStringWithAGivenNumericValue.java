public class SmallestStringWithAGivenNumericValue {

    /*
        The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet, so the numeric value of a is 1,
        the numeric value of b is 2, the numeric value of c is 3, and so on.

        The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values.
        For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.

        You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.

        Note that a string x is lexicographically smaller than string y
        if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.


        Example 1.
        Input: n = 3, k = 27
        Output: "aay"
        Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
        Example 2.
        Input: n = 5, k = 73
        Output: "aaszz"
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.getSmallestString(3, 27));
        System.out.println("Solution II: " + Solution_II.getSmallestString(3, 27));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static String getSmallestString(int n, int k) {
            char[] c=new char[n];

            for(int i=n-1;i>=0;i--){
                int val= Math.min(26,k - i);
                c[i] = (char)('a'+val-1);
                k=k-val;

            }

            return new String(c);
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static String getSmallestString(int n, int k) {
            k=k-n;
            int zcount=k/25;
            int value = k%25;

            char[] c=new char[n];
            int i=n-1;
            while(zcount-->0){
                c[i--]='z';
            }
            if(value>0)
                c[i--]=(char)('a'+value);

            while(i>=0){
                c[i--]='a';
            }
            return new String(c);
        }
    }
}
