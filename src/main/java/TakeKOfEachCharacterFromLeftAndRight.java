public class TakeKOfEachCharacterFromLeftAndRight {
    /*
        You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k.
        Each minute, you may take either the leftmost character of s, or the rightmost character of s.

        Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.



        Example 1:

        Input: s = "aabaaaacaabc", k = 2
        Output: 8
        Explanation:
        Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
        Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
        A total of 3 + 5 = 8 minutes is needed.
        It can be proven that 8 is the minimum number of minutes needed.
        Example 2:

        Input: s = "a", k = 1
        Output: -1
        Explanation: It is not possible to take one 'b' or 'c' so return -1.


        Constraints:

        1 <= s.length <= 105
        s consists of only the letters 'a', 'b', and 'c'.
        0 <= k <= s.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.takeCharacters("aabaaaacaabc", 2));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        private static boolean valid(int[] arr, int k){
            return arr[0] >= k && arr[1] >= k && arr[2] >= k;
        }
        public static int takeCharacters(String s, int k) {
            int[] arr =new int[3];
            for(int i=0;i<s.length();i++){
                arr[s.charAt(i)-'a']++;
            }
            if(arr[0]<k || arr[1]<k || arr[2]<k) return -1;
            int i = s.length()-1;
            int j = s.length()-1;
            int sum = arr[0] + arr[1] + arr[2];
            int mini = Integer.MAX_VALUE;
            while(i>=0 && j>=0){
                arr[s.charAt(i)-'a']--;
                i--;
                while(!valid(arr,k)&& j>=0){
                    arr[s.charAt(j)-'a']++;
                    j--;

                }
                sum=arr[0]+arr[1]+arr[2];
                mini=Math.min(sum,mini);
            }
            return mini;
        }
    }
}
