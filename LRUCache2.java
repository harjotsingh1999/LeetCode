import java.util.*;

public class LRUCache2 {
    class Pair {
        int key, value;

        Pair(int k, int v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public String toString() {
            return "Pair: " + key + "->" + value;
        }
    }

    HashMap<Integer, Pair> map;
    LinkedList<Pair> list = new LinkedList<>();
    int capacity = 0;

    LRUCache2(int cap) {
        // code here
        capacity = cap;
        map = new HashMap<>(cap);
    }

    // Function to return value corresponding to the key.
    public int get(int key) {
        // your code here
        if (map.containsKey(key)) {
            Pair pair = map.get(key);
            list.remove(pair);
            list.push(pair);
            System.out.println("after get key= " + key + " map= " + map + " \nlist= " + list);
            return pair.value;
        } else
            return -1;
    }

    // Function for storing key-value pair.
    public void set(int key, int value) {
        // your code here
        if (map.containsKey(key)) {
            Pair pair = map.get(key);
            list.remove(pair);
            pair.value = value;
            list.push(pair);
        } else {
            // remove from end then add to front
            if (list.size() == capacity) {

                Pair pair = list.removeLast();
                map.remove(pair.key);
                Pair newPair = new Pair(key, value);
                map.put(key, newPair);
                list.push(newPair);
            }
            // directly add to front
            else {
                Pair pair = new Pair(key, value);
                list.push(pair);
                map.put(key, pair);
            }
        }
        System.out.println("after push key= " + key + " value= " + value + " map= " + map + " \n list= " + list);
    }

    public static void main(String[] args) {
        LRUCache2 lruCache = new LRUCache2(2);
        lruCache.set(1, 2);
        lruCache.set(2, 3);
        lruCache.set(1, 5);
        lruCache.set(4, 5);
        lruCache.set(6, 7);
        System.out.println(lruCache.get(4));
        lruCache.set(1, 2);
        System.out.println(lruCache.get(3));
    }
}
