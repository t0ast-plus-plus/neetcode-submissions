class Solution {
    // Dijkstra's shortest path algorithm
    public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        // initialize a map of edge info keyed by source node
        // (value int[0] = destination node, int[1] = weight of connecting edge)
        final Map<Integer, List<int[]>> edgesByNode = new HashMap<>();
        for (int i = 0; i < n; i++) {
            edgesByNode.put(i, new ArrayList<>());
        }

        // populate edges into this map based on input
        // index 0 = source node ID
        // index 1 = destination node ID
        // index 2 = edge weight from source to destination
        for (List<Integer> edge : edges) {
            edgesByNode.get(edge.get(0)).add(new int[] {edge.get(1), edge.get(2)});
        }

        // create Map to store the shortest path to each node from the start (src) node
        final Map<Integer, Integer> shortestPaths = new HashMap<>();

        // create a priority queue of int pairs to be processed as we traverse the graph
        // int[0] represents a node's ID
        // int[1] represents the accumulated path weight to reach this node
        // priority will be given to the lowest cumulative path weight (a.k.a. min-heap)
        final PriorityQueue<int[]> processQueue =
            new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // initialize the proccessing queue with the starting (src) node and a weight of 0
        processQueue.offer(new int[] {src, 0});

        while (!processQueue.isEmpty()) {
            // pop next in queue
            final int[] current = processQueue.poll();

            final int curNode = current[0];
            if (shortestPaths.containsKey(curNode)) {
                // we already have a shortest path to this node, so do nothing with this queue item
                continue;
            }

            // store this node and the weight to reach it in the shortest paths map
            final int curWeight = current[1];
            shortestPaths.put(curNode, curWeight);

            // examine next available nodes for traversal
            for (int[] edge : edgesByNode.get(curNode)) {
                final int nextNode = edge[0], nextWeight = edge[1];
                // disregard any nodes with a shortest path already established
                if (!shortestPaths.containsKey(nextNode)) {
                    // add the node to the queue
                    // weight = previously accumulated weight + the last edge weight to reach it
                    // (prority queue object will handle the insertion position internally)
                    processQueue.offer(new int[] {nextNode, curWeight + nextWeight});
                }
            }
        }

        // backfill shortest path map to assign -1 to unreachable nodes
        for (int i = 0; i < n; i++) {
            shortestPaths.putIfAbsent(i, -1);
        }

        return shortestPaths;
    }
}
