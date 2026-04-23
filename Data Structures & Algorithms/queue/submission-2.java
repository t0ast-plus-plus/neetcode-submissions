class Deque {
    private class Node {
        final int value;
        Node next;
        Node prev;

        public Node(final int value) {
            this.value = value;
            next = null;
            prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public Deque() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    public void append(int value) {
        final Node newNode = new Node(value);
        final Node oldLastNode = tail.prev;
        oldLastNode.next = newNode;
        newNode.prev = oldLastNode;
        newNode.next = tail;
        tail.prev = newNode;
        size++;
    }

    public void appendleft(int value) {
        final Node newNode = new Node(value);
        final Node oldFirstNode = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = oldFirstNode;
        oldFirstNode.prev = newNode;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }

        final Node lastNode = tail.prev;
        tail.prev = lastNode.prev;
        lastNode.prev.next = tail;
        size--;

        return lastNode.value;
    }

    public int popleft() {
        if (isEmpty()) {
            return -1;
        }

        final Node firstNode = head.next;
        head.next = firstNode.next;
        firstNode.next.prev = head;
        size--;

        return firstNode.value;
    }
}
