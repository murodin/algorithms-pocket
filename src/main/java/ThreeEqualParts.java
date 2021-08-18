public class ThreeEqualParts {

    /*
        You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.

        If it is possible, return any [i, j] with i + 1 < j, such that:

        arr[0], arr[1], ..., arr[i] is the first part,
        arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
        arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
        All three parts have equal binary values.
        If it is not possible, return [-1, -1].

        Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3.
        Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.

        Example.

        Input: arr = [1,0,1,0,1]
        Output: [0,3]
     */

    public static void main(String[] args) {
        int[] testArr = {1, 0, 1, 0, 1};
        System.out.println("Equal Parts:" + Solution.threeEqualParts(testArr));
    }

    static class Solution {
        public static int[] threeEqualParts(int[] arr) {
            int n = arr.length;
            int oneCount = 0;

            //Counting total number of 1's
            for(int i : arr) if(i==1) oneCount++;

            //Base exit condition
            if(oneCount % 3 != 0) return new int[]{-1,-1};

            //If oneCount ==0; return any partition
            if(oneCount == 0) return new int[]{0,n-1};

            int k = oneCount/3;

            //Create Partition
            int i1 = -1, i2 = -1, i3 = -1, j1 = -1, j2 = -1,j3 = -1;
            int currCount = 0;

            for(int i = 0; i < n; i++) {
                if(arr[i] == 1) {
                    currCount+=1;
                    if(currCount == 1) i1 = i;
                    if(currCount == (k+1)) i2 = i;
                    if(currCount == (2*k+1)) i3 = i;

                    if(currCount == k) j1 = i;
                    if(currCount == 2*k) j2 = i;
                    if(currCount == 3*k) j3 = i;
                }
            }

            //Compare 3 partitions

            /*
            //O(N) Space Solution
            int[] part1 = Arrays.copyOfRange(arr, i1, j1+1);
            int[] part2 = Arrays.copyOfRange(arr, i2, j2+1);
            int[] part3 = Arrays.copyOfRange(arr, i3, j3+1);

            if(!Arrays.equals(part1, part2) || !Arrays.equals(part1, part3))
                return new int[]{-1,-1};

            */
            int start = i1, mid = i2,end = i3;
            while(k-- > 0 && arr[start] == arr[mid] && arr[mid] == arr[end]){
                start++;
                mid++;
                end++;
            }

            if(k >= 0) return new int[]{-1,-1};


            //Take care of the trailing zeros
            int first = 0, second = 0, third = 0;
            first = i2-j1-1;
            second = i3-j2-1;
            third = n-j3-1;

            if(third > Math.min(first, second)) return new int[]{-1,-1};

            return new int[]{j1+third, j2+third+1};
        }
    }
}
