import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Triangle {

    /*
        Given a triangle array, return the minimum path sum from top to bottom.

        For each step, you may move to an adjacent number of the row below.
        More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

        Example:

        Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        Output: 11
        Explanation: The triangle looks like:
               2
              3 4
             6 5 7
            4 1 8 3
        The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
     */

    public static void main(String[] args) {
        List<List<Integer>> testTriangle = new ArrayList<>();
        createTriangle(testTriangle);

        System.out.println("Min Path Sum:" + Solution.minimumTotal(testTriangle));
        //System.out.println("Min Path Sum II:" + Solution.minimumTotal_II(testTriangle));
    }

    private static void createTriangle(List<List<Integer>> testTriangle) {
        List<Integer> a = new ArrayList<>();
        a.add(2);

        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);

        List<Integer> c = new ArrayList<>();
        c.add(6);
        c.add(5);
        c.add(7);

        List<Integer> d = new ArrayList<>();
        d.add(4);
        d.add(1);
        d.add(8);
        d.add(3);

        testTriangle.add(a);
        testTriangle.add(b);
        testTriangle.add(c);
        testTriangle.add(d);
    }

    static class Solution {
        public static int minimumTotal(List<List<Integer>> triangle) {
            for(int i = 1; i < triangle.size(); i++) {
                for(int j = 0; j < triangle.get(i).size(); j++){
                    int sum = 0;
                    if(j == 0) {
                        sum = triangle.get(i).get(j) + triangle.get(i-1).get(j);
                    }
                    else if(j == triangle.get(i).size()-1) {
                        sum = triangle.get(i).get(j) + triangle.get(i-1).get(triangle.get(i-1).size()-1);
                    }
                    else {
                        int min = Math.min(triangle.get(i-1).get(j), triangle.get(i-1).get(j-1));
                        sum = min+ triangle.get(i).get(j);
                    }

                    triangle.get(i).set(j, sum);
                }
            }
            return Collections.min(triangle.get(triangle.size()-1));
        }

        public static int minimumTotal_II(List<List<Integer>> triangle) {
            for(int i = triangle.size()-2; i >= 0; i--) {
                for(int j = 0; j < triangle.get(i).size(); j++) {
                    int min = Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                    int sum = min + triangle.get(i).get(j);
                    triangle.get(i).set(j, sum);
                }
            }
            return triangle.get(0).get(0);
        }
    }
}
