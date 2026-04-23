class TreeMap {
    private Node head;

    private class Node {
        int key;
        int value;
        Node left;
        Node right;

        Node(final int key, final int value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    public TreeMap() {
        head = null;
    }

    public void insert(final int key, final int val) {
        if (head == null) {
            head = new Node(key, val);
            return;
        }

        Node current = head;
        while (current != null) {
            if (key < current.key) {
                // insert if left is open
                if (current.left == null) {
                    // insert
                    current.left = new Node(key, val);
                    return;
                }
                // otherwise move left
                current = current.left;
            } else if (key > current.key) {
                // insert if right is open
                if (current.right == null) {
                    // insert
                    current.right = new Node(key, val);
                    return;
                }
                // otherwise move right
                current = current.right;
            } else {
                // match found, overwrite value
                current.value = val;
                return;
            }
        }
    }

    public int get(final int key) {
        Node current = head;
        while (current != null) {
            if (key < current.key) {
                // look left
                current = current.left;
            } else if (key > current.key) {
                // look right
                current = current.right;
            } else {
                // found
                return current.value;
            }
        }

        // dead end null node = not found
        return -1;
    }

    public int getMin() {
        if (head == null) {
            return -1;
        }

        // keep going left until we can't
        Node current = head;
        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    public int getMax() {
        if (head == null) {
            return -1;
        }

        // keep going right until we can't
        Node current = head;
        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    public void remove(final int key) {
        head = deleteNode(head, key);
    }

    private Node deleteNode(final Node target, final int key) {
        if (target == null) {
            return target;
        }

        if (key < target.key) {
            // look left
            target.left = deleteNode(target.left, key);
        } else if (key > target.key) {
            // look right
            target.right = deleteNode(target.right, key);
        } else { // found
            // move single child up
            if (target.left == null) {
                return target.right;
            }
            if (target.right == null) {
                return target.left;
            }

            // 2 children - determine successor (next greater value = smallest of right side
            // children)
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

    private void appendInOrder(final Node node, final List<Integer> orderedValues) {
        if (node == null) {
            return;
        }

        // go left
        appendInOrder(node.left, orderedValues);

        // add current
        orderedValues.add(node.key);

        // go right
        appendInOrder(node.right, orderedValues);
    }
}
