import java.util.ArrayList;
import java.util.Arrays;

public class TheEarliestAndLatestRoundsWherePlayersCompete {
    /*
        There is a tournament where n players are participating.
        The players are standing in a single row and are numbered from 1 to n based on their initial standing position
        (player 1 is the first player in the row, player 2 is the second player in the row, etc.).
        The tournament consists of multiple rounds (starting from round number 1).
        In each round, the ith player from the front of the row competes against the ith player from the end of the row, and the winner advances to the next round.
        When the number of players is odd for the current round, the player in the middle automatically advances to the next round.

        For example, if the row consists of players 1, 2, 4, 6, 7
        Player 1 competes against player 7.
        Player 2 competes against player 6.
        Player 4 automatically advances to the next round.
        After each round is over, the winners are lined back up in the row based on the original ordering assigned to them initially (ascending order).

        The players numbered firstPlayer and secondPlayer are the best in the tournament. They can win against any other player before they compete against each other.
        If any two other players compete against each other, either of them might win, and thus you may choose the outcome of this round.

        Given the integers n, firstPlayer, and secondPlayer, return an integer array containing two values,
        the earliest possible round number and the latest possible round number in which these two players will compete against each other, respectively.

        Example 1.
        Input: n = 11, firstPlayer = 2, secondPlayer = 4
        Output: [3,4]
        Explanation:
        One possible scenario which leads to the earliest round number:
        First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        Second round: 2, 3, 4, 5, 6, 11
        Third round: 2, 3, 4
        One possible scenario which leads to the latest round number:
        First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        Second round: 1, 2, 3, 4, 5, 6
        Third round: 1, 2, 4
        Fourth round: 2, 4
        Example 2.
        Input: n = 5, firstPlayer = 1, secondPlayer = 5
        Output: [1,1]
        Explanation: The players numbered 1 and 5 compete in the first round.
        There is no way to make them compete in any other round.


        Constraints:

        2 <= n <= 28
        1 <= firstPlayer < secondPlayer <= n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.earliestAndLatest(11, 2, 4)));
    }

    // Time:
    // Space:
    static class Solution {
        static int firstPlayer;
        static int secondPlayer;
        static int n;
        static boolean enumerate(ArrayList<Integer> ret, int mask, int start, int end) {
            if(start>=end) {
                ret.add(mask);
                return false;
            } else {
                while((start<end)&&((mask&(1<<start))!=0))
                    start++;
                while((start<end)&&((mask&(1<<end))!=0))
                    end--;
                if(start>=end)
                    return enumerate(ret,mask,start+1,end-1);
                else if(start==firstPlayer&&end==secondPlayer)
                    return true;
                else if(start==firstPlayer||start==secondPlayer)
                    return enumerate(ret,mask|1<<end,start+1,end-1);
                else if(end==firstPlayer||end==secondPlayer)
                    return enumerate(ret,mask|1<<start,start+1,end-1);
                else return enumerate(ret,mask|1<<start,start+1,end-1)||enumerate(ret,mask|1<<end,start+1,end-1);

            }
        }
        static int minDFS(int mask) {
            int start=0,end=n-1;
            ArrayList<Integer> arr=new ArrayList<Integer>();
            if(enumerate(arr,mask,start,end))
                return 1;
            else {
                int q=Integer.MAX_VALUE;
                for(int x:arr)
                    q=Math.min(q,1+minDFS(x));
                return q;
            }
        }
        static int maxDFS(int mask)  {
            int start=0,end=n-1;
            ArrayList<Integer> arr=new ArrayList<Integer>();
            if(enumerate(arr,mask,start,end))
                return 1;
            else {
                int q=Integer.MIN_VALUE;
                for(int x:arr)
                    q=Math.max(q,1+maxDFS(x));
                return q;
            }
        }

        static public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
            Solution.n=n;
            Solution.firstPlayer=firstPlayer-1;
            Solution.secondPlayer=secondPlayer-1;
            return new int[]{minDFS(0),maxDFS(0)};
        }
    }
}
