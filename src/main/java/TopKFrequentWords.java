import java.util.*;

public class TopKFrequentWords {
    /*
        Given an array of strings words and an integer k, return the k most frequent strings.
        Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.

        Example 1.
        Input: words = ["i","love","leetcode","i","love","coding"], k = 2
        Output: ["i","love"]
        Explanation: "i" and "love" are the two most frequent words.
        Note that "i" comes before "love" due to a lower alphabetical order.
        Example 2.
        Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
        Output: ["the","is","sunny","day"]
        Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


        Constraints:

        1 <= words.length <= 500
        1 <= words[i].length <= 10
        words[i] consists of lowercase English letters.
        k is in the range [1, The number of unique words[i]]

        Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.topKFrequent(new String[] {"the","day","is","sunny","the","the","the","sunny","is","is"}, 4));
    }

    // Time: O(NLogK)
    // Space: O(N)
    static class Solution {
        public static List<String> topKFrequent(String[] words, int k) {
            List<String> result = new LinkedList<>();
            Map<String, Integer> map = new HashMap<>();

            for (String word : words) {
                if (map.containsKey(word))
                    map.put(word, map.get(word) + 1);
                else
                    map.put(word, 1);
            }

            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                    (a,b) -> Objects.equals(a.getValue(), b.getValue()) ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
            );

            for(Map.Entry<String, Integer> entry: map.entrySet()) {
                pq.offer(entry);
                if(pq.size()>k)
                    pq.poll();
            }

            while(!pq.isEmpty())
                result.add(0, pq.poll().getKey());

            return result;
        }
    }
}
