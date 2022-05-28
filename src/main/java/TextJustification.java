import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    /*
        Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

        You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
        Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

        Extra spaces between words should be distributed as evenly as possible.
        If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

        For the last line of text, it should be left-justified and no extra space is inserted between words.

        Note:

        A word is defined as a character sequence consisting of non-space characters only.
        Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
        The input array words contains at least one word.


        Example 1:

        Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
        Output:
        [
           "This    is    an",
           "example  of text",
           "justification.  "
        ]
        Example 2:

        Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
        Output:
        [
          "What   must   be",
          "acknowledgment  ",
          "shall be        "
        ]
        Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
        Note that the second line is also left-justified becase it contains only one word.
        Example 3:

        Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
        Output:
        [
          "Science  is  what we",
          "understand      well",
          "enough to explain to",
          "a  computer.  Art is",
          "everything  else  we",
          "do                  "
        ]


        Constraints:

        1 <= words.length <= 300
        1 <= words[i].length <= 20
        words[i] consists of only English letters and symbols.
        1 <= maxWidth <= 100
        words[i].length <= maxWidth
     */

    public static void main(String[] args) {
        String[] words = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;
        System.out.println("Solution: " + Solution.fullJustify(words, maxWidth));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<String> fullJustify(String[] words, int maxWidth) {
            List<String> para = new ArrayList<String>();
            List<String> sent = new ArrayList<String>();
            int currentCount = 0;
            int letterCount = 0;
            int i = 0;
            int fword = 0;

            while(i < words.length) {
                if (currentCount + fword + words[i].length() <= maxWidth) {
                    sent.add(words[i]);
                    letterCount += words[i].length();
                    currentCount += (fword + words[i].length());
                    fword = 1;
                    i++;
                }
                else {
                    if (sent.size() == 1) {
                        para.add(leftAlign(sent, maxWidth));
                    } else {
                        para.add(justify(sent, letterCount, maxWidth+1));
                    }
                    letterCount = 0;
                    currentCount = 0;
                    fword = 0;
                    sent = new ArrayList<String>();
                }
            }

            para.add(leftAlign(sent, maxWidth));

            return para;
        }

        private static String leftAlign(List<String> words, int maxWidth)
        {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for(String s: words) {
                sb.append(s);
                count += s.length();

                if (count < maxWidth) {
                    sb.append(" ");
                    count++;
                }
            }
            while(count < maxWidth) {
                sb.append(" ");
                count++;
            }

            return sb.toString();
        }

        private static String justify(List<String> words, int currentCount, int maxWidth) {
            StringBuilder sb = new StringBuilder();
            int diff = maxWidth - currentCount - 1;
            int dword = diff / (words.size() - 1);
            int rem = diff % (words.size() - 1);
            int count = 0;

            for(int j = 0; j < words.size(); j++) {
                String s = words.get(j);
                sb.append(s);
                count += s.length();

                if (j != words.size() - 1) {
                    for(int i = 0; i < dword; i++) {
                        sb.append(" ");
                        count++;
                    }
                    if (rem > 0) {
                        sb.append(" ");
                        rem--;
                        count++;
                    }
                }
            }
            return sb.toString();
        }
    }
}
