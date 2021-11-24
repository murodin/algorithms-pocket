import java.util.Stack;

public class FindTheMostCompetitiveSubsequence {

    /*
        Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
        An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
        We define that a subsequence a is more competitive than a subsequence b (of the same length)
        if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b.
         For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.

        Example 1.
        Input: nums = [3,5,2,6], k = 2
        Output: [2,6]
        Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
        Example 2.
        Input: nums = [2,4,3,3,5,4,9,6], k = 4
        Output: [2,3,3,4]
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.mostCompetitive(new int[]{2,4,3,3,5,4,9,6}, 4 ));
        System.out.println("Solution II: " + Solution_II.mostCompetitive(new int[]{2,4,3,3,5,4,9,6}, 4 ));
    }

    // Time: O()
    // Space: O()
    static class Solution_I {
        public static int[] mostCompetitive(int[] nums, int k) {
            Stack<Integer> st=new Stack<>();

            for(int i=0;i<nums.length;i++){

                //poping from stack
                while(!st.isEmpty() && nums[i]<nums[st.peek()] && st.size() + nums.length -i>k){
                    st.pop();
                }
                if(st.size()<k){
                    st.push(i);
                }
            }

            int i=k;
            int[] result= new int[k];
            while(!st.isEmpty()){
                result[--i]=nums[st.pop()];
            }

            return result;
        }
    }

    // Time: O()
    // Space: O()
    static class Solution_II {
        public static int[] mostCompetitive(int[] nums, int k) {
            int[] result= new int[k];
            int top=0;
            for(int i=0;i<nums.length;i++){

                //poping from stack
                while(top>0 && nums[i]<nums[top-1] && (top + nums.length -i)>k){
                    top--;
                }

                if(top<k){
                    result[top++]=nums[i];
                }
            }
            return result;
        }
    }
}
