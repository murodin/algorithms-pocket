import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GCDSortOfAnArray {
    /*
        You are given an integer array nums, and you can perform the following operation any number of times on nums:
        Swap the positions of two elements nums[i] and nums[j] if gcd(nums[i], nums[j]) > 1 where gcd(nums[i], nums[j]) is the greatest common divisor of nums[i] and nums[j].
        Return true if it is possible to sort nums in non-decreasing order using the above swap method, or false otherwise.

        Example 1.
        Input: nums = [7,21,3]
        Output: true
        Explanation: We can sort [7,21,3] by performing the following operations:
        - Swap 7 and 21 because gcd(7,21) = 7. nums = [21,7,3]
        - Swap 21 and 3 because gcd(21,3) = 3. nums = [3,7,21]
        Example 2.
        Input: nums = [5,2,6,2]
        Output: false
        Explanation: It is impossible to sort the array because 5 cannot be swapped with any other element.
        Example 3.
        Input: nums = [10,5,9,3,15]
        Output: true
        We can sort [10,5,9,3,15] by performing the following operations:
        - Swap 10 and 15 because gcd(10,15) = 5. nums = [15,5,9,3,10]
        - Swap 15 and 3 because gcd(15,3) = 3. nums = [3,5,9,15,10]
        - Swap 10 and 15 because gcd(10,15) = 5. nums = [3,5,9,10,15]


        Constraints:

        1 <= nums.length <= 3 * 104
        2 <= nums[i] <= 105
        Accepted
        6,759
        Submissions
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.gcdSort(new int[]{7, 21, 3}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution{
        private static class UF {
            int[] parent;
            byte[] rank;
            int n;
            UF(int n) {
                this.n = n;
                parent = new int[n];
                rank = new byte[n];
                for(int i = 0; i < n; i++)
                {
                    parent[i] = i;
                }
            }
            private int find(int i) {
                while(i != parent[i]) {
                    parent[i] = parent[parent[i]];
                    i = parent[i];
                }
                return i;
            }

            public boolean connected(int a, int b) {
                return find(a) == find(b);
            }

            public void union(int a, int b) {
                int pa = find(a);
                int pb = find(b);

                if(pa == pb)    return;

                if(rank[pa] > rank[pb]) parent[pb] = pa;
                if(rank[pa] < rank[pb]) parent[pa] = pb;
                else {
                    parent[pb] = pa;
                    rank[pa] ++;
                }
            }
        }

        public static boolean gcdSort(int[] a) {
            int n = a.length;
            Set<Integer> set = new HashSet<>();
            for(int i : a)  set.add(i);

            int max_num = Integer.MIN_VALUE;
            for(int i : a)  max_num = Math.max(max_num, i);

            UF uf = new UF(max_num + 1);
            int[] sorted_arr = new int[n];
            System.arraycopy(a, 0, sorted_arr, 0, n);
            Arrays.sort(sorted_arr);

            //sieve
            boolean[] sieve = new boolean[max_num + 1];
            for(int i = 2; i * 2 <= max_num; i++) {
                if(sieve[i]) continue;
                for(int j = 2; i * j <= max_num; j++) {
                    // i is a prime number
                    // i * j is connected to i (of course if i * j is there in arr)
                    if(set.contains(i * j)) uf.union(i, i * j);
                    sieve[i * j] = true;
                }
            }
            //checking if arr[i] and sorted[i] is in the same group
            for(int i = 0; i < n; i++) {
                if(!uf.connected(a[i], sorted_arr[i])) return false;
            }
            return true;
        }
    }
}
