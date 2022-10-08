import java.util.Arrays;

public class MyCalendarIII {
    /*
        A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)
        You are given some events [start, end), after each given event, return an integer k representing the maximum k-booking between all the previous events.
        Implement the MyCalendarThree class:

        MyCalendarThree() Initializes the object.
        int book(int start, int end) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.


        Example 1.
        Input
        ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
        [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
        Output
        [null, 1, 1, 2, 3, 3, 3]

        Explanation
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
        myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
        myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
        myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
        myCalendarThree.book(5, 10); // return 3
        myCalendarThree.book(25, 55); // return 3


        Constraints:

        0 <= start < end <= 109
        At most 400 calls will be made to book.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class MyCalendarThree {
        int[][]sweep;
        int curr;

        public MyCalendarThree() {
            sweep =  new int[800][2];
            curr = 0;
        }

        public int book(int start, int end) {
            sweep[2*curr][0] = start;  sweep[2*curr][1] = +1;
            sweep[2*curr+1][0] = end;  sweep[2*curr+1][1] = -1;
            int max = max(2*curr+1);
            curr++;
            return max;
        }

        public int max(int range){
            Arrays.sort(sweep,0,range+1,(a, b)->{
                if(a[0]==b[0]) return a[1]-b[1];
                return a[0]-b[0];
            });

            int  m = 0; int c = 0;
            for(int i = 0;i<range;i++){
                c += sweep[i][1];
                m = Math.max(c,m);
            }
            return m;
        }
    }
}
