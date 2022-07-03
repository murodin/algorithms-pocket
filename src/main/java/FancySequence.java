import java.util.Arrays;

public class FancySequence {
    /*
        Write an API that generates fancy sequences using the append, addAll, and multAll operations.
        Implement the Fancy class:
        Fancy() Initializes the object with an empty sequence.
        void append(val) Appends an integer val to the end of the sequence.
        void addAll(inc) Increments all existing values in the sequence by an integer inc.
        void multAll(m) Multiplies all existing values in the sequence by an integer m.
        int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.


        Example 1.
        Input
        ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
        [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
        Output
        [null, null, null, null, null, 10, null, null, null, 26, 34, 20]

        Explanation
        Fancy fancy = new Fancy();
        fancy.append(2);   // fancy sequence: [2]
        fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
        fancy.append(7);   // fancy sequence: [5, 7]
        fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
        fancy.getIndex(0); // return 10
        fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
        fancy.append(10);  // fancy sequence: [13, 17, 10]
        fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
        fancy.getIndex(0); // return 26
        fancy.getIndex(1); // return 34
        fancy.getIndex(2); // return 20


        Constraints:

        1 <= val, inc, m <= 100
        0 <= idx <= 105
        At most 105 calls total will be made to append, addAll, multAll, and getIndex.
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time: O()
    // Space: O()
    static class Fancy {
        private static final int MOD = 1000000007;
        private int[] values = new int[8];
        private long add = 0;                       //addend
        private long mult = 1;                      //multiplier
        private long r_mult = 1;                    //multiplicative inverse
        private int size = 0;                       //append times
        private static int[] INVERSES = cache();    //multiplicative inverse cache

        public void append(int val) {
            //To prevent negative numbers from appearing in the numerator, add MOD to val-add
            long result = (val - add + MOD) * r_mult % MOD;
            //Expand if the array is full
            if (size >= values.length)
                values = Arrays.copyOf(values, size + (size << 1));
            values[size++] = ((int) result);
        }
        public void addAll(int inc) {
            add = (add + inc) % MOD;
        }
        public void multAll(int m) {
            mult = mult * m % MOD;
            add = add * m % MOD;
            //Update multiplicative inverse:
            //Multiplicative inverse element is multiplicative,so r_mult = r_mult * INVERSES[m] % MOD
            //According to the test, there m <= 100, so the multiplicative inverse element in the interval [0-100] can be stored in the table and used directly
            //If m is not limited, then r_mult = multiplicativeInverse(m, MOD) % MOD
            r_mult = r_mult * INVERSES[m] % MOD;
        }
        public int getIndex(int idx) {
            if (idx >= size) return -1;
            return (int) ((mult * values[idx] + add) % MOD);
        }
        // Calculate the inverse element
        // 1/a % p = a^(p-2) % p
        static int multiplicativeInverse(int x, int mod) {
            long y = 1, m = mod - 2, p = x;
            //Similar to the fast exponentiation operation, if the modulus is taken for each step of the judgment, it is a fast exponentiation modular operation.
            for (int i = 0; 1L << i < m; i++, p = p * p % mod)
                if ((m >> i & 1) == 1) y = y * p % mod;
            return (int) y;
        }
        static int[] cache() {
            INVERSES = new int[101];
            INVERSES[0] = 0;
            INVERSES[1] = 1;
            for (int i = 2; i < INVERSES.length; i++)
                INVERSES[i] = multiplicativeInverse(i, MOD);
            return INVERSES;
        }
    }
}
