import java.util.ArrayList;

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    /*
        A string s is called good if there are no two different characters in s that have the same frequency.
        Given a string s, return the minimum number of characters you need to delete to make s good.
        The frequency of a character in a string is the number of times it appears in the string.
        For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

        Example 1.
        Input: s = "aab"
        Output: 0
        Explanation: s is already good.
        Example 2.
        Input: s = "aaabbbcc"
        Output: 2
        Explanation: You can delete two 'b's resulting in the good string "aaabcc".
        Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
        Example 3.
        Input: s = "ceabaacb"
        Output: 2
        Explanation: You can delete both 'c's resulting in the good string "eabaab".
        Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).


        Constraints:

        1 <= s.length <= 105
        s contains only lowercase English letters.
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minDeletions("ceabaacb"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int minDeletions(String s) {
            int[] alp=new int[26];

            // store the frequency of each character
            for(int i=0;i<s.length();i++) {
                alp[s.charAt(i)-'a']++;
            }
            int ans=0;

            //is used to make frequency is unique
            ArrayList<Integer> arr=new ArrayList<>();
            for(int i=0;i<26;i++) {
                if(alp[i]!=0){  //if alp[i]==0 then number of character is 0
                    if(!arr.contains(alp[i])) {   // if it is come first time then just add it
                        arr.add(alp[i]);
                    } else {    //it comes previously ,(we need to make it unique
                        int t=alp[i];
                        while(arr.contains(t) && t>0) {    // we decrement t until it become unique or 0
                            t--;
                            ans++;
                        }
                        arr.add(t);
                    }
                }
            }
            return ans;
        }
    }
}
