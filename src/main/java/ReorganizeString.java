import java.util.*;

public class ReorganizeString {
    /*
        Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
        Return any possible rearrangement of s or return "" if not possible.

        Example 1:

        Input: s = "aab"
        Output: "aba"
        Example 2:

        Input: s = "aaab"
        Output: ""


        Constraints:

        1 <= s.length <= 500
        s consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reorganizeString("aab"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String reorganizeString(String s) {
            Map<Character, Integer> count = new HashMap<>();
            for (char c : s.toCharArray()) {
                count.put(c, count.getOrDefault(c, 0) + 1);
            }

            List<int[]> maxHeap = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry : count.entrySet()) {
                maxHeap.add(new int[]{-entry.getValue(), entry.getKey()});
            }
            heapify(maxHeap);

            int[] prev = null;
            StringBuilder res = new StringBuilder();
            while (!maxHeap.isEmpty() || prev != null) {
                if (prev != null && maxHeap.isEmpty()) {
                    return "";
                }

                int[] top = heapPop(maxHeap);
                res.append((char) top[1]);
                top[0]++;

                if (prev != null) {
                    heapPush(maxHeap, prev);
                    prev = null;
                }

                if (top[0] != 0) {
                    prev = top;
                }
            }

            return res.toString();
        }

        private static void heapify(List<int[]> heap) {
            int n = heap.size();
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapifyDown(heap, i);
            }
        }

        private static void heapifyDown(List<int[]> heap, int index) {
            int n = heap.size();
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < n && heap.get(left)[0] < heap.get(largest)[0]) {
                largest = left;
            }
            if (right < n && heap.get(right)[0] < heap.get(largest)[0]) {
                largest = right;
            }

            if (largest != index) {
                Collections.swap(heap, index, largest);
                heapifyDown(heap, largest);
            }
        }

        private static int[] heapPop(List<int[]> heap) {
            int n = heap.size();
            int[] top = heap.get(0);
            heap.set(0, heap.get(n - 1));
            heap.remove(n - 1);
            heapifyDown(heap, 0);
            return top;
        }

        private static void heapPush(List<int[]> heap, int[] element) {
            heap.add(element);
            heapifyUp(heap, heap.size() - 1);
        }

        private static void heapifyUp(List<int[]> heap, int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap.get(index)[0] >= heap.get(parent)[0]) {
                    break;
                }
                Collections.swap(heap, index, parent);
                index = parent;
            }
        }
    }
}
