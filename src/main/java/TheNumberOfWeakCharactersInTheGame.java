import java.util.Arrays;

public class TheNumberOfWeakCharactersInTheGame {

    /*
        You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense.
         You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
        A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels.
        More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.

        Return the number of weak characters.

        Example 1.
        Input: properties = [[5,5],[6,3],[3,6]]
        Output: 0
        Explanation: No character has strictly greater attack and defense than the other.
        Example 2.
        Input: properties = [[2,2],[3,3]]
        Output: 1
        Explanation: The first character is weak because the second character has a strictly greater attack and defense.
        Example 3.
        Input: properties = [[1,5],[10,4],[4,3]]
        Output: 1
        Explanation: The third character is weak because the second character has a strictly greater attack and defense.


        Constraints:

        2 <= properties.length <= 105
        properties[i].length == 2
        1 <= attacki, defensei <= 105
     */

    public static void main(String[] args) {
        int[][] properties = {{1,5},{10,4},{4,3}};
        System.out.println("Solution I: " + Solution_I.numberOfWeakCharacters(properties));
        System.out.println("Solution II: " + Solution_II.numberOfWeakCharacters(properties));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_I  {
        public static int numberOfWeakCharacters(int[][] p) {
            Arrays.sort(p, (a, b) -> (a[0] == b[0]) ? b[1] - a[1] : a[0] - b[0] );
            int max = p[p.length - 1][1], count = 0;
            for(int i = p.length - 2; i >= 0; i--) {
                if(p[i][1] < max) {
                    count++;
                }
                max = Math.max(max,p[i][1]);
            }
            return count;
        }
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_II {
        public static int numberOfWeakCharacters(int[][] arr) {
            Arrays.sort(arr , (a,b)-> b[0]-a[0]);     // sorting according to decreasing attacking power
            int max = 0 , temp , n = arr.length;
            int count = 0 , i = 0;

            while(i<n) {
                temp = arr[i][1];
                if(arr[i][1]<max)
                    count++;

                while(i<n-1 && arr[i+1][0]==arr[i][0]) {  // If many characters are having same attack then max defence seen wont be updated till we complete all characters with same attack
                    i++;
                    if(arr[i][1]<max)
                        count++;

                    temp = Math.max(temp , arr[i][1]);
                }
                max = Math.max(max , temp);  // updating max defence seen for characters with lesser attack power
                i++;
            }
            return count;
        }
    }
}
