/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // edge case handling
        if(node == null) {
            return null;
        }

        // store previously visited/cloned nodes by value
        Map<Integer, Node> clonedNodeByVal = new HashMap<>();
        return cloneNode(node, clonedNodeByVal);
    }

    private Node cloneNode(Node node, Map<Integer, Node> clonedNodeByVal) {
        if(node == null) {
            return null;
        }

        // pull existing cloned node from map if available
        if(clonedNodeByVal.containsKey(node.val)) {
            return clonedNodeByVal.get(node.val);
        }

        // clone new node and add it to the map
        Node clonedNode = new Node(node.val);
        clonedNodeByVal.put(node.val, clonedNode);
        // populate its neighbor nodes recursively
        for(Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneNode(neighbor, clonedNodeByVal));
        }

        return clonedNode;
    }
}