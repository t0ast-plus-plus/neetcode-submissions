class SegmentTree {
    // segment trees represent an array of values in a binary tree structrue
    // the root node represents the entire range of the array and stores sum of all of its values
    // each child splits the parent's range in half, and stores the sum of that respective half
    // the tree keeps subdividing down into further layers of children until that child only represents one index
    // this means each leaf is representative of a single index and single value in the original array of values
    private Node root;

    private class Node {
        int sum;
        Node leftChild;
        Node rightChild;
        int leftIndex;
        int rightIndex;

        Node(final int sum, final int leftIndex, final int rightIndex) {
            this.sum = sum;
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            this.leftChild = null;
            this.rightChild = null;
        }

        // returns the index around which the children are split
        // left children should go from leftIndex to this value (inclusive)
        // right children should go from (this value + 1) to rightIndex (inclusive)
        public int getMiddleIndex() {
            return (leftIndex + rightIndex) / 2;
        }
    }

    public SegmentTree(final int[] nums) {
        this.root = build(nums, 0, nums.length - 1);
    }

    private Node build(final int[] nums, final int leftIndex, final int rightIndex) {
        if (leftIndex == rightIndex) {
            // range size = 1, therefore sum = value at that index
            return new Node(nums[leftIndex], leftIndex, rightIndex);
        }

        // initialize a node to represent the specified range
        // sum depends on child nodes, so an arbitrary value is used temporarily
        final Node node = new Node(0, leftIndex, rightIndex);

        // split the range in half and (recursively) build child nodes to cover each half
        // middle index will go to the left child in this case
        final int middleIndex = node.getMiddleIndex();
        node.leftChild = build(nums, leftIndex, middleIndex);
        node.rightChild = build(nums, middleIndex + 1, rightIndex);

        // add the sum value of each child node to calculate the sum for this node
        node.sum = node.leftChild.sum + node.rightChild.sum;

        // node has been fully populated and its sum calculated, so it's done
        return node;
    }

    public void update(final int index, final int value) {
        update(root, index, value);
    }

    private void update(final Node node, final int index, final int value) {
        if (node.leftIndex == node.rightIndex) {
            // we have reached the desired leaf node representing *only* the specified index, so set its sum = value
            node.sum = value;
            return;
        }

        // child nodes are divided around a middle index (see build method)
        final int middleIndex = node.getMiddleIndex();

        // find which direction we need to go to find specified index then keep recursively updating
        // along that path
        if (index <= middleIndex) {
            update(node.leftChild, index, value);
        } else {
            update(node.rightChild, index, value);
        }

        // update the sum of this node based on its children's sums
        node.sum = node.leftChild.sum + node.rightChild.sum;
    }

    public int query(final int leftIndex, final int rightIndex) {
        return query(root, leftIndex, rightIndex);
    }

    private int query(final Node node, final int leftIndex, final int rightIndex) {
        if (leftIndex == node.leftIndex && rightIndex == node.rightIndex) {
            // we have reached a node with the exact same range as the query, so its sum is the answer
            return node.sum;
        }

        final int middleIndex = node.getMiddleIndex();
        if (leftIndex > middleIndex) {
            // the start of the query range is greater than or equal to the start of the right child range (middleIndex+1)
            // therefore the entire query range is contained somewhere within the right child branch
            // so we can just repeat the same query range starting from the right child node
            return query(node.rightChild, leftIndex, rightIndex);
        } else if (rightIndex <= middleIndex) {
            // the end of the query range is less than or equal to the end of the left child range (middleIndex)
            // therefore the entire query range is contained somewhere within the left child branch
            // so we can just repeat the same query range starting from that left child node
            return query(node.leftChild, leftIndex, rightIndex);
        } else {
            // the query range spans some amount of each child branch's range, so split the next query steps as follows:
            // query left child, from query's left index to the current node's middle index (the left child's last index)
            // query right child, from middle index + 1 (the right child's starting index) to the query's right index
            // combine the results of each to calculate the result of the query
            return query(node.leftChild, leftIndex, middleIndex)
                + query(node.rightChild, middleIndex + 1, rightIndex);
        }
    }
}
