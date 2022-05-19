public class ProbabilityOfATwoBoxesHavingTheSameNumberOfDistinctBalls {
    /*
        Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i.
        All the balls will be shuffled uniformly at random,
        then we will distribute the first n balls to the first box and the remaining n balls to the other box (Please read the explanation of the second example carefully).
        Please note that the two boxes are considered different.
        For example, if we have two balls of colors a and b, and two boxes [] and (),
        then the distribution [a] (b) is considered different than the distribution [b] (a) (Please read the explanation of the first example carefully).
        Return the probability that the two boxes have the same number of distinct balls. Answers within 10-5 of the actual value will be accepted as correct.

        Example 1.
        Input: balls = [1,1]
        Output: 1.00000
        Explanation: Only 2 ways to divide the balls equally:
        - A ball of color 1 to box 1 and a ball of color 2 to box 2
        - A ball of color 2 to box 1 and a ball of color 1 to box 2
        In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
        Example 2.
        Input: balls = [2,1,1]
        Output: 0.66667
        Explanation: We have the set of balls [1, 1, 2, 3]
        This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equal probability (i.e. 1/12):
        [1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
        After that, we add the first two balls to the first box and the second two balls to the second box.
        We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
        Probability is 8/12 = 0.66667
        Example 3.
        Input: balls = [1,2,1,2]
        Output: 0.60000
        Explanation: The set of balls is [1, 2, 2, 3, 4, 4]. It is hard to display all the 180 possible random shuffles of this set but it is easy to check that 108 of them will have the same number of distinct colors in each box.
        Probability = 108 / 180 = 0.6


        Constraints:

        1 <= balls.length <= 8
        1 <= balls[i] <= 6
        sum(balls) is even.
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.getProbability(new int[] {2,1,1}));
    }

    // Time:
    // Space:
    static class Solution {
        static int[] balls;
        static int n;
        static Double dp[][][][][];
        static Double ncrdp[][];

        public static double getProbability(int[] balls) {
            Solution.balls = balls;
            int sum =0 ;

            for(int ball : balls) {
                sum = sum + ball;
            }
            n = sum/2;
            dp= new Double[9][balls.length][balls.length][n+1][n+1];
            ncrdp = new Double[sum+1][sum+1] ;
            double totalDistinctWays = fact(sum);
            for(int ball : balls) {
                totalDistinctWays = totalDistinctWays / fact(ball);
            }
            double possibleWaysWithCondition = calculate(0, 0, 0, n, n);
            return possibleWaysWithCondition/totalDistinctWays;

        }

        private static double calculate(int index, int df, int ds, int firstBox, int secondBox) {
            // df is distinct element in first box
            // ds is distinct element in second box
            // firstBox is element to be filled in firstBox
            // secondBox is element to be filled in second box
            if (firstBox== 0 && secondBox ==0) {
                if (df == ds) return 1.0;
                else return 0;
            }
            if (firstBox < 0 || secondBox < 0) {
                return 0;
            }
            if (dp[index][df][ds][firstBox][secondBox] != null) return dp[index][df][ds][firstBox][secondBox];
            double ans = 0;
            for(int i = 0; i <= balls[index]; i++ ) {
                ans = ans +  nCr(secondBox, (balls[index] - i))* nCr(firstBox, i) *
                        calculate(index+1,
                                df + (i > 0 ? 1 : 0),
                                ds + ( (balls[index] - i) > 0 ? 1 : 0),
                                firstBox - i,
                                secondBox - (balls[index] - i));
            }
            dp[index][df][ds][firstBox][secondBox] = ans;
            return ans;
        }

        private static double nCr(int n, int r)
        {
            if(ncrdp[n][r] != null) return ncrdp[n][r];
            ncrdp[n][r] =  fact(n) / (fact(r) * fact(n - r));
            return ncrdp[n][r];
        }

        private static double fact(int n)
        {
            double res = 1;
            for (int i = 2; i <= n; i++)
                res = res * i;
            return res;
        }
    }
}
