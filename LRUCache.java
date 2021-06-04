import java.util.*;

public class LRUCache {
    // Constructor for initializing the cache capacity with the given value.
    LinkedHashMap<Integer, Integer> cache;

    int count = 0, capacity;

    LRUCache(int cap) {
        // code here
        cache = new LinkedHashMap<>(cap) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return count > capacity;
            };
        };
        capacity = cap;
    }

    // Function to return value corresponding to the key.
    public int get(int key) {
        // your code here
        if (cache.containsKey(key)) {
            int value = cache.get(key);
            cache.remove(key);
            cache.put(key, value);
            return value;
        } else
            return -1;
    }

    // Function for storing key-value pair.
    public void set(int key, int value) {
        // your code here
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
        } else {
            cache.put(key, value);
            count += 1;
            // if(count==capacity)
            // {
            // }
            // else
            // {

            // }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.cache.toString());
        lruCache.set(1, 2);
        System.out.println(lruCache.cache.toString());
        lruCache.set(2, 3);
        System.out.println(lruCache.cache.toString());
        lruCache.set(1, 5);
        System.out.println(lruCache.cache.toString());
        lruCache.set(4, 5);
        System.out.println(lruCache.cache.toString());
        lruCache.set(6, 7);
        System.out.println(lruCache.cache.toString());
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.cache.toString());
        lruCache.set(1, 2);
        System.out.println(lruCache.cache.toString());
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.cache.toString());

    }
}