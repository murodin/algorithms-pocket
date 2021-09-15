import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StampingTheSequence {

    /*
        You are given two strings stamp and target. Initially, there is a string s of length target.length with all s[i] == '?'.
        In one turn, you can place stamp over s and replace every letter in the s with the corresponding letter from stamp.
        For example, if stamp = "abc" and target = "abcba", then s is "?????" initially. In one turn you can:
        place stamp at index 0 of s to obtain "abc??",
        place stamp at index 1 of s to obtain "?abc?", or
        place stamp at index 2 of s to obtain "??abc".
        Note that stamp must be fully contained in the boundaries of s in order to stamp (i.e., you cannot place stamp at index 3 of s).
        We want to convert s to target using at most 10 * target.length turns.

        Return an array of the index of the left-most letter being stamped at each turn.
        If we cannot obtain target from s within 10 * target.length turns, return an empty array.

        Example 1.
        Input: stamp = "abc", target = "ababc"
        Output: [0,2]
        Explanation: Initially s = "?????".
        - Place stamp at index 0 to get "abc??".
        - Place stamp at index 2 to get "ababc".
        [1,0,2] would also be accepted as an answer, as well as some other answers.

        Example 2.
        Input: stamp = "abca", target = "aabcaca"
        Output: [3,0,1]
        Explanation: Initially s = "???????".
        - Place stamp at index 3 to get "???abca".
        - Place stamp at index 0 to get "abcabca".
        - Place stamp at index 1 to get "aabcaca".
     */

    public static void main(String[] args) {
        String stamp = "abc", target = "ababc";
        System.out.println("Solution: " + Arrays.toString(Solution.movesToStamp(stamp, target)));
    }

    // Time: O(N*(N-M))
    // Space: O(N*(N-M))
    static class Solution {
        public static int[] movesToStamp(String stamp, String target) {
            char[] tchar = target.toCharArray();
            char[] schar = stamp.toCharArray();
            int count =0;
            boolean[] visited = new boolean[tchar.length];
            List<Integer> res = new ArrayList<>();

            while(count != tchar.length){
                boolean didChange = false;
                for(int i=0;i<= tchar.length - schar.length;i++){
                    if(!visited[i] && canReplace(tchar,i,schar)){
                        count = replace(tchar,i,schar.length,count);
                        visited[i] = true;
                        didChange = true;
                        res.add(i);

                        if(count == tchar.length){
                            break;
                        }
                    }
                }
                if(!didChange){
                    return new int[0];
                }
            }

            int[] result = new int[res.size()];
            int k=0;
            for(int i=res.size()-1;i>=0;i--){
                result[k++] = res.get(i);
            }

            return result;
        }

        static boolean canReplace(char[] tchar, int pos, char[] schar){
            for(int i=0;i<schar.length;i++){
                if(tchar[i+pos] != '?' && tchar[i+pos] != schar[i]){
                    return false;
                }
            }
            return true;
        }

        static int replace(char[] tchar, int pos, int len, int count){
            for(int i=0;i<len;i++){
                if(tchar[i+pos] != '?'){
                    tchar[i+pos] = '?';
                    count++;
                }
            }
            return count;
        }
    }
}
