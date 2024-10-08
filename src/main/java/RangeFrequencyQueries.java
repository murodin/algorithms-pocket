import java.util.*;

public class RangeFrequencyQueries {
    /*
        Design a data structure to find the frequency of a given value in a given subarray.

        The frequency of a value in a subarray is the number of occurrences of that value in the subarray.

        Implement the RangeFreqQuery class:

        RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
        int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
        A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray
        that contains the elements of nums between indices left and right (inclusive).

        Example 1:
        Input
        ["RangeFreqQuery", "query", "query"]
        [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
        Output
        [null, 1, 2]

        Explanation
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
        rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
        rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.


        Constraints:

        1 <= arr.length <= 105
        1 <= arr[i], value <= 104
        0 <= left <= right < arr.length
        At most 105 calls will be made to query
     */
    public static void main(String[] args) {
        System.out.println("Done. ");
    }

    class RangeFreqQuery {
        Map<Integer, List<Integer>> map;
        public RangeFreqQuery(int[] arr) {
            map = new HashMap<>();
            for(int i=0;i<arr.length;i++){
                if(!map.containsKey(arr[i])) map.put(arr[i],new ArrayList<>());
                map.get(arr[i]).add(i);
            }
        }

        public int query(int left, int right, int value) {
            if(!map.containsKey(value)) return 0;
            List<Integer> list=map.get(value);
            int s = Collections.binarySearch(list, left);
            int e = Collections.binarySearch(list, right);

            if(s<0) s=(s+1)*(-1);
            if(e<0) e=(e+2)*(-1);
            return e-s+1;
        }
    }

    /**
     * Your RangeFreqQuery object will be instantiated and called as such:
     * RangeFreqQuery obj = new RangeFreqQuery(arr);
     * int param_1 = obj.query(left,right,value);
     */
}
