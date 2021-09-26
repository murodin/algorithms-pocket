import java.util.LinkedList;

public class DesignHashMap {

    /*
        Design a HashMap without using any built-in hash table libraries.
        Implement the MyHashMap class:
        MyHashMap() initializes the object with an empty map.
        void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
        int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
        void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.

        Example 1.
        Input
        ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
        [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
        Output
        [null, null, null, 1, -1, null, 1, null, -1]

        Explanation
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
        myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
     */

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // The map is now [[1,1]]
        myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
        myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
        myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
    }

    static class MyHashMap {
        /** Initialize your data structure here. */
        LinkedList<Entry>[] map;
        public static int SIZE = 769;
        public MyHashMap() {
            map = new LinkedList[SIZE];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int bucket = key % SIZE;
            if(map[bucket] == null) {
                map[bucket] = new LinkedList<Entry>();
                map[bucket].add(new Entry(key, value));
            } else {
                for(Entry entry : map[bucket]){
                    if(entry.key == key){
                        entry.val = value;
                        return;
                    }
                }
                map[bucket].add(new Entry(key, value));
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int bucket = key % SIZE;
            LinkedList<Entry> entries = map[bucket];
            if(entries == null) return -1;
            for(Entry entry : entries) {
                if(entry.key == key) return entry.val;
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int bucket = key % SIZE;
            Entry toRemove = null;
            if(map[bucket] == null) return;
            else {
                for(Entry entry : map[bucket]){
                    if(entry.key == key) {
                        toRemove = entry;
                    }
                }
                if(toRemove == null) return;
                map[bucket].remove(toRemove);
            }
        }
    }

    static class Entry {
        public int key;
        public int val;

        public Entry(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
