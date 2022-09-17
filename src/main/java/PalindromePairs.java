import java.util.*;

public class PalindromePairs {
    /*
        Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list,
        so that the concatenation of the two words words[i] + words[j] is a palindrome.

        Example 1.
        Input: words = ["abcd","dcba","lls","s","sssll"]
        Output: [[0,1],[1,0],[3,2],[2,4]]
        Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
        Example 2.
        Input: words = ["bat","tab","cat"]
        Output: [[0,1],[1,0]]
        Explanation: The palindromes are ["battab","tabbat"]
        Example 3.
        Input: words = ["a",""]
        Output: [[0,1],[1,0]]


        Constraints:

        1 <= words.length <= 5000
        0 <= words[i].length <= 300
        words[i] consists of lower-case English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<List<Integer>> palindromePairs(String[] words) {
            HashMap<String,Integer> wordMap = new HashMap<>();
            Set<Integer> set = new TreeSet<>();
            int n = words.length;

            for(int i=0;i<n;i++){
                wordMap.put(words[i],i);
                set.add(words[i].length());
            }

            List<List<Integer>> ans = new ArrayList<>();

            for(int i=0;i<n;i++){
                int length = words[i].length();

                if(length ==1){
                    if(wordMap.containsKey("")){
                        ans.add(Arrays.asList(i, wordMap.get("")));
                        ans.add(Arrays.asList(wordMap.get(""), i));
                    }
                    continue;
                }
                String reverse= new StringBuilder(words[i]).reverse().toString();
                if(wordMap.containsKey(reverse) && wordMap.get(reverse) != i)
                    ans.add(Arrays.asList(i,wordMap.get(reverse)));

                for(Integer k:set){
                    if(k==length)
                        break;
                    if(isPalindrome(reverse,0,length-1-k)){
                        String s1 = reverse.substring(length-k);
                        if(wordMap.containsKey(s1))
                            ans.add(Arrays.asList(i,wordMap.get(s1)));
                    }

                    if(isPalindrome(reverse,k,length-1)){
                        String s2 = reverse.substring(0,k);
                        if(wordMap.containsKey(s2))
                            ans.add(Arrays.asList(wordMap.get(s2),i));
                    }
                }
            }
            return ans;
        }

        private static boolean isPalindrome(String s, int left, int right){
            while(left<right)
                if(s.charAt(left++)!=s.charAt(right--))
                    return false;
            return true;
        }
    }
}
