import java.util.*;

public class FindAllPossibleRecipesFromGivenSupplies {
    /*
        You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].

        Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
        You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

        Return a list of all the recipes that you can create. You may return the answer in any order.

        Note that two recipes may contain each other in their ingredients.

        Example 1.
        Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
        Output: ["bread"]
        Explanation:
        We can create "bread" since we have the ingredients "yeast" and "flour".
        Example 2.
        Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
        Output: ["bread","sandwich"]
        Explanation:
        We can create "bread" since we have the ingredients "yeast" and "flour".
        We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
        Example 3.
        Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
        Output: ["bread","sandwich","burger"]
        Explanation:
        We can create "bread" since we have the ingredients "yeast" and "flour".
        We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
        We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


        Constraints:

        n == recipes.length == ingredients.length
        1 <= n <= 100
        1 <= ingredients[i].length, supplies.length <= 100
        1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
        recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
        All the values of recipes and supplies combined are unique.
        Each ingredients[i] does not contain any duplicate values.
     */

    public static void main(String[] args) {
        String[] recipes = {"bread","sandwich"};
        var ingredients = List.of(
                List.of("yeast","flour"),
                List.of("bread","meat")
        );
        String[] supplies = {"yeast","flour","meat"};

        System.out.println("Solution I: " + Solution_I.findAllRecipes(recipes, ingredients, supplies));
        System.out.println("Solution II: " + Solution_II.findAllRecipes(recipes, ingredients, supplies));
    }

    // Topological Sort
    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
        static Set<String> suppliesAvailable = new HashSet<>();
        static Map<String, List<Recipy>> graph = new HashMap<>();
        static PriorityQueue<Recipy> pq = new PriorityQueue<>();

        static class Recipy implements Comparable<Recipy> {
            String name;
            Set<String> ingredientsReq;
            int ingredientsNotAvailable;

            public Recipy (String name, Set<String> ingredientsReq, int ingredientsNotAvailable) {
                this.name = name;
                this.ingredientsReq = ingredientsReq;
                this.ingredientsNotAvailable = ingredientsNotAvailable;
            }

            public String toString() {
                return String.format("%s, %s, %d", (Object) name, ingredientsReq, ingredientsNotAvailable);
            }

            @Override
            public int compareTo(Recipy o1) {
                return this.ingredientsNotAvailable - o1.ingredientsNotAvailable;
            }
        }

        private static void initialize(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            suppliesAvailable.addAll(Arrays.asList(supplies));
            for(int i = 0; i < recipes.length; i++) {
                Set<String> temp = new HashSet<>();
                Recipy recipy = new Recipy(recipes[i], temp, 0);

                for(String ingredient : ingredients.get(i)) {
                    if(!suppliesAvailable.contains(ingredient)) {
                        temp.add(ingredient);
                        graph.putIfAbsent(ingredient, new ArrayList<>());
                        graph.get(ingredient).add(recipy);
                    }
                }

                recipy.ingredientsNotAvailable = temp.size();
                pq.offer(recipy);
            }
        }

        public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            initialize(recipes, ingredients, supplies);
            List<String> ans = new ArrayList<>();

            while(!pq.isEmpty()) {
                Recipy recipy = pq.poll();
                if(recipy.ingredientsNotAvailable == 0) {
                    ans.add(recipy.name);
                    List<Recipy> recipiesMightBePossible = graph.get(recipy.name);
                    if(recipiesMightBePossible == null) continue;
                    for(Recipy temp : recipiesMightBePossible) {
                        if(temp.ingredientsReq.contains(recipy.name)) {
                            pq.remove(temp);
                            temp.ingredientsNotAvailable--;
                            temp.ingredientsReq.remove(recipy);
                            pq.offer(temp);
                        }
                    }
                }
            }

            return ans;
        }
    }

    // DFS
    // Time: O(V+E)
    // Space: O(N)
    static class Solution_II {
        static Set<String> items;
        static Map<String, Set<String>> graph;
        static Set<String> visited;

        static void initialize(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            items = new HashSet<>();
            visited = new HashSet<>();
            Collections.addAll(items, supplies);

            graph = new HashMap<>();
            for(int i = 0; i < recipes.length; i++) {
                graph.put(recipes[i], new HashSet<>());
                for(String ingredient : ingredients.get(i)) {
                    if(!items.contains(ingredient)) {
                        graph.get(recipes[i]).add(ingredient);
                    }
                }
            }
        }

        static boolean dfs(String recipy) {
            if(items.contains(recipy)) return true;
            if(visited.contains(recipy)) return false;

            visited.add(recipy);
            if(!graph.containsKey(recipy)) return false;

            for(String ingredient : graph.get(recipy)) {
                if(!dfs(ingredient)) return false;
            }

            items.add(recipy);

            return true;
        }

        public static List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            initialize(recipes, ingredients, supplies);
            List<String> result = new ArrayList<>();

            for(String recipy : recipes) {
                if(dfs(recipy)) {
                    result.add(recipy);
                }
            }

            return result;
        }
    }
}
