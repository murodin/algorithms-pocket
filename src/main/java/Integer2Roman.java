public class Integer2Roman {

    /*
        Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
        For example, 2 is written as II in Roman numeral, just two one's added together.
        12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

        Roman numerals are usually written largest to smallest from left to right.
        However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

        I can be placed before V (5) and X (10) to make 4 and 9.
        X can be placed before L (50) and C (100) to make 40 and 90.
        C can be placed before D (500) and M (1000) to make 400 and 900.
        Given an integer, convert it to a roman numeral.

        Example 1.
        Input: num = 3
        Output: "III"
        Example 2.
        Input: num = 4
        Output: "IV"
        Example 3.
        Input: num = 9
        Output: "IX"
        Example 4.
        Input: num = 58
        Output: "LVIII"
        Explanation: L = 50, V = 5, III = 3.
        Example 5.
        Input: num = 1994
        Output: "MCMXCIV"
        Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
     */

    public static void main(String[] args) {
        System.out.println("58 to Roman:" + Solution.intToRoman(58));
        System.out.println("65 to Roman:" + Solution.intToRoman(65));
        System.out.println("1071 to Roman:" + Solution.intToRoman(1071));
        System.out.println("1453 to Roman:" + Solution.intToRoman(1453));
        System.out.println("1923 to Roman:" + Solution.intToRoman(1923));
    }

    /*
        M  - 1000
        CM - 900
        D  - 500
        CD - 400
        C  - 100
        XC - 90
        L  - 50
        XL - 40
        X  - 10
        IX - 9
        V  - 5
        IV - 4
        I  - 1
    */

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String intToRoman(int num) {
            int[] intCode = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
            String[] code = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < intCode.length; i++){
                while(num >= intCode[i]){
                    sb.append(code[i]);
                    num-=intCode[i];
                }
            }
            return sb.toString();
        }
    }


}
