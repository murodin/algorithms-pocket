import java.util.Arrays;

public class BestTeamWithNoConflicts {
    /*
        You are the manager of a basketball team.
        For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
        However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player.
        A conflict does not occur between players of the same age.
        Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player,
        respectively, return the highest overall score of all possible basketball teams.

        Example 1.
        Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
        Output: 34
        Explanation: You can choose all the players.
        Example 2.
        Input: scores = [4,5,6,5], ages = [2,1,2,1]
        Output: 16
        Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
        Example 3.
        Input: scores = [1,2,3,5], ages = [8,9,10,1]
        Output: 6
        Explanation: It is best to choose the first 3 players.


        Constraints:

        1 <= scores.length, ages.length <= 1000
        scores.length == ages.length
        1 <= scores[i] <= 106
        1 <= ages[i] <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int bestTeamScore(int[] scores, int[] ages) {
            int n = scores.length;
            int[][] players = new int[n][2];
            for(int i=0;i<n;i++){
                players[i][0]=scores[i];
                players[i][1]=ages[i];
            }
            Arrays.sort(players, (p1, p2) -> p1[0]==p2[0]? Integer.compare(p1[1],p2[1]): Integer.compare(p1[0],p2[0]));
            int[] dp = new int[n];
            int max=0;
            for(int i=0;i<n;i++){
                dp[i]=search(dp,players,i)+players[i][0];
                max=Math.max(max,dp[i]);
            }
            return max;
        }

        private static int search(int[] dp, int[][] players, int i){
            int max=0;
            for(int j=0;j<i;j++){
                if(dp[j]>max && players[j][1]<=players[i][1]){
                    max=dp[j];
                }
            }
            return max;
        }
    }
}
