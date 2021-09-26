import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CustomSet {

    /**
     * Implement hashmap with insert(),remove() and getRandom() in O(1)
     * 
     * bool insert(int val) Inserts an item val into the set if not present. Returns
     * true if the item was not present, false otherwise.
     * 
     * bool remove(int val) Removes an item val from the set if present. Returns
     * true if the item was present, false otherwise.
     * 
     * int getRandom() Returns a random element from the current set of elements.
     * Each element must have the same probability of being returned.
     * 
     */

    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;

    public CustomSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;

        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int index = map.get(val);
            map.remove(val);

            int lastIndexElement = list.get(list.size() - 1);
            list.set(index, lastIndexElement);
            map.put(lastIndexElement, index);
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }
}
