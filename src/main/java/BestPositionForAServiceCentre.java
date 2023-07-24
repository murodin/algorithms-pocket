public class BestPositionForAServiceCentre {
    /*
        A delivery company wants to build a new service center in a new city. The company knows the positions of
        all the customers in this city on a 2D-Map and wants to build the new center in a position such that the sum of the euclidean distances to all customers is minimum.
        Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map,
        return the minimum sum of the euclidean distances to all customers.
        In other words, you need to choose the position of the service center [xcentre, ycentre] such that the following formula is minimized:

        Answers within 10-5 of the actual value will be accepted.

        Example 1:
        Input: positions = [[0,1],[1,0],[1,2],[2,1]]
        Output: 4.00000
        Explanation: As shown, you can see that choosing [xcentre, ycentre] = [1, 1] will make the distance to each customer = 1,
        the sum of all distances is 4 which is the minimum possible we can achieve.
        Example 2:
        Input: positions = [[1,1],[3,3]]
        Output: 2.82843
        Explanation: The minimum possible sum of distances = sqrt(2) + sqrt(2) = 2.82843


        Constraints:

        1 <= positions.length <= 50
        positions[i].length == 2
        0 <= xi, yi <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.getMinDistSum(new int[][]{{0,1},{1,0},{1,2},{2,1}}));
    }

    // Time: O(N^2)
    // Space: O(1)
   static class Solution {
        private static final int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        public static double getMinDistSum(int[][] positions) {
            double x = 0, y = 0;
            double approxRes = findEuclideanDist(positions, x, y);
            double step = 50;

            while(step >= 0.000001) {
                double localMinEuclideanDist = Double.MAX_VALUE;
                double localX = 0.0, localY = 0.0;

                for(int[] dir : dirs) {
                    double neighborX = x + step * dir[0];
                    double neighborY = y + step * dir[1];
                    double neighborEuclideanDist = findEuclideanDist(positions, neighborX, neighborY);

                    if(localMinEuclideanDist > neighborEuclideanDist) {
                        localMinEuclideanDist = neighborEuclideanDist;
                        localX = neighborX;
                        localY = neighborY;
                    }
                }
                if(approxRes > localMinEuclideanDist) {
                    approxRes = localMinEuclideanDist;
                    x = localX;
                    y = localY;
                    continue;
                }
                step /= 2;
            }

            return approxRes;
        }
        private static double findEuclideanDist(int[][] positions, double x, double y) {
            double res = 0;
            for(int[] position : positions) {
                res += Math.sqrt(Math.pow(position[0] - x, 2) + Math.pow(position[1] - y, 2));
            }
            return res;
        }
    }
}
