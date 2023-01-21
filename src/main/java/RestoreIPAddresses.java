import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    /*
        A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

        For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
        Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s.
        You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

        Example 1.
        Input: s = "25525511135"
        Output: ["255.255.11.135","255.255.111.35"]
        Example 2.
        Input: s = "0000"
        Output: ["0.0.0.0"]
        Example 3:

        Input: s = "101023"
        Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.restoreIpAddresses("101023"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static List<String> restoreIpAddresses(String s) {
            List<String> addresses = new ArrayList<>();
            List<String> address = new ArrayList<>();
            rec(s, 0, address, addresses);
            return addresses;
        }
        private static void rec(String s, int i, List<String> address, List<String> addresses) {
            if (address.size() == 4) {
                if (i == s.length()) {
                    addresses.add(String.join(".", address));
                }
            } else {
                for (int j = i + 1; j <= i + 3 && j <= s.length(); j++) {
                    String nextInt = s.substring(i, j);
                    if (Integer.parseInt(nextInt) <= 255 && (nextInt.equals("0") || !nextInt.startsWith("0"))) {
                        address.add(nextInt);
                        rec(s, j, address, addresses);
                        address.remove(address.size() - 1);
                    }
                }
            }
        }
    }
}
