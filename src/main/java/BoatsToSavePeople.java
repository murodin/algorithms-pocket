import java.util.Arrays;

public class BoatsToSavePeople {

    /*
         You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit.
         Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

        Return the minimum number of boats to carry every given person.

        Example 1.
        Input: people = [1,2], limit = 3
        Output: 1
        Explanation: 1 boat (1, 2)
        Example 2:

        Input: people = [3,2,2,1], limit = 3
        Output: 3
        Explanation: 3 boats (1, 2), (2) and (3)
        Example 3:

        Input: people = [3,5,3,4], limit = 5
        Output: 4
        Explanation: 4 boats (3), (3), (4), (5)


        Constraints:

        1 <= people.length <= 5 * 104
        1 <= people[i] <= limit <= 3 * 104
     */

    public static void main(String[] args) {
        int[] people = {3,2,2,1};
        System.out.println("Solution I: " + Solution_I.numRescueBoats(people, 3));
        System.out.println("Solution II: " + Solution_II.numRescueBoats(people, 3));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
        public static int numRescueBoats(int[] people, int limit) {
            int n = people.length;
            int count[] = new int[limit+1];

            for(int p: people){
                count[p]++;
            }
            int i = 0;
            for(int val = 1; val<=limit; val++){
                while(count[val]-- > 0){
                    people[i++] = val;
                }
            }

            int left = 0, right = n-1, boats = 0;
            while(left <= right){
                if(people[left] + people[right] <= limit){
                    left++;
                    right--;
                }
                else{
                    right--;
                }
                boats++;
            }
            return boats;
        }
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_II{
        public static int numRescueBoats(int[] people, int limit) {
            int len = people.length;
            Arrays.sort(people);
            int left = 0;
            int right = len - 1;
            int ans = 0;
            while (left <= right) {
                if (people[left] + people[right] <= limit) {
                    left++;
                    right--;
                    ans++;
                } else {
                    right--;
                    ans++;
                }
            }
            return ans;
        }
    }
}
