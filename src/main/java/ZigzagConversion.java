public class ZigzagConversion {
    /*
        The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
        P   A   H   N
        A P L S I I G
        Y   I   R
        And then read line by line: "PAHNAPLSIIGYIR"
        Write the code that will take a string and make this conversion given a number of rows:
        string convert(string s, int numRows);

        Example 1.
        Input: s = "PAYPALISHIRING", numRows = 3
        Output: "PAHNAPLSIIGYIR"
        Example 2.
        Input: s = "PAYPALISHIRING", numRows = 4
        Output: "PINALSIGYAHRPI"
        Explanation:
        P     I    N
        A   L S  I G
        Y A   H R
        P     I
        Example 3.
        Input: s = "A", numRows = 1
        Output: "A"


        Constraints:

        1 <= s.length <= 1000
        s consists of English letters (lower-case and upper-case), ',' and '.'.
        1 <= numRows <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.convert("PAYPALISHIRING", 3));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String convert(String s, int numRows) {
            if(numRows==1){
                return s;
            }
            StringBuilder str = new StringBuilder();
            int n = s.length();
            int k = 2* (numRows -1);
            for(int i=0;i<numRows;i++){
                int index = i ;
                while(index<n){
                    str.append(s.charAt(index));
                    if(i!=0 && i!=numRows-1){
                        int k1 = k- (2*i);
                        int k2 = index + k1;
                        if(k2<n){
                            str.append(s.charAt(k2));
                        }
                    }
                    index = index + k;
                }
            }
            return str.toString();
        }
    }
}
