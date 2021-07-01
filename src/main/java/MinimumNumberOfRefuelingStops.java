import java.util.PriorityQueue;

public class MinimumNumberOfRefuelingStops {

    /*
        A car travels from a starting position to a destination which is target miles east of the starting position.

        There are gas stations along the way.
        The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that
        the ith gas station is positioni miles east of the starting position and has fueli liters of gas.

        The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.
        It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

        Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.

        Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.
        If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
     */

    public static void main(String[] args) {
        int[][] testStations = {{10,60}, {20,30}, {30,30}, {60,40}};
        int target = 100, startFuel = 10;

        System.out.println("Min Refuel: " + Solution.minRefuelStops(target, startFuel, testStations));
        System.out.println("Min Refuel BFS: " + Solution.minRefuelStopsBFS(target, startFuel, testStations));
    }

    static class Solution {
        public static int minRefuelStops(int target, int startFuel, int[][] stations) {
            int n = stations.length;
            long[] dp = new long[n + 1];
            dp[0] = startFuel;

            for(int i=0; i<n; i++) {
                for(int refill=i; refill>=0 && dp[refill]>=stations[i][0]; refill--) {
                    dp[refill+1] = Math.max(dp[refill+1], dp[refill] + stations[i][1]);
                }
            }

            for(int i=0; i<=n;i++) {
                if(dp[i]>=target)
                    return i;
            }

            return -1;
        }

        public static int minRefuelStopsBFS(int target, int startFuel, int[][] stations) {
            int n = stations.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1]-a[1]);
            int reFill = 0, i= 0;
            int distance = startFuel;

            while (distance < target) {
                while (i < n && distance >= stations[i][0]) {
                    pq.offer(stations[i]);
                    i++;
                }

                distance += pq.remove()[1];
                reFill++;
            }

            return reFill;
        }
    }

}
