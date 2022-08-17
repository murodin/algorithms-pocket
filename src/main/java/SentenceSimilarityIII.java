public class SentenceSimilarityIII {
    /*
        A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
        For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.
        Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty)
        inside one of these sentences such that the two sentences become equal.
        For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.

        Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.

        Example 1.
        Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
        Output: true
        Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
        Example 2.
        Input: sentence1 = "of", sentence2 = "A lot of words"
        Output: false
        Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.
        Example 3.
        Input: sentence1 = "Eating right now", sentence2 = "Eating"
        Output: true
        Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.


        Constraints:

        1 <= sentence1.length, sentence2.length <= 100
        sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
        The words in sentence1 and sentence2 are separated by a single space.
   */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.areSentencesSimilar("My name is Haley", "My Haley"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean areSentencesSimilar(String sentence1, String sentence2) {
            String[] a= sentence1.split(" ");
            String[] b= sentence2.split(" ");
            int i=0,j=0,n=a.length,m=b.length;
            while(i<n && j<m){
                if(!a[i].equals(b[j])) break;
                i++;
                j++;
            }

            if(i==n|| j==m) return true;

            int p=n-1,q=m-1;

            while(p>=i&&q>=j){
                if(!a[p].equals(b[q])) return false;
                p--;
                q--;
            }
            return p<i||q<j;
        }
    }
}
