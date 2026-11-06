/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        final Node newHead = getNodeCopy(head, new HashMap<>());
        return newHead;
    }

    // toCopy - node to be copied
    // newByOld - map of newly copied nodes, keyed by the existing node they copied
    private Node getNodeCopy(Node toCopy, Map<Node,Node> newByOld) {
        if(toCopy == null) {
            return null;
        }

        // check map to see if we already copied this node
        Node newNode = newByOld.get(toCopy);
        if(newNode != null) {
            return newNode;
        }

        // create new node and insert into the map
        newNode = new Node(toCopy.val);
        newByOld.put(toCopy, newNode);

        // recursively obtain node copies for .next and .random
        newNode.next = getNodeCopy(toCopy.next, newByOld);
        newNode.random = getNodeCopy(toCopy.random, newByOld);

        return newNode;
    }
}
