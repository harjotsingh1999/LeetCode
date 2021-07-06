import java.util.*;

/**
 * A Binary Heap is a Binary Tree with following properties. 1) It’s a complete
 * tree (All levels are completely filled except possibly the last level and the
 * last level has all keys as left as possible). This property of Binary Heap
 * makes them suitable to be stored in an array.
 * 
 * 2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the
 * key at root must be minimum among all keys present in Binary Heap. The same
 * property must be recursively true for all nodes in Binary Tree. Max Binary
 * Heap is similar to MinHeap.
 * 
 * 
 * A Binary Heap is a Complete Binary Tree. A binary heap is typically
 * represented as an array.
 * 
 * The root element will be at Arr[0]. Below table shows indexes of other nodes
 * for the ith node, i.e., Arr[i]:
 * 
 * Arr[(i-1)/2] Returns the parent node
 * 
 * Arr[(2*i)+1] Returns the left child node
 * 
 * Arr[(2*i)+2] Returns the right child node
 * 
 * 
 * 
 * Applications of Heaps: 1) Heap Sort: Heap Sort uses Binary Heap to sort an
 * array in O(nLogn) time.
 * 
 * 2) Priority Queue: Priority queues can be efficiently implemented using
 * Binary Heap because it supports insert(), delete() and extractmax(),
 * decreaseKey() operations in O(logn) time. Binomoial Heap and Fibonacci Heap
 * are variations of Binary Heap. These variations perform union also
 * efficiently.
 * 
 * 3) Graph Algorithms: The priority queues are especially used in Graph
 * Algorithms like Dijkstra’s Shortest Path and Prim’s Minimum Spanning Tree.
 * 
 * 4) Many problems can be efficiently solved using Heaps. See following for
 * example. a) K’th Largest Element in an array. b) Sort an almost sorted array/
 * c) Merge K Sorted Arrays.
 * 
 * Operations on Min Heap:
 * 
 * 1) getMini(): It returns the root element of Min Heap. Time Complexity of
 * this operation is O(1).
 * 
 * 2) extractMin(): Removes the minimum element from MinHeap. Time Complexity of
 * this Operation is O(Logn) as this operation needs to maintain the heap
 * property (by calling heapify()) after removing root.
 * 
 * 3) decreaseKey(): Decreases value of key. The time complexity of this
 * operation is O(Logn). If the decreases key value of a node is greater than
 * the parent of the node, then we don’t need to do anything. Otherwise, we need
 * to traverse up to fix the violated heap property.
 * 
 * 4) insert(): Inserting a new key takes O(Logn) time. We add a new key at the
 * end of the tree. IF new key is greater than its parent, then we don’t need to
 * do anything. Otherwise, we need to traverse up to fix the violated heap
 * property.
 * 
 * 5) delete(): Deleting a key also takes O(Logn) time. We replace the key to be
 * deleted with minum infinite by calling decreaseKey(). After decreaseKey(),
 * the minus infinite value must reach root, so we call extractMin() to remove
 * the key.
 */

public class MaxHeap {

    List<Integer> list = null;
    int heapSize;

    public MaxHeap() {
        list = new ArrayList<>();
        heapSize = 0;
    }

    void insert(int num) {
        // add element to the last
        int addAtIndex = heapSize;
        list.add(addAtIndex, num);
        heapSize += 1;
        int parentIndex = (addAtIndex - 1) / 2;
        if (parentIndex >= 0 && list.get(parentIndex) < num)
            swimUp(addAtIndex);
    }

    // since this is a max heap,
    // if child is larger than parent, it moves up
    private void swimUp(int index) {
        int parentIndex = (index - 1) / 2;
        int ele = list.get(index);
        while (index >= 0 && list.get(parentIndex) < ele) {
            // System.out.println("index= " + index + " parent index= " + parentIndex + "
            // ele= " + ele + " parent= "
            // + list.get(parentIndex));
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
    }

    private void swap(int index, int parentIndex) {
        int temp = list.get(index);
        list.set(index, list.get(parentIndex));
        list.set(parentIndex, temp);
    }

    // get the max(top) element
    // present at position 0;
    public int peek() {
        return list.get(0);
    }

    // get the max, present at root,
    public int poll() {
        int max = list.get(0);
        int lastEleIndex = heapSize - 1;
        swap(0, lastEleIndex);
        list.remove(lastEleIndex);
        heapSize -= 1;
        sinkDown(0);
        return max;
    }

    private void sinkDown(int index) {
        while (true) {
            int leftChildIndex = 2 * index + 1, rightChildIndex = 2 * index + 2, maxChildIndex = index;

            // if left child exists and is greater than this element
            if (leftChildIndex < heapSize && list.get(leftChildIndex) > list.get(maxChildIndex))
                maxChildIndex = leftChildIndex;

            // if right child exists and is greater than this element, and the left child
            if (rightChildIndex < heapSize && list.get(rightChildIndex) > list.get(maxChildIndex))
                maxChildIndex = rightChildIndex;

            // if index was not changed, i.e. this element was largest
            // among its children, then break
            if (maxChildIndex == index)
                break;

            // otherwise swap, and update its index
            swap(index, maxChildIndex);
            index = maxChildIndex;
        }
    }

    public void remove(int key) {
        // linearly search for the key
        for (int i = 0; i < heapSize; i++) {
            if (list.get(i) == key) {
                int lastEleIndex = heapSize - 1;
                swap(i, lastEleIndex);
                list.remove(lastEleIndex);
                heapSize -= 1;

                // now we may have to swim up or sink down
                sinkDown(i);
                swimUp(i);
            }
        }
    }

    public boolean isMaxHeap(int rootIndex) {
        if (rootIndex >= heapSize)
            return true;
        int leftIndex = rootIndex * 2 + 1;
        int rightIndex = rootIndex * 2 + 2;

        // if left child is greater, return false
        if (leftIndex < heapSize && list.get(leftIndex) > list.get(rootIndex))
            return false;
        // if right child is greater return false
        if (rightIndex < heapSize && list.get(rightIndex) > list.get(rootIndex))
            return false;

        return isMaxHeap(leftIndex) && isMaxHeap(rightIndex);
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.heapSort(new int[] { 3, 6, 5, 0, 8, 2, 1, 9 });
        // maxHeap.insert(2);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(4);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(6);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(1);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.poll();
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(7);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(8);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.poll();
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(10);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(100);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.insert(8);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));
        // maxHeap.remove(10);
        // System.out.println(maxHeap.list);
        // System.out.println(maxHeap.isMaxHeap(0));

    }

    /**
     * If heap size is n, then, range of leaves= floor(n/2) to n-1 and internal
     * nodes from 0 to floor(n/2)-1
     */

    // given an array, build a max heap out of it

    /**
     * It is known that leaf nodes always follow the heap property Hence, only need
     * to take care of internal nodes so you just need to heapify(swim down) the
     * internal nodes in the reverse order cuz, to swim down both right and left
     * have to follow heap property which is not true for the root here, but only
     * for the parents of leaf nodes
     */
    void buildHeap(int[] arr) {
        System.out.println(Arrays.toString(arr));
        int size = arr.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(arr, i, size);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(checkMaxHeap(arr, 0));
    }

    private void percolateDown(int[] arr, int i, int size) {
        int leftChildIndex = 2 * i + 1, rightChildIndex = 2 * i + 2, maxChildIndex = i;
        if (leftChildIndex < size && arr[leftChildIndex] > arr[maxChildIndex])
            maxChildIndex = leftChildIndex;
        if (rightChildIndex < size && arr[rightChildIndex] > arr[maxChildIndex])
            maxChildIndex = rightChildIndex;
        if (maxChildIndex != i) {
            int temp = arr[i];
            arr[i] = arr[maxChildIndex];
            arr[maxChildIndex] = temp;
            percolateDown(arr, maxChildIndex, size);
        }
    }

    private boolean checkMaxHeap(int arr[], int curr) {
        int size = arr.length;
        if (curr >= size)
            return true;

        int lchild = curr * 2 + 1, rchild = curr * 2 + 2;
        if (lchild < size && arr[lchild] > arr[curr])
            return false;
        if (rchild < size && arr[rchild] > arr[curr])
            return false;
        return checkMaxHeap(arr, lchild) && checkMaxHeap(arr, rchild);
    }

    public void heapSort(int[] arr) {
        System.out.println("original array= " + Arrays.toString(arr));
        buildHeap(arr);
        System.out.println("build max heap = " + Arrays.toString(arr));
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            percolateDown(arr, 0, i);
        }
        System.out.println("sorted array= " + Arrays.toString(arr));
    }

    /**
     * Given an integer array nums and an integer k, return the k most frequent
     * elements. You may return the answer in any order.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] Example 2:
     * 
     * Input: nums = [1], k = 1 Output: [1]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 105 k is in the range [1, the number of unique elements
     * in the array]. It is guaranteed that the answer is unique.
     * 
     * 
     * Follow up: Your algorithm's time complexity must be better than O(n log n),
     * where n is the array's size.
     */

    // Runtime: 7 ms, faster than 98.74% of Java online submissions for Top K
    // Frequent Elements.
    // Memory Usage: 41.3 MB, less than 91.23% of Java online submissions for Top K
    // Frequent Elements.

    // TC= O(n + n + klogn )
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Pair> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).incrementFreq();
            } else {
                map.put(nums[i], new Pair(nums[i]));
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.freq - o1.freq;
            }
        });

        // putting elements in pq= O(n)
        for (int key : map.keySet()) {
            pq.offer(map.get(key));
        }

        // popping k times= O(klogn)
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().key;
        }
        return ans;
    }

    class Pair {
        int key, freq;

        Pair(int key) {
            this.key = key;
            this.freq = 1;
        }

        void incrementFreq() {
            this.freq += 1;
        }
    }
}
