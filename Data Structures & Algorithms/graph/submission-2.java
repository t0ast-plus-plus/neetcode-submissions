class Graph {
    // key = vertex ID, value = edge destinations
    private final HashMap<Integer, HashSet<Integer>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(int src, int dst) {
        HashSet<Integer> vertex = graph.get(src);
        if (vertex == null) {
            // add new vertex w/ edge
            vertex = new HashSet<>();
            vertex.add(dst);
            graph.put(src, vertex);
        } else {
            // append edge
            vertex.add(dst);
        }
    }

    public boolean removeEdge(int src, int dst) {
        final HashSet<Integer> vertex = graph.get(src);
        if (vertex == null) {
            // vertext does not exist
            return false;
        }

        // remove returns true if actual remove occurred, otherwise false
        return vertex.remove(dst);
    }

    public boolean hasPath(int src, int dst) {
        return seek(src, dst, new HashSet<>());
    }

    private boolean seek(int src, int dst, HashSet<Integer> visited) {
        if (src == dst) {
            // path found
            return true;
        }

        final HashSet<Integer> vertex = graph.get(src);
        if (vertex == null) {
            // dead end
            return false;
        }

        // mark src as visited then keep looking for dst
        visited.add(src);

        for (final int toVisit : vertex) {
            if (!visited.contains(toVisit) && seek(toVisit, dst, visited)) {
                return true;
            }
        }

        return false;
    }
}
