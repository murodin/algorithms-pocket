import java.util.HashSet;
import java.util.PriorityQueue;

public class SmallestNumberInInfiniteSet {
    /*
        You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

        Implement the SmallestInfiniteSet class:

        SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
        int popSmallest() Removes and returns the smallest integer contained in the infinite set.
        void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.


        Example 1:

        Input
        ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
        [[], [2], [], [], [], [1], [], [], []]
        Output
        [null, null, 1, 2, 3, null, 1, 4, 5]

        Explanation
        SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
        smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
        smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
        smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
        smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
        smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
        smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                           // is the smallest number, and remove it from the set.
        smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
        smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.


        Constraints:

        1 <= num <= 1000
        At most 1000 calls will be made in total to popSmallest and addBack.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Your SmallestInfiniteSet object will be instantiated and called as such:
     * SmallestInfiniteSet obj = new SmallestInfiniteSet();
     * int param_1 = obj.popSmallest();
     * obj.addBack(num);
     */

    class SmallestInfiniteSet {
        private HashSet<Integer> isPresent;
        private PriorityQueue<Integer> addedIntegers;
        private Integer currentInteger;

        public SmallestInfiniteSet() {
            isPresent = new HashSet<>();
            addedIntegers = new PriorityQueue<>();
            currentInteger = 1;
        }

        public int popSmallest() {
            int answer;
            if (!addedIntegers.isEmpty()) {
                answer = addedIntegers.poll();
                isPresent.remove(answer);
            } else {
                answer = currentInteger;
                currentInteger += 1;
            }
            return answer;
        }

        public void addBack(int num) {
            if (currentInteger <= num || isPresent.contains(num)) {
                return;
            }
            addedIntegers.add(num);
            isPresent.add(num);
        }
    }
}
