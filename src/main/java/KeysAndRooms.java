import java.util.List;

public class KeysAndRooms {

    /*
        There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms.
        However, you cannot enter a locked room without having its key.
        When you visit a room, you may find a set of distinct keys in it.
        Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
        Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

        Example 1.
        Input: rooms = [[1],[2],[3],[]]
        Output: true
        Explanation:
        We visit room 0 and pick up key 1.
        We then visit room 1 and pick up key 2.
        We then visit room 2 and pick up key 3.
        We then visit room 3.
        Since we were able to visit every room, we return true.
        Example 2.
        Input: rooms = [[1,3],[3,0,1],[2],[0]]
        Output: false
        Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
     */

    public static void main(String[] args) {
        List<List<Integer>> testRooms = List.of(
                List.of(1,3),
                List.of(3,0,1),
                List.of(2),
                List.of(0)
        );
        System.out.println("Can Visit All-I: " + Solution_I.canVisitAllRooms(testRooms));
        System.out.println("Can Visit All-II: " + Solution_II.canVisitAllRooms(testRooms));
    }

    // Time: O(rooms + keys)
    // Space: O(rooms)
    static class Solution_I {
        static boolean[] visited;
        public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
            visited = new boolean[rooms.size()];
            visited[0] = true;
            dfs(rooms, 0);
            for(boolean b : visited) if(!b) return false;
            return true;
        }

        public static void dfs(List<List<Integer>> rooms, int ind) {
            for(int i : rooms.get(ind)) {
                if(!visited[i]){
                    visited[i] = true;
                    dfs(rooms, i);
                }
            }
        }
    }

    // Time: O(rooms + keys)
    // Space: O(rooms)
    static class Solution_II {
        static int[] visited;
        public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
            visited = new int[rooms.size()];
            dfs(0,rooms);
            for(int i : visited){
                if(i == 0){
                    return false;
                }
            }
            return true;
        }
        public static void dfs(int x, List<List<Integer>> rooms){
            visited[x] = 1;
            for(int i = 0; i < rooms.get(x).size(); i++){
                if(visited[rooms.get(x).get(i)] == 0){
                    dfs(rooms.get(x).get(i),rooms);
                }
            }
        }
    }
}
