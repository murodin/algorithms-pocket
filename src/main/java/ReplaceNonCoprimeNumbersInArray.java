import java.util.*;

public class ReplaceNonCoprimeNumbersInArray {
    /*
        You are given an array of integers nums. Perform the following steps:
        Find any two adjacent numbers in nums that are non-coprime.
        If no such numbers are found, stop the process.
        Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
        Repeat this process as long as you keep finding two adjacent non-coprime numbers.
        Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary order will lead to the same result.

        The test cases are generated such that the values in the final array are less than or equal to 108.

        Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.

        Example 1.
        Input: nums = [6,4,3,2,7,6,2]
        Output: [12,7,6]
        Explanation:
        - (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [12,3,2,7,6,2].
        - (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [12,2,7,6,2].
        - (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [12,7,6,2].
        - (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,6].
        There are no more adjacent non-coprime numbers in nums.
        Thus, the final modified array is [12,7,6].
        Note that there are other ways to obtain the same resultant array.
        Example 2.
        Input: nums = [2,2,1,1,3,3,3]
        Output: [2,1,1,3]
        Explanation:
        - (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3,3].
        - (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3].
        - (2, 2) are non-coprime with LCM(2, 2) = 2. Now, nums = [2,1,1,3].
        There are no more adjacent non-coprime numbers in nums.
        Thus, the final modified array is [2,1,1,3].
        Note that there are other ways to obtain the same resultant array.


        Constraints:

        1 <= nums.length <= 105
        1 <= nums[i] <= 105
        The test cases are generated such that the values in the final array are less than or equal to 108.
     */

    public static void main(String[] args) {
        int[] nums = {6,4,3,2,7,6,2};
        System.out.println("Solution I: " + Solution_I.replaceNonComprimes(nums));
        System.out.println("Solution II: " + Solution_II.replaceNonCoprimes(nums));
        System.out.println("Solution III: " + Solution_III.replaceNonCoprimes(nums));
    }

    // Time: O(M*log(min(N1,N2))); M where length of the array, N1 and N2 two numbers in array.
    // Space: O(M)
    static class Solution_I {
        public static List<Integer> replaceNonComprimes(int[] nums) {
            int n = nums.length, i;
            Stack<Long> stk = new Stack<Long>();

            for(i = 0; i<n; i++) {
                long x = nums[i];
                while(!stk.isEmpty()) {
                    long gcd = calcGcd(stk.peek(), x);
                    if(gcd==1) break;
                    else {
                        long y = stk.peek(); stk.pop();
                        x = calcLcm(x, y);
                    }
                }
                stk.add(x);
            }

            List<Integer>lst = new ArrayList<Integer>();

            while(!stk.isEmpty()) {
                long x = stk.peek(); stk.pop();
                lst.add((int)x);
            }

            Collections.reverse(lst);
            return lst;
        }

        public static long calcGcd(long a, long b) {
            if(a==0) return b;
            return calcGcd(b%a, a);
        }

        public static long calcLcm(long a, long b) {
            long gcd = calcGcd(a, b);
            return (a*b)/gcd;
        }
    }

    // Time: O(M*log(min(N1,N2))); M where length of the array, N1 and N2 two numbers in array.
    // Space: O(M)
    static class Solution_II {
        public static List<Integer> replaceNonCoprimes(int[] nums) {
            // lcm = (a * b) / gcd
            LinkedList<Integer> result = new LinkedList<>();
            if (nums == null || nums.length == 0) {
                return result;
            }
            if (nums.length == 1) {
                result.add(nums[0]);
                return result;
            }
            for (int number : nums) {
                while (true) {
                    int lastElement = result.isEmpty() ? 1 : result.getLast(); // triggers mainly on the very first element
                    int gcd = findGCD(lastElement, number);
                    if (gcd == 1 ) {
                        break; // we should not want this
                    }
                    number = number * (result.removeLast() / gcd); // done division first to avoid overflow
                }
                result.add(number);
            }
            return result;
        }

        public static int findGCD(int a, int b) {
            if (b == 0) {
                return a;
            }
            return findGCD(b, a % b);
        }
    }

    // Time: O(N*logN)
    // Space: O(N)
    static class Solution_III {
        public static List<Integer> replaceNonCoprimes(int[] A) {
            LinkedList<Integer> res = new LinkedList();
            for (int a : A) {
                while (true) {
                    int last = res.isEmpty() ? 1 : res.getLast();
                    int x = gcd(last, a);
                    if (x == 1) break; // co-prime
                    a *= res.removeLast() / x;
                }
                res.add(a);
            }
            return res;
        }

        private static int gcd(int a, int b) {
            return b > 0 ? gcd(b, a % b) : a;
        }
    }
}
