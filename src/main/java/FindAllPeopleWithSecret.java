import java.util.*;

public class FindAllPeopleWithSecret {
    /*
        You are given an integer n indicating there are n people numbered from 0 to n - 1.
        You are also given a 0-indexePerson 0 has a secret and initially shares the secret with a person firstPerson at time 0.
        This secret is then shared every time a meeting takes place with a person that has the secret.
        More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
        The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.
        Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.

        Example 1.
        Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
        Output: [0,1,2,3,5]
        Explanation:
        At time 0, person 0 shares the secret with person 1.
        At time 5, person 1 shares the secret with person 2.
        At time 8, person 2 shares the secret with person 3.
        At time 10, person 1 shares the secret with person 5.​​​​
        Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
        Example 2.
        Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
        Output: [0,1,3]
        Explanation:
        At time 0, person 0 shares the secret with person 3.
        At time 2, neither person 1 nor person 2 know the secret.
        At time 3, person 3 shares the secret with person 0 and person 1.
        Thus, people 0, 1, and 3 know the secret after all the meetings.
        Example 3.
        Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
        Output: [0,1,2,3,4]
        Explanation:
        At time 0, person 0 shares the secret with person 1.
        At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
        Note that person 2 can share the secret at the same time as receiving it.
        At time 2, person 3 shares the secret with person 4.
        Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.


        Constraints:

        2 <= n <= 105
        1 <= meetings.length <= 105
        meetings[i].length == 3
        0 <= xi, yi <= n - 1
        xi != yi
        1 <= timei <= 105
        1 <= firstPerson <= n - 1d 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei.
         A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
     */
    public static void main(String[] args) {
        System.out.println("Solution with Union Find: " + Solution.findAllPeopleUF(4, new int[][]{{3,1,3},{1,2,2},{0,3,3}}, 3));
        System.out.println("Solution with BFS: " + Solution.findAllPeopleBFS(4, new int[][]{{3,1,3},{1,2,2},{0,3,3}}, 3));
    }

    static class DSU {
        int[] parent;
        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
        public void reset(int x) {
            parent[x] = x;
        }
    }

    //  O(N^2)
    // Space: O(N)
    static class Solution {
        // Union Find
        public static List<Integer> findAllPeopleUF(int n, int[][] meetings, int firstPerson) {
            DSU dsu = new DSU(n);
            dsu.union(0, firstPerson);
            TreeMap<Integer, List<Integer>> sameTimeMeeting = new TreeMap<>();
            int N = meetings.length;
            for (int i = 0; i < N; i++)
                sameTimeMeeting.computeIfAbsent(meetings[i][2], x -> new ArrayList<>()).add(i);
            for (int time : sameTimeMeeting.keySet()) {
                Set<Integer> pool = new HashSet<>();
                for (int id : sameTimeMeeting.get(time)) {
                    int a = meetings[id][0];
                    int b = meetings[id][1];
                    dsu.union(a, b);
                    pool.add(a);
                    pool.add(b);
                }
                for (int i : pool) if (dsu.find(0) != dsu.find(i)) dsu.reset(i);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (dsu.find(i) == dsu.find(0)) res.add(i);
            return res;
        }

        //BFS
        public static List<Integer> findAllPeopleBFS(int n, int[][] meetings, int firstPerson) {
            Map<Integer, Map<Integer, List<Integer>>> graph = new HashMap<>();
            for (int[] meeting : meetings) {
                int a = meeting[0], b = meeting[1], time = meeting[2];
                graph.putIfAbsent(a, new HashMap());
                graph.putIfAbsent(b, new HashMap());
                graph.get(a).computeIfAbsent(time, x -> new ArrayList<>()).add(b);
                graph.get(b).computeIfAbsent(time, x -> new ArrayList<>()).add(a);
            }
            Set<Integer> visited = new HashSet<>();
            List<Integer> res = new ArrayList<>();
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{0, 0});
            pq.offer(new int[]{firstPerson, 0});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curPerson = cur[0], curTime = cur[1];
                if (visited.add(curPerson)) res.add(curPerson);
                else continue;
                for (int otherTime : graph.getOrDefault(curPerson, new HashMap<>()).keySet()) {
                    if (otherTime < curTime) continue;
                    for (int nextPerson : graph.getOrDefault(curPerson, new HashMap<>()).get(otherTime))
                        pq.offer(new int[]{nextPerson, otherTime});
                }
            }
            return res;
        }
    }

}
