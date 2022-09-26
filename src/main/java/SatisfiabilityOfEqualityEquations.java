public class SatisfiabilityOfEqualityEquations {
    /*
        You are given an array of strings equations that represent relationships between variables
         where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".
         Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
        Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.

        Example 1.
        Input: equations = ["a==b","b!=a"]
        Output: false
        Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
        There is no way to assign the variables to satisfy both equations.
        Example 2.
        Input: equations = ["b==a","a==b"]
        Output: true
        Explanation: We could assign a = 1 and b = 1 to satisfy both equations.


        Constraints:

        1 <= equations.length <= 500
        equations[i].length == 4
        equations[i][0] is a lowercase letter.
        equations[i][1] is either '=' or '!'.
        equations[i][2] is '='.
        equations[i][3] is a lowercase letter.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.equationsPossible(new String[] {"b==a","a==b"}));
    }

    // Time:
    // Space:
    static class Solution {
        static int[] par;
        public static int findLeaderParent(int u){
            return par[u]==u ? u : (par[u]=findLeaderParent(par[u]));
        }

        public static boolean equationsPossible(String[] equations) {
            par=new int[26];
            for(int i=0;i<26;i++){
                par[i]=i;
            }

            int[] notArray=new int[26];
            for(String str : equations){
                if(str.charAt(1) == '='){
                    int p1=findLeaderParent(str.charAt(0) - 'a');
                    int p2=findLeaderParent(str.charAt(3) - 'a');
                    if(p1!=p2){
                        par[p2]=p1;
                    }
                }
            }

            for(String str : equations){
                if(str.charAt(1)=='!'){
                    int p1=findLeaderParent(str.charAt(0) - 'a');
                    int p2=findLeaderParent(str.charAt(3) - 'a');
                    if(p1==p2){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
