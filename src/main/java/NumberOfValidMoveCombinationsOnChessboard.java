import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfValidMoveCombinationsOnChessboard {
    /*
        There is an 8 x 8 chessboard containing n pieces (rooks, queens, or bishops).
        You are given a string array pieces of length n, where pieces[i] describes the type (rook, queen, or bishop) of the ith piece.
        In addition, you are given a 2D integer array positions also of length n, where positions[i] = [ri, ci] indicates that the ith piece is currently
        at the 1-based coordinate (ri, ci) on the chessboard.

        When making a move for a piece, you choose a destination square that the piece will travel toward and stop on.

        A rook can only travel horizontally or vertically from (r, c) to the direction of (r+1, c), (r-1, c), (r, c+1), or (r, c-1).
        A queen can only travel horizontally, vertically, or diagonally from (r, c) to the direction of (r+1, c), (r-1, c), (r, c+1), (r, c-1), (r+1, c+1), (r+1, c-1), (r-1, c+1), (r-1, c-1).
        A bishop can only travel diagonally from (r, c) to the direction of (r+1, c+1), (r+1, c-1), (r-1, c+1), (r-1, c-1).
        You must make a move for every piece on the board simultaneously.
        A move combination consists of all the moves performed on all the given pieces. Every second, each piece will instantaneously travel one square towards their destination
        if they are not already at it. All pieces start traveling at the 0th second. A move combination is invalid if, at a given time, two or more pieces occupy the same square.

        Return the number of valid move combinations​​​​​.

        Notes:

        No two pieces will start in the same square.
        You may choose the square a piece is already on as its destination.
        If two pieces are directly adjacent to each other, it is valid for them to move past each other and swap positions in one second.


        Example 1:
        Input: pieces = ["rook"], positions = [[1,1]]
        Output: 15
        Explanation: The image above shows the possible squares the piece can move to.
        Example 2:
        Input: pieces = ["queen"], positions = [[1,1]]
        Output: 22
        Explanation: The image above shows the possible squares the piece can move to.
        Example 3:
        Input: pieces = ["bishop"], positions = [[4,3]]
        Output: 12
        Explanation: The image above shows the possible squares the piece can move to.


        Constraints:

        n == pieces.length
        n == positions.length
        1 <= n <= 4
        pieces only contains the strings "rook", "queen", and "bishop".
        There will be at most one queen on the chessboard.
        1 <= xi, yi <= 8
        Each positions[i] is distinct.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countCombinations(new String[] {"bishop"}, new int[][]{{4,3}}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, 0}};
        static int ans = 0;
        public static int countCombinations(String[] pieces, int[][] positions) {
            List<List<int[]>> list = new ArrayList<>();
            for (int i = 0; i < pieces.length; ++i) {
                List<int[]> me = new ArrayList<>();
                int r = positions[i][0], c = positions[i][1];
                me.add(new int[]{8, 0});
                if (pieces[i].equals("rook") || pieces[i].equals("queen") ) {
                    for (int j = 0; j < 4; ++j) {
                        for (int step = 1; step <= 7; ++step) {
                            int r1 = r + dirs[j][0] * step, c1 = c + dirs[j][1] * step;
                            if (isValid(r1, c1)) me.add(new int[]{j, step});
                            else break;
                        }
                    }
                }
                if (pieces[i].equals("bishop") || pieces[i].equals("queen") ) {
                    for (int j = 4; j < 8; ++j) {
                        for (int step = 1; step <= 7; ++step) {
                            int r1 = r + dirs[j][0] * step, c1 = c + dirs[j][1] * step;
                            if (isValid(r1, c1)) me.add(new int[]{j, step});
                            else break;
                        }
                    }
                }
                list.add(me);
            }
            if (1 == pieces.length) return list.get(0).size();
            backtracing(list, 0, new ArrayList<>(), positions);
            return ans;
        }

        private static boolean isValid(int r, int c) {
            return r >= 1 && r <= 8 && c >= 1 && c <= 8;
        }

        private static void backtracing(List<List<int[]>> list, int index, List<int[]> path, int[][] positions) {
            if (index == list.size()) {
                if (noCollison(path, positions)) ans++;
                return;
            }
            for (int[] i : list.get(index)) {
                path.add(i);
                backtracing(list, index + 1, path, positions);
                path.remove(path.size() - 1);
            }
        }

        private static boolean noCollison(List<int[]> path, int[][] positions) {
            Set<Integer> hs = new HashSet<>();
            for (int step = 1; step <= 7; ++step) {
                hs.clear();
                int arrived = 0;
                for (int i = 0; i < path.size(); ++i) {
                    int r = positions[i][0], c = positions[i][1];
                    int j = path.get(i)[0], maxStep = path.get(i)[1];
                    int r1 = r + dirs[j][0] * Math.min(maxStep, step), c1 = c + dirs[j][1] * Math.min(maxStep, step);
                    hs.add(r1 * 8 + c1);
                    if (step >= path.get(i)[1]) arrived++;
                }
                if (hs.size() != path.size()) {
                    return false;
                }
                if (arrived == path.size()) return true;
            }
            return true;
        }
    }
}
