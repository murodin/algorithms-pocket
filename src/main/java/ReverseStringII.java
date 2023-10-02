public class ReverseStringII {
    /*
        Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
        If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters,
        then reverse the first k characters and leave the other as original.

        Example 1:

        Input: s = "abcdefg", k = 2
        Output: "bacdfeg"
        Example 2:

        Input: s = "abcd", k = 2
        Output: "bacd"


        Constraints:

        1 <= s.length <= 104
        s consists of only lowercase English letters.
        1 <= k <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reverseStr("abcdefg", 2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static void reverse(char[] arr, int i, int j){
            while(i < j){
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        static public String reverseStr(String s, int k) {
            int i = 0; int n = s.length();
            char[] arr = s.toCharArray();
            while(i < n){
                int j = Math.min(i+k-1,n-1);
                reverse(arr,i,j);
                i = i + 2*k;
            }
            String str = new String(arr);
            return str;
        }
    }
}
