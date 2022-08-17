import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {
    /*
        Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
        Return the sorted string. If there are multiple answers, return any of them.

        Example 1.
        Input: s = "tree"
        Output: "eert"
        Explanation: 'e' appears twice while 'r' and 't' both appear once.
        So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
        Example 2.
        Input: s = "cccaaa"
        Output: "aaaccc"
        Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
        Note that "cacaca" is incorrect, as the same characters must be together.
        Example 3.
        Input: s = "Aabb"
        Output: "bbAa"
        Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
        Note that 'A' and 'a' are treated as two different characters.


        Constraints:

        1 <= s.length <= 5 * 105
        s consists of uppercase and lowercase English letters and digits.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.frequencySort("cccaaa"));
    }

    // Time:O(NLogN)
    // Space: O(N)
    static class Solution {
        public static String frequencySort(String s) {
            //Step 1 : Store the frequencies in a hashmap
            Map<Character,Integer> map = new HashMap<>();
            for(char ch : s.toCharArray()){
                map.put(ch,map.getOrDefault(ch,0)+1);
            }
            //Step 2 : Now store the entries of map into a Max Heap using Priority Queue Datastructure
            PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
            for(Map.Entry<Character,Integer> value : map.entrySet()) { // entrySet() will return the elements of entries in set i.e unique entries into the map
                pq.add(value); // store the entries into pq
            }
            //Map.Entry<Datatype,Datatype> is used to access the whole singular entry
            //Step 3 : Since its a max heap , elements would have been stored in descending order just return them until pq size is 0
            StringBuilder sb = new StringBuilder();
            while(pq.size() > 0){
                Map.Entry<Character,Integer> value = pq.poll();
                sb.append(String.valueOf(value.getKey()).repeat(Math.max(0, value.getValue())));  // getValue() and getKey() supports if the datatype is Map.Entry<DT,DT>
            }
            return sb.toString();
        }
    }
}
