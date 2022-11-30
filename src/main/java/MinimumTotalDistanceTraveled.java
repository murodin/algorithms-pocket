import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumTotalDistanceTraveled {
    /*
        There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the position of the ith robot.
        You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that
        positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.
        The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be in the same position as a factory initially.
        All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis.
        When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.
        At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance traveled by all the robots.
        Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots can be repaired.

        Note that

        All robots move at the same speed.
        If two robots move in the same direction, they will never collide.
        If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
        If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
        If the robot moved from a position x to a position y, the distance it moved is |y - x|.


        Example 1.
        Input: robot = [0,4,6], factory = [[2,2],[6,2]]
        Output: 4
        Explanation: As shown in the figure:
        - The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
        - The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
        - The third robot at position 6 will be repaired at the second factory. It does not need to move.
        The limit of the first factory is 2, and it fixed 2 robots.
        The limit of the second factory is 2, and it fixed 1 robot.
        The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
        Example 2.
        Input: robot = [1,-1], factory = [[-2,1],[2,1]]
        Output: 2
        Explanation: As shown in the figure:
        - The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
        - The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
        The limit of the first factory is 1, and it fixed 1 robot.
        The limit of the second factory is 1, and it fixed 1 robot.
        The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.


        Constraints:

        1 <= robot.length, factory.length <= 100
        factory[j].length == 2
        -109 <= robot[i], positionj <= 109
        0 <= limitj <= robot.length
        The input will be generated such that it is always possible to repair every robot.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumTotalDistance(Arrays.asList(1,-1), new int[][] {{-2,1}, {2,1}}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static Long[][] dp;
        public static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
            Collections.sort(robot);  // sorting robot based on position
            Arrays.sort(factory,(a, b)->(a[0]-b[0])); // sorting factory based on position
            dp = new Long[robot.size()+1][factory.length+1];
            return solve(robot,factory,0,0);
        }

        public static long solve(List<Integer> list,int[][] arr,int i,int j){
            if(i>=list.size()) return 0L;  // if all robot are repaired then there is no robot left to repair so return 0.
            if(j>=arr.length) return Long.MAX_VALUE; // here we check if there is no factory left for rapair robot but we have some robot to repair because we didnt pass on first condition.

            if(dp[i][j]!=null) return dp[i][j]; // checking memo for already calculated result.

            long x = 0;
            long res = solve(list,arr,i,j+1); // option 1 - no robot will repair on jth factory

            // here we check that ... from i to k will repair on jth factory and other will check with recurstion....
            // for k, we will check all possible index from i+1 to array.length
            for(int k = i;k<list.size() && k-i+1<=arr[j][1];k++){
                x += Math.abs(list.get(k)-arr[j][0]);
                long p = solve(list,arr,k+1,j+1);

                //p!=maxValue because of maxValue means there is no possible way to repair all robot with that
                if(p!=Long.MAX_VALUE){
                    res = Math.min(res,x+p);
                }
            }
            return dp[i][j] = res;
        }

    }
}
