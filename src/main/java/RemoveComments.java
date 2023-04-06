import java.util.ArrayList;
import java.util.List;

public class RemoveComments {
    /*
        Given a C++ program, remove comments from it. The program source is an array of strings source where source[i] is the ith line of the source code.
        This represents the result of splitting the original source code string by the newline character '\n'.
     */
    public static void main(String[] args) {
        String[] source = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test",
                "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println("Solution: " + Solution.removeComments(source));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static List<String> removeComments(String[] source) {
            List<String> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean mode = false;
            for (String s : source) {
                for (int i = 0; i < s.length(); i++) {
                    if (mode) {
                        if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                            mode = false;
                            i++;
                        }
                    } else {
                        if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                            break;
                        } else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                            mode = true;
                            i++;
                        } else    sb.append(s.charAt(i));
                    }
                }
                if (!mode && sb.length() > 0) {
                    res.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
            return res;
        }
    }

}
