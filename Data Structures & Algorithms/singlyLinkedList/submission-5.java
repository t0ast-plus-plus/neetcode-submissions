class LinkedList {
    private Node head;
    private Node tail;

    private class Node {
        int value;
        Node next;

        Node(final int value, final Node next)
        {
            this.value = value;
            this.next = next;
        }
    }

    public LinkedList() {
        head = null;
        tail = null;
    }

    private void printList()
    {
        if (head == null)
        {
            System.out.println("empty");
            return;
        }

        Node current = head;
        System.out.println("nodes:");
        do {
            if (current == head) {
                System.out.println("following is head");
            }
            if (current == tail) {
                System.out.println("following is tail");
            }
            System.out.println(current.value);
            current = current.next;
        } while (current != null);
    }

    private Node getNode(final int index)
    {
        if (head == null || index == 0) {
            return head;
        }

        Node currentNode = head;
        int currentIndex = 0;
        do {
            currentNode = currentNode.next;
            currentIndex++;
        } while (currentIndex < index && currentNode.next != null);

        if (currentIndex == index) {
            return currentNode;
        }

        return null;
    }

    public int get(int index) {
        final Node nodeAtIndex = getNode(index);
        if(nodeAtIndex == null) {
            return -1;
        }

        return nodeAtIndex.value;
    }

    public void insertHead(int val) {
        final Node newHead = new Node(val, head);
        head = newHead;
        
        if (tail == null) {
            tail = newHead;
        }

        printList();
    }

    public void insertTail(int val) {
        final Node newTail = new Node(val, null);
        if(head == null)
        {
            head = newTail;
            tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }

        printList();
    }

    public boolean remove(int index) {
        if (head == null) {
            return false;
        }

        if (index == 0) {
            head = head.next;
            return true;
        }

        final Node nodeBeforeIndex = getNode(index-1);
        if(nodeBeforeIndex == null || nodeBeforeIndex.next == null) {
            return false;
        }

        if(nodeBeforeIndex.next == tail)
        {
            nodeBeforeIndex.next = null;
        } else {
            nodeBeforeIndex.next = nodeBeforeIndex.next.next;
        }

        if(head.next == null)
        {
            tail = head;
        }
        
        printList();

        return true;
    }

    public ArrayList<Integer> getValues() {
        final ArrayList<Integer> values = new ArrayList<>();
        if(head == null) {
            return values;
        }

        Node current = head;
        do {
            values.add(current.value);
            current = current.next;
        } while (current != null);

        return values;
    }
}
