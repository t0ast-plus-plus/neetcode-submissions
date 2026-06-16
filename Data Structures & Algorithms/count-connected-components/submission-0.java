class Solution {
    public int countComponents(final int n, final int[][] edges) {
        // component groups will be defined by an arbitrary root parent node
        // so we need to track the root parent for each node
        final int[] parents = new int[n];

        // initialize the parents array such that each node points to itself
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        // connect nodes using given edges
        for (int[] edge : edges) {
            connect(edge[0], edge[1], parents);
        }

        // find all unique root parent nodes
        final Set<Integer> componentGroupParents = new HashSet<>();
        for (int i = 0; i < n; i++) {
            componentGroupParents.add(getRootParent(i, parents));
        }

        // the count of those unique root parent nodes is the number of components
        return componentGroupParents.size();
    }

    private void connect(final int a, final int b, final int[] parents) {
        final int rootA = getRootParent(a, parents);
        final int rootB = getRootParent(b, parents);
        if (rootA != rootB) {
            parents[rootA] = rootB;
        }
    }

    private int getRootParent(final int n, final int[] parents) {
        // a parent of self means that this node is a root parent
        if (parents[n] == n) {
            return n;
        }

        // recurse to find root parent
        final int rootParent = getRootParent(parents[n], parents);

        // jump this node's parent to the root parent before returning
        // as this will speed up repeated searches
        parents[n] = rootParent;
        return rootParent;
    }
}
