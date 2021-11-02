import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestDistance2ACharacter {

    /*
        Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.

        The distance between two indices i and j is abs(i - j), where abs is the absolute value function.

        Example 1.
        Input: s = "loveleetcode", c = "e"
        Output: [3,2,1,0,1,0,0,1,2,2,1,0]
        Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
        The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
        The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
        For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
        The closest occurrence of 'e' for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
        Example 2.
        Input: s = "aaab", c = "b"
        Output: [3,2,1,0]
     */

    public static void main(String[] args) {
        System.out.println("Shortest Distance: " + Arrays.toString(Solution.shortestToChar("aaab", 'b')));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int[] shortestToChar(String s, char c) {
            List<Integer> l = new ArrayList<>();
            int n = s.length();

            l.add(-10000);
            for(int i=0;i<n;i++){
                if(s.charAt(i) == c){
                    l.add(i);
                }
            }
            l.add(Integer.MAX_VALUE);

            int[] result = new int[n];

            int first = l.get(0);
            int second = l.get(1);

            int k = 2;
            for(int i=0;i<n;i++){
                if(s.charAt(i) != c){
                    result[i] = Math.min(i-first,second-i);
                } else{
                    first = second;
                    second = l.get(k++);
                }
            }
            return result;
        }
    }
}
