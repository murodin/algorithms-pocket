import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

public class CheckIfAnOriginalStringExistsGivenTwoEncodedStrings {
    /*
        An original string, consisting of lowercase English letters, can be encoded by the following steps:
        Arbitrarily split it into a sequence of some number of non-empty substrings.
        Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
        Concatenate the sequence as the encoded string.
        For example, one way to encode an original string "abcdefghijklmnop" might be:

        Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
        Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
        Concatenate the elements of the sequence to get the encoded string: "ab121p".
        Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive),
        return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.

        Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.

        Example 1.
        Input: s1 = "internationalization", s2 = "i18n"
        Output: true
        Explanation: It is possible that "internationalization" was the original string.
        - "internationalization"
          -> Split:       ["internationalization"]
          -> Do not replace any element
          -> Concatenate:  "internationalization", which is s1.
        - "internationalization"
          -> Split:       ["i", "nternationalizatio", "n"]
          -> Replace:     ["i", "18",                 "n"]
          -> Concatenate:  "i18n", which is s2
        Example 2.
        Input: s1 = "l123e", s2 = "44"
        Output: true
        Explanation: It is possible that "leetcode" was the original string.
        - "leetcode"
          -> Split:      ["l", "e", "et", "cod", "e"]
          -> Replace:    ["l", "1", "2",  "3",   "e"]
          -> Concatenate: "l123e", which is s1.
        - "leetcode"
          -> Split:      ["leet", "code"]
          -> Replace:    ["4",    "4"]
          -> Concatenate: "44", which is s2.
        Example 3:

        Input: s1 = "a5b", s2 = "c5b"
        Output: false
        Explanation: It is impossible.
        - The original string encoded as s1 must start with the letter 'a'.
        - The original string encoded as s2 must start with the letter 'c'.


        Constraints:

        1 <= s1.length, s2.length <= 40
        s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
        The number of consecutive digits in s1 and s2 does not exceed 3.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.possiblyEquals("internationalization", "i18n"));
    }

    // Time: O(N^3)
    // Space: O(N^3)
    static class Solution {
        static List<boolean [][][]> dp;
        public static boolean possiblyEquals(String s1, String s2) {
            int l1 = s1.length()+1;
            int l2 = s2.length()+1;
            int l = 2000;
            Solution.dp = Arrays.asList(new boolean [l1][l2][l], new boolean [l2][l1][l]);
            return possiblyEquals(0, s1, 0, s2, 0, 0);
        }

        private static boolean possiblyEquals(int i, String s1, int j, String s2, int cnt, int dpIdx){
            int origI = i;
            int origJ = j;
            int origCnt = cnt;
            if(dp.get(dpIdx)[i][j][cnt])
                return false;
            while( (cnt > 0 || i < s1.length()) && j < s2.length()){
                if(cnt > 0){
                    char ch = s2.charAt(j);
                    if(ch >= 'a'){
                        j++;
                        cnt--;
                    } else {
                        Pair<Integer, List<Integer>> cnts = getCnt(j, s2);
                        int newJ = cnts.getKey();
                        List<Integer> cntList = cnts.getValue();
                        for(int currCnt : cntList){
                            if(cnt >= currCnt && possiblyEquals(i, s1, newJ, s2, cnt-currCnt, dpIdx))
                                return true;
                            else if(cnt < currCnt && possiblyEquals(newJ, s2, i, s1, currCnt-cnt, dpIdx^1))
                                return true;
                        }
                        dp.get(dpIdx)[origI][origJ][origCnt] = true;
                        return false;
                    }
                } else{
                    char ch1 = s1.charAt(i);
                    char ch2 = s2.charAt(j);
                    if(ch1 >= 'a' && ch2 >= 'a' && ch1 != ch2){
                        dp.get(dpIdx)[origI][origJ][origCnt] = true;
                        return false;
                    } else if(ch1 >= 'a' && ch2 >= 'a'){
                        i++;j++;
                    } else if(ch1 >= 'a')
                        return possiblyEquals(j, s2, i, s1, cnt, dpIdx^1);
                    else {
                        Pair<Integer, List<Integer>> cnts = getCnt(i, s1);
                        int newI = cnts.getKey();
                        List<Integer> cntList = cnts.getValue();
                        for(int currCnt : cntList){
                            if(possiblyEquals(newI, s1, j, s2, currCnt, dpIdx))
                                return true;
                        }
                        dp.get(dpIdx)[origI][origJ][origCnt] = true;
                        return false;
                    }
                }
            }
            dp.get(dpIdx)[origI][origJ][origCnt] = true;
            return  cnt == 0 && i == s1.length() && j == s2.length();

        }

        private static Pair<Integer, List<Integer>> getCnt(int i, String s){
            int l = 0;
            int idx = i;
            while(idx < s.length() && s.charAt(idx) < 'a'){
                idx++;
                l++;
            }
            return new Pair(i+l, getCnt(i, i+l, s));
        }

        private static List<Integer> getCnt(int i, int j, String s){
            int num = 0;
            if(i == j)
                return Arrays.asList(0);
            List<Integer> res = new ArrayList();
            for(int idx =i; idx < j; idx++){
                num = num*10 + s.charAt(idx) - '0';
                List<Integer> currRes = getCnt(idx+1, j, s);
                for(Integer itr : currRes)
                    res.add(num+itr);
            }
            return res;
        }
    }
}
