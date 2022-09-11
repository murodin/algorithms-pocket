import java.util.*;

public class MaximumPerformanceOfATeam {
    /*
        You are given two integers n and k and two integer arrays speed and efficiency both of length n.
        There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
        Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
        The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.

        Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.

        Example 1.
        Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
        Output: 60
        Explanation:
        We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
        Example 2.
        Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
        Output: 68
        Explanation:
        This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
        Example 3:

        Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
        Output: 72


        Constraints:

        1 <= k <= n <= 105
        speed.length == n
        efficiency.length == n
        1 <= speed[i] <= 105
        1 <= efficiency[i] <= 108
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.maxPerformance(6 , new int[] {2,10,3,1,5,8}, new int[] {5,4,3,9,7,2}, 4));
        System.out.println("Solution II: " + Solution_II.maxPerformance(6 , new int[] {2,10,3,1,5,8}, new int[] {5,4,3,9,7,2}, 4));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
        public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            int[][] players = new int[n][2];
            for (int i=0; i<n; i++) {
                players[i][0] = efficiency[i];
                players[i][1] = speed[i];
            }
            // Sort the players based on efficiency in decreasing order, as for each iteration, we'll consider only players with higher efficiency.
            Arrays.sort(players, (p1, p2) -> (p2[0] - p1[0]));

            // Priority-Queue to maintain players with the highest relative speeds with efficiencies greater than the one under iteration.
            PriorityQueue<Integer> speedQueue = new PriorityQueue<>(k);
            long teamPerformance = 0, teamSpeed = 0;

            for (int i=0; i<n; i++) {
                // This is because a team can have almost `k` players.
                if (speedQueue.size() >= k) {
                    teamSpeed -= speedQueue.remove();
                }
                speedQueue.add(players[i][1]);
                teamSpeed += players[i][1];

                teamPerformance = Math.max(teamPerformance, teamSpeed * players[i][0]);
            }
            return (int) (teamPerformance % 1000000007);
        }
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_II {
        // Combining the data of both the given arrays to make it easier to do comparisons
        private static class Engineer {
            private final int speed;
            private final int efficiency;

            public Engineer(int speed, int efficiency) {
                this.speed = speed;
                this.efficiency = efficiency;
            }
        }

        public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            List<Engineer> engg = new ArrayList<>();    // Will store all the engineers

            for (int i = 0; i < n; i++) {
                engg.add(new Engineer(speed[i], efficiency[i]));
            }

            // sorting the eng list in descending order of their efficiency
            engg.sort((a, b) -> b.efficiency - a.efficiency);
            // This priority queue (min heap) will contain engineers chosen to form a team to get the performance
            // min heap will keep the slowest engineer at the top, faster ones stay longer in the team
            Queue<Engineer> currTeam = new PriorityQueue<>((a, b) -> a.speed - b.speed);

            long teamSpeed = 0, maxPerformance = 0;

            for (Engineer newHire : engg) {
                if (currTeam.size() == k) {
                    Engineer slowGuy = currTeam.poll();     // remove the engineer with the slowest speed
                    assert slowGuy != null;
                    teamSpeed -= slowGuy.speed;             // deducting the removed engineer's speed from the total speed
                }

                currTeam.add(newHire);                      // adding the new engineer to the team (eng list)
                teamSpeed += newHire.speed;                 // adding the new engineer's speed to the total speed

                long newPerformance = teamSpeed * (long) newHire.efficiency;    // getting the current performance
                maxPerformance = Math.max(maxPerformance, newPerformance);      // updating the max performance if greater
            }
            return (int) (maxPerformance % 1000000007);         // returning the way asked in the problem statement
        }
    }
}
