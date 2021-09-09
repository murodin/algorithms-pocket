import java.util.Set;

public class DetermineIfStringHalvesAreAlike {

    /*
        You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
        Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
        Return true if a and b are alike. Otherwise, return false.

        Example 1.
        Input: s = "book"
        Output: true
        Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.

        Example 2.
        Input: s = "textbook"
        Output: false
        Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
        Notice that the vowel o is counted twice.

        Example 3.
        Input: s = "MerryChristmas"
        Output: false

        Example 4.
        Input: s = "AbCdEfGh"
        Output: true
     */

    public static void main(String[] args) {
        String testString = "textbook";
        String testString_II = "AbCdEfGh";

        System.out.println("Halves are like: " + Solution.halvesAreAlike(testString));
        System.out.println("Halves are like: " + Solution.halvesAreAlike(testString_II));
    }

    static class Solution {
        // Time: O(N)
        // Space: O(N)
        public static boolean halvesAreAlike(String s) {
            char[] ch = s.toCharArray();
            int left=0,right=ch.length-1;

            int lcount=0, rcount=0;
            Set vowels=Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

            while(left<right){
                lcount += vowels.contains(ch[left]) ? 1:0;
                rcount += vowels.contains(ch[right]) ? 1:0;
                left++;
                right--;
            }

            return lcount == rcount;
        }
    }
}
