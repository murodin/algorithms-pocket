import java.util.*;

public class MostPopularVideoCreator {
    /*
        You are given two string arrays creators and ids, and an integer array views, all of length n.
        The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.

        The popularity of a creator is the sum of the number of views on all of the creator's videos.
        Find the creator with the highest popularity and the id of their most viewed video.

        If multiple creators have the highest popularity, find all of them.
        If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
        Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest popularity and idi is the id of their most popular video.
        The answer can be returned in any order.



        Example 1:

        Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
        Output: [["alice","one"],["bob","two"]]
        Explanation:
        The popularity of alice is 5 + 5 = 10.
        The popularity of bob is 10.
        The popularity of chris is 4.
        alice and bob are the most popular creators.
        For bob, the video with the highest view count is "two".
        For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically smaller than "three", it is included in the answer.
        Example 2:

        Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
        Output: [["alice","b"]]
        Explanation:
        The videos with id "b" and "c" have the highest view count.
        Since "b" is lexicographically smaller than "c", it is included in the answer.


        Constraints:

        n == creators.length == ids.length == views.length
        1 <= n <= 105
        1 <= creators[i].length, ids[i].length <= 5
        creators[i] and ids[i] consist only of lowercase English letters.
        0 <= views[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    class Solution {
        public List<List<String>> mostPopularCreator(final String[] creators, final String[] ids, final int[] views) {
            final List<List<String>> result = new ArrayList<>();
            final Map<String, Creator> totalViews = new HashMap<>();
            final PriorityQueue<Creator> maxHeap = new PriorityQueue<>((a, b) -> b.totalViews() - a.totalViews());
            final int n = creators.length;

            for(int i = 0; i < n; ++i) {
                totalViews.putIfAbsent(creators[i], new Creator(creators[i], views[i], ids[i]));

                Creator creator = totalViews.get(creators[i]);

                creator.totalViews(creator.totalViews() + views[i]);

                if(views[i] > creator.maxViewsVideo() || (views[i] == creator.maxViewsVideo() && ids[i].compareTo(creator.maxViewsVideoId()) < 0))
                    creator.maxViewsVideo(views[i]).maxViewsVideoId(ids[i]);
            }

            for(Creator creator : totalViews.values())
                maxHeap.offer(creator);

            int prev = maxHeap.peek().totalViews();

            while(!maxHeap.isEmpty() && prev == maxHeap.peek().totalViews()) {
                Creator creator = maxHeap.poll();

                prev = creator.totalViews();
                result.add(Arrays.asList(creator.name(), creator.maxViewsVideoId()));
            }

            return result;
        }

        private final class Creator {
            private String name;
            private int totalViews;
            private int maxViewsVideo;
            private String maxViewsVideoId;

            public Creator(final String name, final int maxViewsVideo, final String maxViewsVideoId) {
                this.name = name;
                this.totalViews = 0;
                this.maxViewsVideo = maxViewsVideo;
                this.maxViewsVideoId = maxViewsVideoId;
            }

            public Creator name(final String name) {
                this.name = name;
                return this;
            }

            public String name() {
                return this.name;
            }

            public Creator totalViews(final int totalViews) {
                this.totalViews = totalViews;
                return this;
            }

            public int totalViews() {
                return this.totalViews;
            }

            public Creator maxViewsVideo(final int maxViewsVideo) {
                this.maxViewsVideo = maxViewsVideo;
                return this;
            }

            public int maxViewsVideo() {
                return this.maxViewsVideo;
            }

            public Creator maxViewsVideoId(final String maxViewsVideoId) {
                this.maxViewsVideoId = maxViewsVideoId;
                return this;
            }

            public String maxViewsVideoId() {
                return this.maxViewsVideoId;
            }
        }
    }
}
