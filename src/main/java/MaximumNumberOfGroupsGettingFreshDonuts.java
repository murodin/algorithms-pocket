import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfGroupsGettingFreshDonuts {
    /*
        There is a donuts shop that bakes donuts in batches of batchSize. They have a rule where they must serve all of the donuts of a batch
        before serving any donuts of the next batch. You are given an integer batchSize and an integer array groups, where groups[i] denotes that
        there is a group of groups[i] customers that will visit the shop. Each customer will get exactly one donut.
        When a group visits the shop, all customers of the group must be served before serving any of the following groups. A group will be happy if they all get fresh donuts.
        That is, the first customer of the group does not receive a donut that was left over from the previous group.
        You can freely rearrange the ordering of the groups. Return the maximum possible number of happy groups after rearranging the groups.

        Example 1:

        Input: batchSize = 3, groups = [1,2,3,4,5,6]
        Output: 4
        Explanation: You can arrange the groups as [6,2,4,5,1,3]. Then the 1st, 2nd, 4th, and 6th groups will be happy.
        Example 2:

        Input: batchSize = 4, groups = [1,3,2,5,2,2,1,6]
        Output: 4


        Constraints:

        1 <= batchSize <= 9
        1 <= groups.length <= 30
        1 <= groups[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxHappyGroups(3, new int[]{1,2,3,4,5,6}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int maxHappyGroups(int batchSize, int[] groups) {
            int n = groups.length;
            int[] cnt = new int[batchSize];
            int res = 0;
            int remgroup = 0;
            for (int group : groups) {
                int gr = group % batchSize;
                cnt[gr]++;
                if (gr != 0) remgroup++;
            }
            for(int i = 1; i <= batchSize / 2; i++){
                int val = Math.min(cnt[i], cnt[batchSize - i]);
                if(batchSize % 2 == 0 && i == batchSize / 2){
                    val = cnt[i] / 2;
                }
                res += val;
                cnt[i] -= val;
                cnt[batchSize - i] -= val;
                remgroup -= val * 2;
            }
            res += cnt[0];
            res += dfs(0, cnt, remgroup, new HashMap<>());
            return(res);
        }


        public static int dfs(int curr, int[] cnt, int remgroup, Map<String, Integer> memo){
            int n = cnt.length;
            if(remgroup == 0) return(0);
            String key = curr + Arrays.toString(cnt);
            if(memo.containsKey(key)) return(memo.get(key));
            int res = 0;
            if(curr == 0){
                res++;
                curr = n;
            }
            int val = 0;
            for(int i = 1; i < n; i++){
                if(cnt[i] == 0) continue;
                cnt[i]--;
                int currem = remgroup - 1;
                int nextcurr = curr - i;
                if(nextcurr < 0) nextcurr += n;
                val = Math.max(val, dfs(nextcurr, cnt, currem, memo));
                cnt[i]++;
            }
            res += val;
            memo.put(key, res);
            return(res);
        }
    }
}
