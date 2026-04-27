class MinHeap {
    List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
        heap.add(0); // dummy node, because heap math is easier with root index = 1
    }

    public void push(final int val) {
        // insert new value at the end
        heap.add(val);

        // bubble the new value "up" the heap until it is appropriately positioned
        bubbleUp(heap.size() - 1);
    }

    public Integer pop() {
        final int heapSize = heap.size();

        if (heapSize <= 1) {
            // empty
            return -1;
        }
        if (heapSize == 2) {
            // root only
            return heap.remove(1);
        }

        // store the current root to be returned later
        final int value = heap.get(1);

        // pull out the last node and overwrite the root node with it
        heap.set(1, heap.remove(heapSize - 1));

        // bubble the new root back down and return the old root value when done
        bubbleDown(1);

        return value;
    }

    public Integer top() {
        if (heap.size() <= 1) {
            // empty
            return -1;
        }

        // min value will always be @ root
        return heap.get(1);
    }

    public void heapify(final List<Integer> nums) {
        // reinitialize heap with provided values
        heap = new ArrayList<>(nums.size() + 1);
        heap.add(0);
        heap.addAll(nums);       

        // to "heapify" an unordered heap, start at the last non-leaf node (index=size/2)
        // perform bubble down on that node, move one step towards root, and repeat
        // keep repeating until the root has been bubbled down, at which point we have the heap
        for (int i = heap.size() / 2; i > 0; i--) {
            bubbleDown(i);
        }
    }

    private void bubbleUp(final int index) {
        if (index <= 1) {
            // can't bubble up beyond root
            return;
        }

        // get targeted node's parent, which will always be at target index / 2 (thanks int math!)
        int parent = index / 2;

        if (heap.get(parent) <= heap.get(index)) {
            // parent is smaller, so we're done
            return;
        }

        // swap index node with parent node and keep bubbling up as needed
        swap(index, parent);
        bubbleUp(parent);
    }

    private void bubbleDown(final int index) {
        // get left child index, which will always be target index * 2
        int targetChild = index * 2;

        if (targetChild >= heap.size()) {
            // no children to bubble down into
            return;
        }

        // if right child exists and is the smaller of the two, target it instead
        final int rightChild = targetChild + 1;
        if (rightChild < heap.size() && heap.get(rightChild) < heap.get(targetChild)) {
            targetChild = rightChild;
        }

        // swap with the targeted child then keep attempting to bubble down from that position
        if(heap.get(targetChild) < heap.get(index)) {
            swap(index, targetChild);
            bubbleDown(targetChild);
        }
    }

    private void swap(final int index1, final int index2) {
        final int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
