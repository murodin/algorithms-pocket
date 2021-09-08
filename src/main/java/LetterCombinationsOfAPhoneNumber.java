import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    /*
        Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
        A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

        Example 1.
        Input: digits = "23"
        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        Example 2.
        Input: digits = ""
        Output: []

        Example 3.
        Input: digits = "2"
        Output: ["a","b","c"]
     */
    public static void main(String[] args) {
        String testDigits = "56";
        System.out.println("Letter Combination: " + Solution.letterCombinations(testDigits));
    }

    static class Solution {
        // Time: O(4^N*N)
        // Space: 0(N)
        static List<String> result=null;
        static String[] mapping = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        public static List<String> letterCombinations(String digits) {
            result = new ArrayList<>();
            if(digits.length()==0) return result;
            dfs(0, digits, new StringBuilder());
            return result;
        }

        static void dfs(int length, String digits, StringBuilder temp){
            if(length == digits.length()){
                result.add(temp.toString());
                return;
            }

            //loop digit present at length index of digits string;
            char ch = digits.charAt(length);
            String str = mapping[ch-'0'];
            for(char c:str.toCharArray()){
                temp.append(c);
                dfs(length+1, digits, temp);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
}
