
public class SuperPalindromes {

    /*
        Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
        Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].

        Example.1
        Input: left = "4", right = "1000"
        Output: 4
        Explanation: 4, 9, 121, and 484 are super palindromes.
        Note that 676 is not a super palindrome: 26 * 26 = 676, but 26 is not a palindrome.
     */

    public static void main(String[] args) {
        String left = "4", right = "1000";
        System.out.println("Number of super palindromes in range 4 to 1000 is " + Solution.superPalindromesInRange(left, right));
    }

    static class Solution {

        public static int superPalindromesInRange(String left, String right) {
            long l = Long.valueOf(left);
            long r = Long.valueOf(right);
            int count = 0;

            // Create Palindromes in range 1, 100000
            // Even Palindromes
            for(int i = 1; i < 100000; i++) {
                StringBuilder sb = new StringBuilder(Integer.toString(i));
                for(int j = sb.length()-1; j >=0; j--){
                    sb.append(sb.charAt(j));
                }
                long n = Long.valueOf(sb.toString());
                n *= n;

                if(n > r) break;
                else if(n>=l && isPalindrome(n)) count++;
            }

            // Odd Palindromes
            for(int i = 1; i < 100000; i++) {
                StringBuilder sb = new StringBuilder(Integer.toString(i));
                for(int j = sb.length()-2; j >=0; j--){
                    sb.append(sb.charAt(j));
                }
                long n = Long.valueOf(sb.toString());
                n *= n;

                if(n > r) break;
                else if(n>=l && isPalindrome(n)) count++;
            }
            return count;
        }

        private static boolean isPalindrome(long n) {
            return n == reverse(n);
        }

        private static long reverse(long n) {
            long rev = 0;
            while (n > 0) {
                rev = rev*10 + n%10;
                n /= 10;
            }
            return rev;
        }
    }
}
