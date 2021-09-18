import java.util.ArrayList;
import java.util.List;

public class WordSubsets {

    /*
        You are given two string arrays words1 and words2.
        A string b is a subset of string a if every letter in b occurs in a including multiplicity.
        For example, "wrr" is a subset of "warrior" but is not a subset of "world".
        A string a from words1 is universal if for every string b in words2, b is a subset of a.
        Return an array of all the universal strings in words1. You may return the answer in any order.

        Example 1.
        Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
        Output: ["facebook","google","leetcode"]
        Example 2.
        Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
        Output: ["apple","google","leetcode"]
        Example 3.
        Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","oo"]
        Output: ["facebook","google"]
        Example 4.
        Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lo","eo"]
        Output: ["google","leetcode"]
        Example 5.
        Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["ec","oc","ceo"]
        Output: ["facebook","leetcode"]
     */

    public static void main(String[] args) {
        String[] words1 = {"amazon","apple","facebook","google","leetcode"}, words2 = {"e","o"};
        System.out.println("Substrings found: " + Solution.wordSubsets(words1, words2));
    }

    // Time: O(A + B)
    // Space: O(Len(A))
    static class Solution {
        public static List<String> wordSubsets(String[] A, String[] B) {
            List<String> result = new ArrayList<>();

            int[] target = new int[26];

            for(String b:B){
                int[] temp=new int[26];

                for(char c:b.toCharArray()){
                    temp[c-'a']++;
                    target[c-'a'] = Math.max(target[c-'a'],temp[c-'a']);
                }
            }

            for(String a:A) {
                int[] arr=new int[26];

                for(char c:a.toCharArray()) arr[c-'a']++;

                if(subset(arr,target)){
                    result.add(a);
                }
            }

            return result;
        }

        private static boolean subset(int[] source,int[] dest){
            for(int i=0;i<26;i++){
                if(dest[i]>source[i]) return false;
            }
            return true;
        }
    }
}
