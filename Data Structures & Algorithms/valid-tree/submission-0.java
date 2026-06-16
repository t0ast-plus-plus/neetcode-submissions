class Solution {
    public boolean validTree(int n, int[][] edges) {
        
        // Provided edges are undirected and will not include reciprocal values
        // e.g., if [1,2] is provided, [2,1] will *not* be provided.
        // To make life easier for our directed traversal logic, transform edges
        // into 2D int list that includes both directions of values
        List<List<Integer>> edgeList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            edgeList.add(new ArrayList<>(n));
        }

        for(int[] edge : edges) {
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }

        // need to track visited nodes (loop and connection checks)
        Set<Integer> visited = new HashSet<>();

        // begin traversal w/ dummy parent (-1) node value
        if(!traverse(edgeList, -1, 0, visited)) {
            return false;
        }

        // all nodes must be visited for the tree to be valid
        return visited.size() == n;
    }

    private boolean traverse(List<List<Integer>> edgeList, int parent, int curNode, Set<Integer> visited) {
        // loop detection
        if(visited.contains(curNode)) {
            return false;
        }

        // mark visited
        visited.add(curNode);

        // look for connections
        for(int adj : edgeList.get(curNode)) {
            // ignore path back to parent
            if(adj == parent) {
                continue;
            }
            // traverse adjacent nodes
            if(!traverse(edgeList, curNode, adj, visited)) {
                return false;
            }
        }
        
        // no issues found from this node
        return true;
    }
}
