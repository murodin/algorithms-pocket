import java.util.PriorityQueue;

public class TimeToCrossABridge {
    /*
        There are k workers who want to move n boxes from an old warehouse to a new one. You are given the two integers n and k, and a 2D
        integer array time of size k x 4 where time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi].

        The warehouses are separated by a river and connected by a bridge. The old warehouse is on the right bank of the river,
        and the new warehouse is on the left bank of the river. Initially, all k workers are waiting on the left side of the bridge. To move the boxes, the ith worker (0-indexed) can :

        Cross the bridge from the left bank (new warehouse) to the right bank (old warehouse) in leftToRighti minutes.
        Pick a box from the old warehouse and return to the bridge in pickOldi minutes. Different workers can pick up their boxes simultaneously.
        Cross the bridge from the right bank (old warehouse) to the left bank (new warehouse) in rightToLefti minutes.
        Put the box in the new warehouse and return to the bridge in putNewi minutes. Different workers can put their boxes simultaneously.
        A worker i is less efficient than a worker j if either condition is met:

        leftToRighti + rightToLefti > leftToRightj + rightToLeftj
        leftToRighti + rightToLefti == leftToRightj + rightToLeftj and i > j
        The following rules regulate the movement of the workers through the bridge :

        If a worker x reaches the bridge while another worker y is crossing the bridge, x waits at their side of the bridge.
        If the bridge is free, the worker waiting on the right side of the bridge gets to cross the bridge. If more than one worker is waiting on the right side,
        the one with the lowest efficiency crosses first.
        If the bridge is free and no worker is waiting on the right side, and at least one box remains at the old warehouse, the worker on the left side of the river
         gets to cross the bridge. If more than one worker is waiting on the left side, the one with the lowest efficiency crosses first.
        Return the instance of time at which the last worker reaches the left bank of the river after all n boxes have been put in the new warehouse.



        Example 1:

        Input: n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
        Output: 6
        Explanation:
        From 0 to 1: worker 2 crosses the bridge from the left bank to the right bank.
        From 1 to 2: worker 2 picks up a box from the old warehouse.
        From 2 to 6: worker 2 crosses the bridge from the right bank to the left bank.
        From 6 to 7: worker 2 puts a box at the new warehouse.
        The whole process ends after 7 minutes. We return 6 because the problem asks for the instance of time at which the last worker reaches the left bank.
        Example 2:

        Input: n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
        Output: 50
        Explanation:
        From 0  to 10: worker 1 crosses the bridge from the left bank to the right bank.
        From 10 to 20: worker 1 picks up a box from the old warehouse.
        From 10 to 11: worker 0 crosses the bridge from the left bank to the right bank.
        From 11 to 20: worker 0 picks up a box from the old warehouse.
        From 20 to 30: worker 1 crosses the bridge from the right bank to the left bank.
        From 30 to 40: worker 1 puts a box at the new warehouse.
        From 30 to 31: worker 0 crosses the bridge from the right bank to the left bank.
        From 31 to 39: worker 0 puts a box at the new warehouse.
        From 39 to 40: worker 0 crosses the bridge from the left bank to the right bank.
        From 40 to 49: worker 0 picks up a box from the old warehouse.
        From 49 to 50: worker 0 crosses the bridge from the right bank to the left bank.
        From 50 to 58: worker 0 puts a box at the new warehouse.
        The whole process ends after 58 minutes. We return 50 because the problem asks for the instance of time at which the last worker reaches the left bank.


        Constraints:

        1 <= n, k <= 104
        time.length == k
        time[i].length == 4
        1 <= leftToRighti, pickOldi, rightToLefti, putNewi <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class Solution {
        public int findCrossingTime(int n, int k, int[][] time) {
            Worker[] worker = new Worker[k];
            for(int i=0;i<k;i++) {
                worker[i] = new Worker(i, time[i][0], time[i][1], time[i][2], time[i][3]);
            }

            //Waiting at left side
            PriorityQueue<Worker> leftToRight = new PriorityQueue<>(k);
            //Picking up old boxes
            PriorityQueue<Worker> pickOld = new PriorityQueue<>(k, (a,b) -> a.time-b.time);
            //Waiting at Right Side
            PriorityQueue<Worker> rightToLeft = new PriorityQueue<>(k);
            //Putting new boxes
            PriorityQueue<Worker> putNew = new PriorityQueue<>(k, (a,b) -> a.time-b.time);
            //Initially all workers waiting at left
            for(int i=0;i<k;i++) {
                leftToRight.offer(worker[i]);
            }
            //Num boxes remaining
            int remaining = n;
            //Current time
            int currentTime = 0;

            while(remaining > 0) {
                //Move the workers from pickOld to rightToLeft or putNew to leftToRight if currentTime >= worker's time to complete the task
                while(!pickOld.isEmpty() && pickOld.peek().time <= currentTime) {
                    Worker w = pickOld.poll();
                    rightToLeft.offer(w);
                }
                while(!putNew.isEmpty() && putNew.peek().time <= currentTime) {
                    Worker w = putNew.poll();
                    leftToRight.offer(w);
                }

                //Move worker on the bridge and update the worker's time to complete the task
                if(!rightToLeft.isEmpty()) {
                    Worker w = rightToLeft.poll();
                    w.time = currentTime + w.RL + w.PN;
                    putNew.offer(w);
                    currentTime = currentTime + w.RL;
                } else if(!leftToRight.isEmpty()) {
                    Worker w = leftToRight.poll();
                    w.time = currentTime + w.LR + w.PO;
                    pickOld.offer(w);
                    remaining--;
                    currentTime = currentTime + w.LR;
                } else {
                    //Bridge is empty and no worker waiting, move currenttime to first person finishing the task
                    currentTime = Integer.MAX_VALUE;
                    if(!pickOld.isEmpty()) {
                        currentTime = Math.min(currentTime, pickOld.peek().time);
                    }
                    if(!putNew.isEmpty()) {
                        currentTime = Math.min(currentTime, putNew.peek().time);
                    }
                }
            }
            //Now empty the right warehouse and keep workers moving
            while(!rightToLeft.isEmpty() || !pickOld.isEmpty()) {
                while(!pickOld.isEmpty() && pickOld.peek().time <= currentTime) {
                    Worker w = pickOld.poll();
                    rightToLeft.offer(w);
                }
                if(!rightToLeft.isEmpty()) {
                    Worker w = rightToLeft.poll();
                    w.time = currentTime + w.RL + w.PN;
                    currentTime = currentTime + w.RL;
                } else {
                    currentTime = pickOld.peek().time;
                }
            }
            return currentTime;
        }

        class Worker implements Comparable<Worker> {
            int index;
            int LR;
            int PO;
            int RL;
            int PN;
            int time;

            Worker(int i, int LR, int PO, int RL, int PN) {
                this.index = i;
                this.LR = LR;
                this.PO = PO;
                this.RL = RL;
                this.PN = PN;
                this.time = 0;
            }

            @Override
            public int compareTo(Worker w) {
                if(this.LR+this.RL!=w.LR+w.RL) {
                    return w.LR+w.RL-this.LR-this.RL;
                }
                return w.index-this.index;
            }

            @Override
            public String toString() {
                return index+":"+time+":["+LR+" "+PO+" "+RL+" "+PN+"]";
            }
        }
    }
}
