import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ReconstructItinerary {
    /*
        You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
        Reconstruct the itinerary in order and return it.
        All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries,
        you should return the itinerary that has the smallest lexical order when read as a single string.
        For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
        You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

        Example 1.
        Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
        Output: ["JFK","MUC","LHR","SFO","SJC"]
        Example 2.
        Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


        Constraints:

        1 <= tickets.length <= 300
        tickets[i].length == 2
        fromi.length == 3
        toi.length == 3
        fromi and toi consist of uppercase English letters.
        fromi != toi
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findItinerary(
                List.of(List.of("MUC","LHR"), List.of("JFK","MUC"), List.of("SFO","SJC"), List.of("LHR","SFO"))
        ));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution{
        private static HashMap<String, PriorityQueue<String>> map= new HashMap<>();//Starting airport -> Destination airport(lexically asc sorted)
        private static LinkedList<String> res= new LinkedList<>();//result
        public static List<String> findItinerary(List<List<String>> tickets)  {
            /*Note::
             *->The main idea is to traverse every edge atmost once
             *->And we are starting from JFK airport
             *->We use Priority Queue to store the adjacent airport in Lexically sorted manner
             *->We use a topological sort like approach for displaying the result, i.e, we start from an no in-dependency edge to the most in-dependenncy edge
             *->We are considering the euler path to traverse the graph
             *->Priority Queue is also helping us keep track of the visited and non-visited edge
             *->Hash Map is Used like a adjacency list here
             */

            for (List<String> ticket: tickets) {
                String u= ticket.get(0);//starting airport
                String v= ticket.get(1);//destination airport

                PriorityQueue<String> temp= map.getOrDefault(u, new PriorityQueue<>());
                temp.offer(v);//adding the desitination airport
                map.put(u, temp);//adding to the map
            }
            dfs("JFK");//Journey Starting Airport
            return res;
        }

        private static void dfs(String src) {
            PriorityQueue<String> pq= map.get(src);//Adjacent Airports

            while (pq != null && pq.size() > 0){ //processsing all the destination Airport of the current Airport(src)
                String temp= pq.poll();//removing the edge//visited
                dfs(temp);//recursing down
            }
            res.addFirst(src);//adding the Airport while backtracking//least in-dependency as far as possible
        }
    }
}
