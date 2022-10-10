public class BreakAPalindrome {
    /*
        Given a palindromic string of lowercase English letters palindrome,
        replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and
        that it is the lexicographically smallest one possible.
        Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.
        A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ,
        a has a character strictly smaller than the corresponding character in b.
        For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.

        Example 1.
        Input: palindrome = "abccba"
        Output: "aaccba"
        Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
        Of all the ways, "aaccba" is the lexicographically smallest.
        Example 2.
        Input: palindrome = "a"
        Output: ""
        Explanation: There is no way to replace a single character to make "a" not a palindrome, so return an empty string.


        Constraints:

        1 <= palindrome.length <= 1000
        palindrome consists of only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.breakPalindrome("abccba"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String breakPalindrome(String palindrome) {
            StringBuilder sb = new StringBuilder(palindrome); // Create Stringbuilder as we need setCharAt position operation
            if(palindrome.length()<=1){ // Check for base case where length of string is less than or equals to 1 , if yes then return empty string.
                return "";
            }
            for(int i=0; i<palindrome.length()/2; i++){ // iterate less than half of the array
                char c = palindrome.charAt(i);
                if(c!='a'){ // check if char at i th position is not 'a'
                    sb.setCharAt(i,'a'); // set 'a' at the  i th position.
                    return sb.toString(); // return result
                }
            }
            sb.setCharAt(palindrome.length()-1,'b'); // if 'a' not found in the above iteration then we set 'b' at the end of the string
            // consider test case : "aba"
            // iterate till  0 th index
            // finally append 'b' at the end of the String which will becomes "abb"
            return sb.toString();

        }
    }
}
