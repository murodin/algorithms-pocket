import java.util.Arrays;
import java.util.PriorityQueue;

public class CourseSchedulingIII {

    /*
        N different courses -> 1 ... N
        courses[i] = [duration, lastDay]
        You will start 1st day and you cannot take two or more course simultaneously.

        Return max number of courses that you take
     */

    public static void main(String[] args) {

        int[][] testCourses = {{100,200},{200,1300},{1000,1250},{2000,3200}};
        System.out.println("Max Courses to take: " + Solution.scheduleCourse(testCourses));
    }

    static class Solution {
        public static int scheduleCourse(int[][] courses) {
            Arrays.sort(courses, (a, b) -> a[1]==b[1] ? a[0]-b[0]: a[1]-b[1]);
            PriorityQueue<Integer> durationPQ = new PriorityQueue<>((a, b) -> b-a);
            int maxTime = 0;

            for(int[] course: courses) {
                // Check if we consider current course: if duration <= lastDay
                if(course[0] <= course[1]) {
                    if(course[0]+maxTime < course[1]) {
                        durationPQ.offer(course[0]);
                        maxTime += course[0];
                    } else {
                        // Check if we can swap
                        if(durationPQ.peek() > course[0]) {
                            maxTime -= durationPQ.poll();
                            maxTime += course[0];
                            durationPQ.offer(course[0]);
                        }
                    }
                }

                /*
                    //Short
                    maxTime += course[0];
                    durationPQ.offer(course[0]);
                    if(maxTime > course[1])
                        maxTime -= durationPQ.poll();
                 */

            }
            return durationPQ.size();
        }
    }

}
