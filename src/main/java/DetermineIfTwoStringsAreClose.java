import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetermineIfTwoStringsAreClose {

    /*
        Two strings are considered close if you can attain one from the other using the following operations:
        Operation 1: Swap any two existing characters.
        For example, abcde -> aecdb
        Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
        For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
        You can use the operations on either string as many times as necessary.

        Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.


        Example 1.
        Input: word1 = "abc", word2 = "bca"
        Output: true
        Explanation: You can attain word2 from word1 in 2 operations.
        Apply Operation 1: "abc" -> "acb"
        Apply Operation 1: "acb" -> "bca"
        Example 2.
        Input: word1 = "a", word2 = "aa"
        Output: false
        Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
        Example 3.
        Input: word1 = "cabbba", word2 = "abbccc"
        Output: true
        Explanation: You can attain word2 from word1 in 3 operations.
        Apply Operation 1: "cabbba" -> "caabbb"
        Apply Operation 2: "caabbb" -> "baaccc"
        Apply Operation 2: "baaccc" -> "abbccc"
        Example 4.
        Input: word1 = "cabbba", word2 = "aabbss"
        Output: false
        Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.
     */

    public static void main(String[] args) {
        System.out.println("Solution I : " + Solution_I.closeStrings("abc", "bca"));
        System.out.println("Solution II: " + Solution_II.closeStrings("abc", "bca"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static boolean closeStrings(String word1, String word2) {
            if(word1.length()!=word2.length()) return false;

            int[] w1=new int[26];
            int[] w2=new int[26];

            for(char c:word1.toCharArray()){
                w1[c-'a']++;
            }

            for(char c:word2.toCharArray()){
                w2[c-'a']++;
            }

            //Step1:
            for(int i=0;i<26;i++){
                if((w1[i]==0 && w2[i]!=0) || (w1[i]!=0 && w2[i]==0)){
                    return false;
                }
            }

            //Step 2:
            Map<Integer,Integer> map=new HashMap<>();

            for(int i:w1){
                if(i>0){
                    map.put(i,map.getOrDefault(i,0)+1);
                }
            }

            for(int i:w2){
                if(i>0){
                    if(!map.containsKey(i)){
                        return false;
                    }

                    map.put(i,map.get(i)-1);
                    if(map.get(i)==0){
                        map.remove(i);
                    }
                }
            }

            return map.size()==0;
        }
    }

    // Time: O(NLogN ~ N)
    // Space: O(1)
    static class Solution_II {
        public static boolean closeStrings(String word1, String word2) {
            if(word1.length()!=word2.length()) return false;

            int[] w1=new int[26];
            int[] w2=new int[26];

            for(char c:word1.toCharArray()){
                w1[c-'a']++;
            }

            for(char c:word2.toCharArray()){
                w2[c-'a']++;
            }

            //Step1:
            for(int i=0;i<26;i++){
                if((w1[i]==0 && w2[i]!=0) || (w1[i]!=0 && w2[i]==0)){
                    return false;
                }
            }

            //Step 2:
            Arrays.sort(w1);
            Arrays.sort(w2);

            return Arrays.equals(w1,w2);
        }
    }
}
