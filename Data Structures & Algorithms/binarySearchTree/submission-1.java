class TreeMap {
    private Node head;

    private class Node {
        int key;
        int value;
        Node left;
        Node right;

        Node(final int key, final int value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    public TreeMap() {
        head = null;
    }

    public void insert(int key, int val) {
        if(head == null)
        {
            head = new Node(key, val);
            printTree();
            return;
        }

        Node current = head;
        while(current != null)
        {
            if(key < current.key) {
                // look left
                if(current.left == null)
                {   // insert
                    current.left = new Node(key, val);
                    printTree();
                    return;
                }
                // keep moving down
                current = current.left;
            } else if(key > current.key) {
                // look right
                if(current.right == null)
                {
                    // insert
                    current.right = new Node(key, val);
                    printTree();
                    return;
                }
                // keep moving down
                current = current.right;
            } else { // replace
                current.value = val;
                printTree();
                return;
            }
        }
    }

    private Node getNode(int key)
    {
        Node current = head;
        while (current != null)
        {
            if(key < current.key) {
                current = current.left; // look left
            } else if(key > current.key) {
                current = current.right; // look right
            } else {
                break; // found
            }
        }
        return current;
    }

    public int get(int key) {
        final Node found = getNode(key);
        return found == null ? -1 : found.value;
    }

    public int getMin() {
        if(head == null)
        {
            return -1;
        }

        Node current = head;
        while (current.left != null)
        {
            current = current.left;
        }

        return current.value;
    }

    public int getMax() {
        if(head == null)
        {
            return -1;
        }

        Node current = head;
        while (current.right != null)
        {
            current = current.right;
        }

        return current.value;
    }

    public void remove(int key) {
       head = deleteNode(head, key);
    }

    private Node deleteNode(Node target, int key)
    {
        if(target == null) {
            return target;
        }

        if(key < target.key) {
            // look left
            target.left = deleteNode(target.left, key);
        } else if (key > target.key) {
            // look right
            target.right = deleteNode(target.right, key);
        } else { // found
            // move single child up
            if(target.left == null) {
                return target.right;
            }
            if(target.right == null) {
                return target.left;
            }

            // 2 children - determine successor (next greater value = smallest of right side children)
            Node successor = target.right; // go right once
            while (successor != null && successor.left != null) {
                successor = successor.left; // keep looking left
            }
            // overwrite deletion target key+value using successor
            target.key = successor.key;
            target.value = successor.value;
            
            // go delete the successor
            target.right = deleteNode(target.right, successor.key);
        }

        return target;
    }

    public List<Integer> getInorderKeys() {
        final List<Integer> orderedValues = new ArrayList<>();
        appendInOrder(head, orderedValues);
        return orderedValues;
    }

    private void appendInOrder(final Node node, final List<Integer> orderedValues)
    {
        if(node == null)
        {
            return;
        }

        appendInOrder(node.left, orderedValues);
        orderedValues.add(node.key);
        appendInOrder(node.right, orderedValues);
    }

    private void printTree()
    {
        System.out.println(getInorderKeys());
    }
}
