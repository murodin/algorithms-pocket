public class CheckIfTwoStringArraysAreEquivalent {

    /*
        Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.

        A string is represented by an array if the array elements concatenated in order forms the string.

        Example 1.
        Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
        Output: true
        Explanation:
        word1 represents string "ab" + "c" -> "abc"
        word2 represents string "a" + "bc" -> "abc"
        The strings are the same, so return true.
        Example 2.
        Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
        Output: false
        Example 3.
        Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
        Output: true

        Constraints.
            1 <= word1.length, word2.length <= 103
            1 <= word1[i].length, word2[i].length <= 103
            1 <= sum(word1[i].length), sum(word2[i].length) <= 103
            word1[i] and word2[i] consist of lowercase letters.
     */

    public static void main(String[] args) {
        String[] word1 = {"abc", "d", "defg"};
        String[] word2 = {"abcddefg"};

        System.out.println("Solution I: " + Solution_I.arrayStringsAreEqual(word1, word2));
        System.out.println("Solution II: " + Solution_II.arrayStringsAreEqual(word1, word2));
    }

    // Time: O(Max(M,N)), where M: len(word1) and N: len(word2)
    // Space: O(K), where K: len(string)
    static class Solution_I {
        public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            String s1=makeString(word1);
            String s2=makeString(word2);

            return s1.equals(s2);
        }

        static String makeString(String[] word){
            StringBuilder sb=new StringBuilder();

            for(String s:word){
                sb.append(s);
            }

            return sb.toString();
        }
    }

    // Time: O(M+N), where M: len(word1) and N: len(word2)
    // Space: O(1)
    static class Solution_II {
        public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
            int arrindex1 = 0, arrindex2 = 0;
            int id1 = 0, id2 = 0;

            while(arrindex1<word1.length && arrindex2<word2.length){
                if(word1[arrindex1].charAt(id1) != word2[arrindex2].charAt(id2)){
                    return false;
                }
                id1++;
                id2++;

                if(id1 == word1[arrindex1].length()){
                    id1=0;
                    arrindex1++;
                }

                if(id2 == word2[arrindex2].length()){
                    id2=0;
                    arrindex2++;
                }
            }

            return arrindex1 == word1.length && arrindex2 == word2.length;
        }
    }
}
