import java.util.Arrays;

public class Candy {

    /*
        There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

        You are giving candies to these children subjected to the following requirements:

        Each child must have at least one candy.
        Children with a higher rating get more candies than their neighbors.
        Return the minimum number of candies you need to have to distribute the candies to the children.
     */

    public static void main(String[] args) {
        int[] testRatings = {1, 0, 2};
        System.out.println("Candies: " + Solution.candy(testRatings));

        System.out.println("Candies Optimum Space: " + Solution_Optimum_Space.candy(testRatings));
    }

    /*
        Time Complexity: o(N)
        Space Complexity: o(N)
     */
    static class Solution {
        public static int candy(int[] ratings) {
            int n = ratings.length;
            int caddies = 0;
            int[] r = new int[n];
            int[] l = new int[n];

            Arrays.fill(r, 1);
            Arrays.fill(l, 1);

            for(int i=1; i<n; i++) {
                if(ratings[i] > ratings[i-1]) l[i] = l[i-1] +1;
            }

            for(int i=n-2; i >= 0; i--) {
                if(ratings[i] > ratings[i+1]) r[i] = r[i+1] +1;
            }

            for(int i=0; i <n; i++) {
                caddies = caddies + Math.max(r[i], l[i]);
            }

            return caddies;
        }
    }


    /*
        Time Complexity: o(N)
        Space Complexity: o(1)
     */
    static class Solution_Optimum_Space {
        public static int candy(int[] ratings) {
            if(ratings.length <= 1) return ratings.length;

            int up = 0, down = 0;
            int prevSlope = 0;
            int candies = 0;

            // Looping the array
            for(int i=1; i<ratings.length; i++) {

                // Calculate slope
                // Inc: 1, Dec: -1, Equal(Plain): 0
                int currSlope = ratings[i] > ratings[i-1]? 1
                        : (ratings[i] < ratings[i-1] ? -1: 0);

                // Dividing Mountains
                //Divide Mountain on three conditions \/ || \_ || /--
                if((prevSlope < 0 && currSlope >= 0) || (prevSlope > 0 && currSlope == 0)) {
                    candies = candies + sum(up) + sum(down) + Math.max(up, down);
                    up = 0;
                    down = 0;
                }

                if(currSlope > 0) up++;
                else if(currSlope < 0) down++;
                else candies++;

                prevSlope = currSlope;
            }

            candies = candies + sum(up) + sum(down) + Math.max(up, down) + 1;

            return candies;
        }

        private static int sum(int n) {
            return (n*(n+1)/2);
        }
    }
}
