import java.util.List;
import java.util.stream.Collectors;

public class LongestWordInDictionaryThroughDeleting {
    /*
        Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters.
        If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

        Example 1.
        Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
        Output: "apple"
        Example 2.
        Input: s = "abpcplea", dictionary = ["a","b","c"]
        Output: "a"
     */

    public static void main(String[] args) {
        List<String> dict = List.of("ale","apple","monkey","plea")
                .stream()
                .collect(Collectors.toList()); // To make a List.of(...) immutable List<?> to mutable List<?> (ArrayList<?>)
        String test = "abpcplea";
        System.out.println("Solution I: " + Solution_I.findLongestWord(test, dict));
        System.out.println("Solution I: " + Solution_II.findLongestWord(test, dict));
    }

    // Time: O(NXLogN + NX) where N: Len(s), X: Len(s)
    // Space: O(1)
    static class Solution_I {
        public static String findLongestWord(String s, List<String> d) {
            String result = "";
            d.sort((String a, String b) ->
                    a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            for(String str : d) {
                if(isSubsequence(s, str)) return result = str;
            }
            return result;
        }

        public static boolean isSubsequence(String S, String D) {
            int i = 0, j = 0;
            if(D.length() > S.length()) return false;
            while(i < S.length() && j < D.length()) {
                if(S.charAt(i) == D.charAt(j)) j++;
                i++;
            }
            return j == D.length();
        }
    }

    // Time: O(NX) where N: Len(s), X: Len(s)
    // Space: O(1)
    static class Solution_II {
        public static String findLongestWord(String s, List<String> d) {
            String result = "";

            for(String str : d) {
                if(isSubsequence(s, str) ) {
                    //Length str > result || length is equal but str is lexicographically small.
                    if(str.length() > result.length() || (str.length() == result.length() && str.compareTo(result) < 1))
                        result = str;
                }
            }
            return result;
        }

        public static boolean isSubsequence(String S, String D) {
            int i = 0, j = 0;
            if(D.length() > S.length()) return false;
            while(i < S.length() && j < D.length()) {
                if(S.charAt(i) == D.charAt(j)) j++;
                i++;
            }
            return j == D.length();
        }
    }
}
