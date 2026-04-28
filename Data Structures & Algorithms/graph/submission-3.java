class Graph {
    // key = vertex ID, value = edge destinations
    private final HashMap<Integer, HashSet<Integer>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(final int src, final int dst) {
        // add src and dst to graph if they don't exist
        graph.putIfAbsent(src, new HashSet<>());
        graph.putIfAbsent(dst, new HashSet<>());
        // add edge from src to dst (won't duplicate because Set)
        graph.get(src).add(dst);
    }

    public boolean removeEdge(final int src, final int dst) {
        final HashSet<Integer> vertex = graph.get(src);
        if (vertex == null) {
            // vertex does not exist
            return false;
        }

        // remove returns true if actual remove occurred, otherwise false
        return vertex.remove(dst);
    }

    public boolean hasPath(final int src, final int dst) {
        // initiate DFS from src, seeking dst, starting with no nodes visited
        return seek(src, dst, new HashSet<>());
    }

    private boolean seek(final int src, final int dst, final HashSet<Integer> visited) {
        if (src == dst) {
            // path found
            return true;
        }

        final HashSet<Integer> vertex = graph.get(src);
        if (vertex == null || visited.contains(src)) {
            // dead end or loop
            return false;
        }

        // mark src as visited then keep looking for dst
        visited.add(src);
        for (final int toVisit : vertex) {
            if (seek(toVisit, dst, visited)) {
                // found somewhere on this path
                return true;
            }
        }

        // not found
        return false;
    }
}
