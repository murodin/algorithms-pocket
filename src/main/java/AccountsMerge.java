import java.util.*;

public class AccountsMerge {
    /*
        Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
        and the rest of the elements are emails representing emails of the account.
        Now, we would like to merge these accounts. Two accounts definitely belong to the same person
        if there is some common email to both accounts. Note that even if two accounts have the same name,
        they may belong to different people as people could have the same name. A person can have any number of accounts initially,
        but all of their accounts definitely have the same name.
        After merging the accounts, return the accounts in the following format: the first element of each account is the name,
        and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

        Example 1.
        Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        Explanation:
        The first and second John's are the same person as they have the common email "johnsmith@mail.com".
        The third John and Mary are different people as none of their email addresses are used by other accounts.
        We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
        ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
        Example 2.
        Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
        Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]


        Constraints:

        1 <= accounts.length <= 1000
        2 <= accounts[i].length <= 10
        1 <= accounts[i][j].length <= 30
        accounts[i][0] consists of English letters.
        accounts[i][j] (for j > 0) is a valid email.
     */
    public static void main(String[] args) {
        List<List<String>> acts = List.of(
                List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
                List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com"),
                List.of("John","johnnybravo@mail.com")
        );
        System.out.println("Solution: " + Solution.accountsMerge(acts));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static List<List<String>> accountsMerge(List<List<String>> acts) {
            Map<String, String> owner = new HashMap<>();
            Map<String, String> parents = new HashMap<>();
            Map<String, TreeSet<String>> unions = new HashMap<>();
            for (List<String> a : acts) {
                for (int i = 1; i < a.size(); i++) {
                    parents.put(a.get(i), a.get(i));
                    owner.put(a.get(i), a.get(0));
                }
            }
            for (List<String> a : acts) {
                String p = find(a.get(1), parents);
                for (int i = 2; i < a.size(); i++)
                    parents.put(find(a.get(i), parents), p);
            }
            for(List<String> a : acts) {
                String p = find(a.get(1), parents);
                unions.computeIfAbsent(p, s -> new TreeSet<>());
                for (int i = 1; i < a.size(); i++)
                    unions.get(p).add(a.get(i));
            }
            List<List<String>> res = new ArrayList<>();
            for (Map.Entry<String, TreeSet<String>> p : unions.entrySet()) {
                List<String> emails = new ArrayList<>(unions.get(p.getKey()));
                emails.add(0, owner.get(p.getKey()));
                res.add(emails);
            }
            return res;
        }
        private static String find(String s, Map<String, String> p) {
            return Objects.equals(p.get(s), s) ? s : find(p.get(s), p);
        }
    }
}
