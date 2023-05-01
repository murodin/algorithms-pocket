public class AverageSalaryExcludingTheMinimumAndMaximumSalary {
    /*
        You are given an array of unique integers salary where salary[i] is the salary of the ith employee.

        Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.



        Example 1:

        Input: salary = [4000,3000,1000,2000]
        Output: 2500.00000
        Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
        Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
        Example 2:

        Input: salary = [1000,2000,3000]
        Output: 2000.00000
        Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
        Average salary excluding minimum and maximum salary is (2000) / 1 = 2000


        Constraints:

        3 <= salary.length <= 100
        1000 <= salary[i] <= 106
        All the integers of salary are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.average(new int[]{4000,3000,1000,2000}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static double average(int[] s) {
            int max = 0;
            int min = 100000000;
            double avg = 0;
            for (int j : s) {
                if (j > max)
                    max = j;
                if (j < min)
                    min = j;
            }
            for (int j : s) {
                avg += j;
            }
            avg = (avg-min-max)/(s.length-2);
            return avg;

        }
    }
}
