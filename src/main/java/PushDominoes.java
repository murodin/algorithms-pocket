public class PushDominoes {
    /*
        There are n dominoes in a line, and we place each domino vertically upright.
        In the beginning, we simultaneously push some dominoes either to the left or to the right.
        After each second, each domino that is falling to the left pushes the adjacent domino on the left.
        Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
        When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
        For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
        You are given a string dominoes representing the initial state where:

        dominoes[i] = 'L', if the ith domino has been pushed to the left,
        dominoes[i] = 'R', if the ith domino has been pushed to the right, and
        dominoes[i] = '.', if the ith domino has not been pushed.
        Return a string representing the final state.

        Example 1.
        Input: dominoes = "RR.L"
        Output: "RR.L"
        Explanation: The first domino expends no additional force on the second domino.
        Example 2.
        Input: dominoes = ".L.R...LR..L.."
        Output: "LL.RR.LLRRLL.."

        Constraints:

        n == dominoes.length
        1 <= n <= 105
        dominoes[i] is either 'L', 'R', or '.'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.pushDominoes(".L.R...LR..L.."));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String pushDominoes(String dominoes) {
            //  adding "L" and "R" on left and right (respectively) will not cause any change
            dominoes = "L" + dominoes + "R";
            char[] a = dominoes.toCharArray();
            int l = 0;
            for (int i = 0; i < a.length; i++) {
                while (a[i] == '.') {
                    //  skip all the dominoes that are not initially pushed
                    i++;
                }
                //  four cases possible
                if (a[l] == 'L' && a[i] == 'L') {
                    while (l != i) a[l++] = 'L';
                } else if(a[l] == 'R' && a[i] == 'L') {
                    //  two cases of even number of non pushed dominoes or odd
                    //  both cases are handled in the while loop
                    int oi = i;    //  saving original i
                    while (l < i) {
                        a[l++] = 'R';
                        a[i--] = 'L';
                    }
                    i = oi;
                } else if(a[l] == 'L' && a[i] == 'R') {
                    //  no change inside the [L...R] pair
                } else if(a[l] == 'R' && a[i] == 'R') {
                    while (l != i) a[l++] = 'R';
                }
                l = i;
            }
            // removing the initial changes we made
            return (new String(a)).substring(1, a.length - 1);
        }
    }
}
