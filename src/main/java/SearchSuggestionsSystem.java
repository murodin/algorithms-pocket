import java.util.*;

public class SearchSuggestionsSystem {
    /*
        You are given an array of strings products and a string searchWord.
        Design a system that suggests at most three product names from products after each character of searchWord is typed.
        Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
        Return a list of lists of the suggested products after each character of searchWord is typed.

        Example 1.
        Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
        Output: [
        ["mobile","moneypot","monitor"],
        ["mobile","moneypot","monitor"],
        ["mouse","mousepad"],
        ["mouse","mousepad"],
        ["mouse","mousepad"]
        ]
        Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
        After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
        After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
        Example 2.
        Input: products = ["havana"], searchWord = "havana"
        Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
        Example .
        Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
        Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]


        Constraints:

        1 <= products.length <= 1000
        1 <= products[i].length <= 3000
        1 <= sum(products[i].length) <= 2 * 104
        All the strings of products are unique.
        products[i] consists of lowercase English letters.
        1 <= searchWord.length <= 1000
        searchWord consists of lowercase English letters.
     */

    public static void main(String[] args) {
        String[] products = {"bags","baggage","banner","box","cloths"};
        String searchWord = "bags";
        System.out.println("Solution I:");
        Solution_I.suggestedProducts(products, searchWord);
        System.out.println("Solution II:");
        Solution_II.suggestedProducts(products, searchWord);
        System.out.println("Solution III:");
        Solution_III.suggestedProducts(products, searchWord);
    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution_I {
        public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
            Arrays.sort(products);
            List<List<String>> answer= new ArrayList<>();
            for(int i=0;i<searchWord.length();i++) {
                String s = searchWord.substring(0,i+1);
                int count = 0;
                List<String> l = new ArrayList<String>();
                for (String product : products) {
                    if (product.length() >= i + 1) {
                        if ((product.substring(0, i + 1)).equals(s)) {
                            // System.out.println(products[j]);
                            l.add(product);
                            count++;
                        }
                    }
                    if (count == 3)
                        break;
                }
                System.out.println(l);
                answer.add(l);
                l.clear();
            }
            return answer;
        }
    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution_II {
        public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
            List<List<String>> ans = new ArrayList<>();
            PriorityQueue<String> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 1; i <= searchWord.length(); i++) {
                String t = searchWord.substring(0, i);
                for (String word: products) {
                    if (word.startsWith(t))
                        pq.add(word);

                    if (pq.size() > 3)
                        pq.poll();
                }

                List<String> list = new ArrayList<>();
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
                Collections.reverse(list);
                System.out.println(list);
                ans.add(list);
            }
            return ans;
        }
    }

    // Time:
    // Space:
    static class Solution_III {
        public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
            List<List<String>> ans = new ArrayList<>();
            for (int i = 1; i <= searchWord.length(); i++) {
                String typed = searchWord.substring (0, i); // to store the characters that are typed
                List<String> list = new ArrayList<>();
                for (String p : products) {
                    if (p.startsWith(typed)) {
                        list.add(p);
                        Collections.sort(list);
                        if (list.size() == 4)
                            list.remove(list.size() - 1);
                    }
                }
                System.out.println(list);
                ans.add(list);
            }
            return ans;
        }
    }
}
