import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SimplifyPath {

    /*
        Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
        In a Unix-style file system, a period '.' refers to the current directory,
        a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
        For this problem, any other format of periods such as '...' are treated as file/directory names.
        The canonical path should have the following format:
        The path starts with a single slash '/'.
        Any two directories are separated by a single slash '/'.
        The path does not end with a trailing '/'.
        The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
        Return the simplified canonical path.

        Example 1.
        Input: path = "/home/"
        Output: "/home"
        Explanation: Note that there is no trailing slash after the last directory name.
        Example 2.
        Input: path = "/../"
        Output: "/"
        Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
        Example 3.
        Input: path = "/home//foo/"
        Output: "/home/foo"
        Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
        Example 4.
        Input: path = "/a/./b/../../c/"
        Output: "/c"
     */

    public static void main(String[] args) {
        System.out.println("Simplify Path: " + Solution_I.simplifyPath("/home/"));
        System.out.println("Simplify Path: " + Solution_II.simplifyPath("/home/"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static String simplifyPath(String path) {
            Stack<String> stack = new Stack<>();
            Deque<String> deque = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            String[] p = path.split("/");

            for(String pp: p) {
                if(!stack.isEmpty() && pp.equals("..")) stack.pop();
                else if(!pp.equals("") && !pp.equals(".") &&  !pp.equals("..")) stack.push(pp);
            }

            if(stack.isEmpty()) return "/";

            while (!stack.isEmpty()) {
                sb.insert(0, stack.pop()).insert(0, "/");
            }

            return sb.toString();
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static String simplifyPath(String path) {
            Deque<String> deque = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            String[] p = path.split("/");

            for(String pp: p) {
                if(!deque.isEmpty() && pp.equals("..")) deque.poll();
                else if(!pp.equals("") && !pp.equals(".") &&  !pp.equals("..")) deque.push(pp);
            }

            if(deque.isEmpty()) return "/";

            while (!deque.isEmpty()) {
                sb.append("/").append(deque.pollLast());
            }

            return sb.toString();
        }
    }


}
