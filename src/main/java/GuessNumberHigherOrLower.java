public class GuessNumberHigherOrLower {
    /*
        We are playing the Guess Game. The game is as follows:
        I pick a number from 1 to n. You have to guess which number I picked.
        Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
        You call a pre-defined API int guess(int num), which returns three possible results:

        -1: Your guess is higher than the number I picked (i.e. num > pick).
        1: Your guess is lower than the number I picked (i.e. num < pick).
        0: your guess is equal to the number I picked (i.e. num == pick).
        Return the number that I picked.

        Example 1.
        Input: n = 10, pick = 6
        Output: 6
        Example 2.
        Input: n = 1, pick = 1
        Output: 1
        Example 3.
        Input: n = 2, pick = 1
        Output: 1


        Constraints:

        1 <= n <= 231 - 1
        1 <= pick <= n
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(N)
    // Space:O(1)
    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is higher than the picked number
     *			      1 if num is lower than the picked number
     *               otherwise return 0
     * int guess(int num);
     */

    class GuessGame {
        public int guess(int a){return 0;};
        public int guessNumber(int n) {return 1; }
    }

    public class Solution extends GuessGame {
        public int guessNumber(int n) {
            long s = 1;
            while (s <= n) {
                int x = (int)((n + s) / 2);
                int g = guess(x);
                if (g == 0)  return x;
                if (g < 0)   n = x - 1;
                if (g > 0)   s = x + 1;
            }
            return -1;
        }
    }
}
