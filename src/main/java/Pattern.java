public class Pattern {
    /*
        Pattern: [[1,2,3,4,5], [2,3,4,5,1], [3,4,5,2,1], [4,5,3,2,1], [5,4,3,2,1]]
        At Most three for loops
        NOT If statements
        NOT more than two variables
     */

    public static void main(String[] args) {
        Solution.pattern();
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        static void pattern() {
            int i;
            for(i = 1; i <= 5; i++) {
                System.out.print(i + " ");
                int j;
                for(j = i+1; j <= 5; j++) {
                    System.out.print(j + " ");
                }
                for(; 5<j+i-2; j--) {
                    System.out.print(j+i-7 + " ");
                }
                System.out.println("");
            }
        }
    }
}
