public class DesignFrontMiddleBackQueue {
    /*
        Design a queue that supports push and pop operations in the front, middle, and back.

        Implement the FrontMiddleBack class:

        FrontMiddleBack() Initializes the queue.
        void pushFront(int val) Adds val to the front of the queue.
        void pushMiddle(int val) Adds val to the middle of the queue.
        void pushBack(int val) Adds val to the back of the queue.
        int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
        int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
        int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
        Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:

        Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
        Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].


        Example 1:

        Input:
        ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
        [[], [1], [2], [3], [4], [], [], [], [], []]
        Output:
        [null, null, null, null, null, 1, 3, 4, 2, -1]

        Explanation:
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushFront(1);   // [1]
        q.pushBack(2);    // [1, 2]
        q.pushMiddle(3);  // [1, 3, 2]
        q.pushMiddle(4);  // [1, 4, 3, 2]
        q.popFront();     // return 1 -> [4, 3, 2]
        q.popMiddle();    // return 3 -> [4, 2]
        q.popMiddle();    // return 4 -> [2]
        q.popBack();      // return 2 -> []
        q.popFront();     // return -1 -> [] (The queue is empty)


        Constraints:

        1 <= val <= 109
        At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    class FrontMiddleBackQueue {
        ListNode dummyStart;
        ListNode dummyTail;
        ListNode mid;
        int size;

        public FrontMiddleBackQueue() {
            dummyStart = new ListNode(0);
            dummyTail = new ListNode(0);
            dummyStart.next = dummyTail;
            dummyTail.prev = dummyStart;
        }

        public void pushFront(int val) {
            ListNode newStart = new ListNode(val);
            newStart.next = dummyStart.next;
            newStart.prev = dummyStart;
            dummyStart.next.prev = newStart;
            dummyStart.next = newStart;
            size++;
            if (size == 1) {
                mid = newStart;
            } else {
                if (size % 2 == 0) {
                    mid = mid.prev;
                }
            }
        }

        public void pushMiddle(int val) {
            if (size == 0) {
                ListNode newMid = new ListNode(val);
                newMid.prev = dummyStart;
                newMid.next = dummyTail;
                dummyStart.next = newMid;
                dummyTail.prev = newMid;
                mid = newMid;
                size++;
                return;
            }
            if (size % 2 == 0) {
                pushNext(val);
            } else {
                pushPrev(val);
            }
            size++;
        }

        private void pushPrev(int val) {
            ListNode newMid = new ListNode(val);
            newMid.next = mid;
            newMid.prev = mid.prev;
            newMid.prev.next = newMid;
            mid.prev = newMid;
            mid = newMid;
        }

        private void pushNext(int val) {
            ListNode newMid = new ListNode(val);
            newMid.next = mid.next;
            newMid.prev = mid;
            mid.next.prev = newMid;
            mid.next = newMid;
            mid = newMid;
        }

        public void pushBack(int val) {
            ListNode newEnd = new ListNode(val);
            newEnd.prev = dummyTail.prev;
            newEnd.next = dummyTail;
            dummyTail.prev.next = newEnd;
            dummyTail.prev = newEnd;
            size++;
            if (size == 1) {
                mid = newEnd;
            } else {
                if (size % 2 != 0) {
                    mid = mid.next;
                }
            }
        }

        public int popFront() {
            if (size > 0) {
                ListNode next = dummyStart.next;
                removeNode(next);
                size--;
                if (size % 2 != 0) {
                    mid = mid.next;
                }
                return next.val;
            }
            return -1;
        }

        private void removeNode(ListNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        public int popMiddle() {
            if (size > 0) {
                int val = mid.val;
                mid.next.prev = mid.prev;
                mid.prev.next = mid.next;
                if (size % 2 == 0) {
                    mid = mid.next;
                } else {
                    mid = mid.prev;
                }
                size--;
                return val;
            }
            return -1;
        }

        public int popBack() {
            if (size > 0) {
                ListNode prev = dummyTail.prev;
                removeNode(prev);
                size--;
                if (size % 2 == 0) {
                    mid = mid.prev;
                }
                return prev.val;
            }
            return -1;
        }

        class ListNode {
            int val;
            ListNode prev;
            ListNode next;

            public ListNode(int val) {
                this.val = val;
            }

        }
    }

    /**
     * Your FrontMiddleBackQueue object will be instantiated and called as such:
     * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
     * obj.pushFront(val);
     * obj.pushMiddle(val);
     * obj.pushBack(val);
     * int param_4 = obj.popFront();
     * int param_5 = obj.popMiddle();
     * int param_6 = obj.popBack();
     */
}
