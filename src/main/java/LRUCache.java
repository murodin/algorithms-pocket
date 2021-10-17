import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    /*
        Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
        Implement the LRUCache class:
        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
        int get(int key) Return the value of the key if the key exists, otherwise return -1.
        void put(int key, int value) Update the value of the key if the key exists.
        Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
        The functions get and put must each run in O(1) average time complexity.

        Example 1.
        Input
        ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
        [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
        Output
        [null, null, null, 1, null, -1, null, -1, 3, 4]

        Explanation
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
     */

    public static void main(String[] args) {
        LRU_Cache lRUCache = new LRU_Cache(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        System.out.println("1: " + lRUCache.get(1));
        lRUCache.put(3, 3);
        System.out.println("1: " + lRUCache.get(2));
        lRUCache.put(4, 4);
        System.out.println("1: " + lRUCache.get(1));
        System.out.println("1: " + lRUCache.get(3));
        System.out.println("1: " + lRUCache.get(4));
    }

    static class LRU_Cache {
        Node head = new Node(0,0), tail = new Node(0,0);
        Map<Integer,Node> map = new HashMap<>();
        int capacity;

        public LRU_Cache(int capacity) {
            this.capacity=capacity;
            head.next=tail;
            tail.prev=head;
        }

        public int get(int key) {
            if(map.containsKey(key)){
                Node data=map.get(key);
                //move data to the top
                //remove the node
                remove(data);
                //insert it
                insert(data);
                return data.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            //if value is already present we move it to top
            if(map.containsKey(key)){
                remove(map.get(key));
            }
            //if cache is full
            if(capacity == map.size()){
                remove(tail.prev);
            }
            //tail.prev --> least recently used
            insert(new Node (key,value));
        }

        void remove(Node node){
            map.remove(node.key);
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }

        void insert(Node node){
            map.put(node.key,node);
            Node headNext=head.next;
            head.next=node;
            node.prev=head;
            headNext.prev=node;
            node.next=headNext;
        }

        class Node{
            Node prev,next;
            int key,value;

            Node (int key,int value){
                this.key=key;
                this.value=value;
            }
        }
    }
}
