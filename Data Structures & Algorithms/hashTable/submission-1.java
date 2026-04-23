class HashTable {

    class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private int capacity;
    private int size;
    private Node[] table;

    private int hash(int key) {
        return key % capacity;
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        size = 0;
        table = new Node[capacity];
    }

    public void insert(int key, int value) {       
        final int index = hash(key);
        Node current = table[index];

        if(current == null) {
            // index is open, insert directly
            table[index] = new Node(key, value);
            size++;
        } else {
            // index occupied
            Node prev = null;
            while (current != null) {
                if (current.key == key) {
                    // found key match, overwrite
                    current.value = value;
                    return;
                }
                // move forward
                prev = current;
                current = current.next;
            }
            // insert
            prev.next = new Node(key, value);
            size++;

        }

        // it would be better to do this first, but test cases expect otherwise :-(
        if(size >= capacity / 2) {
            resize();
        }
    }

    public int get(int key) {
        Node node = table[hash(key)];
        while (node != null) {
            if(node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return -1;
    }

    public boolean remove(int key) {
        final int index = hash(key);
        Node current = table[index];
        Node prev = null;

        while (current != null)
        {
            // seek match
            if(current.key == key) {
                if(prev != null)
                {
                    // replace previous node's "next" with current (to be deleted) node's "next"
                    prev.next = current.next;
                } else {
                    // move any "next" into the main postion at this table index
                    table[index] = current.next;
                }
                size--;
                return true;
            }

            // move forward
            prev = current;
            current = current.next;
        }

        return false;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void resize() {
        // double capacity
        int oldCap = capacity;
        capacity *= 2;
        Node[] oldTable = table;
        table = new Node[capacity];
        size = 0;

        // must reinsert each, because capacity change = hashing change
        for(int i = 0; i < oldCap; i++)
        {
            Node current = oldTable[i];
            while(current != null)
            {
                insert(current.key, current.value);
                current = current.next;
            }
        }
    }
}
