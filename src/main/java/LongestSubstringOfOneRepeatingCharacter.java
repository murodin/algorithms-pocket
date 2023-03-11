public class LongestSubstringOfOneRepeatingCharacter {
    /*
        You are given a 0-indexed string s.
        You are also given a 0-indexed string queryCharacters of length k and a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.
        The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].
        Return an array lengths of length k where lengths[i] is the length of the longest substring of s consisting of only one repeating character after the ith query is performed.

        Example 1.
        Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
        Output: [3,3,4]
        Explanation:
        - 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
        - 2nd query updates s = "bbbccc".
          The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
        - 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
        Thus, we return [3,3,4].
        Example 2.
        Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
        Output: [2,3]
        Explanation:
        - 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
        - 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
        Thus, we return [2,3].


        Constraints:

        1 <= s.length <= 105
        s consists of lowercase English letters.
        k == queryCharacters.length == queryIndices.length
        1 <= k <= 105
        queryCharacters consists of lowercase English letters.
        0 <= queryIndices[i] < s.length
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class Node{
        int max;
        int prefSt,prefEnd;
        int suffSt,suffEnd;
        Node(int max,int prefSt,int prefEnd,int suffSt,int suffEnd){
            this.max=max;
            this.prefSt=prefSt;
            this.prefEnd=prefEnd;
            this.suffSt=suffSt;
            this.suffEnd=suffEnd;
        }
    }

    class SegmentTree{
        Node [] tree;
        StringBuilder s;
        SegmentTree(String s){
            this.s=new StringBuilder();
            this.s.append(s);
            tree=new Node[4*s.length()];
            build(0,0,s.length()-1);
        }

        Node merge(Node left,Node right,int tl,int tm,int tr){
            int max=Integer.max(left.max,right.max);
            int prefSt=left.prefSt;
            int prefEnd=left.prefEnd;
            int suffSt=right.suffSt;
            int suffEnd=right.suffEnd;

            if(s.charAt(tm)==s.charAt(tm+1)){
                max=Integer.max(max,right.prefEnd-left.suffSt+1);
                if(left.prefEnd-left.prefSt+1==tm-tl+1)
                    prefEnd=right.prefEnd;
                if(right.suffEnd-right.suffSt+1==tr-tm)
                    suffSt=left.suffSt;
            }

            return new Node(max,prefSt,prefEnd,suffSt,suffEnd);
        }

        void build(int pos,int tl,int tr){
            if(tl==tr){
                tree[pos]=new Node(1,tl,tl,tr,tr);
            }else{
                int tm=tl+(tr-tl)/2;
                build(2*pos+1,tl,tm);
                build(2*pos+2,tm+1,tr);

                tree[pos]=merge(tree[2*pos+1],tree[2*pos+2],tl,tm,tr);
            }
        }

        void update(int pos,int tl,int tr,int idx,char ch){
            if(tl==tr){
                tree[pos]=new Node(1,tl,tl,tr,tr);
                s.setCharAt(idx,ch);
                // System.out.println(pos);
            }
            else{
                int tm=tl+(tr-tl)/2;
                if(idx<=tm)
                    update(2*pos+1,tl,tm,idx,ch);
                else
                    update(2*pos+2,tm+1,tr,idx,ch);
                tree[pos]=merge(tree[2*pos+1],tree[2*pos+2],tl,tm,tr);
            }
        }
    }

    class Solution {
        public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
            int k=queryIndices.length;
            SegmentTree tree=new SegmentTree(s);
            for(int i=0;i<k;i++){
                tree.update(0,0,s.length()-1,queryIndices[i],queryCharacters.charAt(i));
                queryIndices[i]=tree.tree[0].max;
            }
            return queryIndices;
        }
    }
}
