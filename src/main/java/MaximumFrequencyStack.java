import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {

    /*
        Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
        Implement the FreqStack class:

        FreqStack() constructs an empty frequency stack.
        void push(int val) pushes an integer val onto the top of the stack.
        int pop() removes and returns the most frequent element in the stack.
        If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.

        Example 1.
        Input
        ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
        [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
        Output
        [null, null, null, null, null, null, null, 5, 7, 5, 4]

        Explanation
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
     */

    public static void main(String[] args) {
        FreqStack_I freqStack = new FreqStack_I();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
    }

    // Time: O(1) Push and Pop
    //Using Stack
    static class FreqStack_I {
        Map<Integer, Integer> freqMap;
        Map<Integer, Stack<Integer>> freqStack;
        int maxFreq;
        public FreqStack_I() {
            freqMap = new HashMap<>();
            freqStack = new HashMap<>();
            maxFreq = 0;
        }
        //Increment value in freqMap,
        //updating the maxFreq
        //Adding value in dfreqStack
        public void push(int x) {
            int freq = freqMap.getOrDefault(x,0)+1;
            freqMap.put(x, freq);
            if(freq > maxFreq) maxFreq = freq;
            freqStack.computeIfAbsent(freq, f->new Stack()).push(x);
        }

        //Return and remove the top of maxFreq
        //Update maxFreq (Decrementing, if applicable)
        //Update freqMap
        public int pop() {
            Stack<Integer> s = freqStack.get(maxFreq);
            int top = s.pop();
            if(s.isEmpty()) maxFreq--;
            freqMap.put(top, freqMap.get(top)-1);
            return top;
        }
    }

    //Using ArrayList
    static class FreqStack_II {
        Map<Integer, Integer> freqMap;
        ArrayList<ArrayList<Integer>> freqStack;
        int maxFreq;
        public FreqStack_II() {
            freqMap = new HashMap<>();
            freqStack = new ArrayList<>();
            freqStack.add(new ArrayList<Integer>());
            maxFreq = 0;
        }
        //Increment value in freqMap,
        //updating the maxFreq
        //Adding value in dfreqStack
        public void push(int x) {
            int freq = freqMap.getOrDefault(x,0)+1;
            freqMap.put(x, freq);
            if(freq > maxFreq) maxFreq = freq;
            //freqStack.computeIfAbsent(freq, f->new Stack()).push(x);
            if(freqStack.size() <= freq) freqStack.add(new ArrayList());
            freqStack.get(freq).add(x);
        }

        //Return and remove the top of maxFreq
        //Update maxFreq (Decrementing, if applicable)
        //Update freqMap
        public int pop() {
            ArrayList<Integer> s = freqStack.get(maxFreq);
            int top = s.remove(s.size()-1);
            if(s.isEmpty()) maxFreq--;
            freqMap.put(top, freqMap.get(top)-1);
            return top;
        }
    }
}
