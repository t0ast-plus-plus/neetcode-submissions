class UnionFind {
    final int[] parents;
    final int[] sizes;
    int numComponents;

    public UnionFind(final int n) {
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            // all nodes start as individual, disjoint roots
            // roots identify themselves by parent = self
            parents[i] = i;
            // give each node a starting size number
            // tracking this is optional, but helps improve efficiency, see comments in union()
            sizes[i] = 1;
        }

        // number of components = number of disjoint sets = number of starting nodes
        numComponents = n;
    }

    // finds the root node for a given node
    public int find(final int x) {
        if (x != parents[x]) {
            // keep traversing parents
            // along the way, "compress" the path by setting each node's parent to root
            // this extra step will help speed up any future find() on these nodes
            parents[x] = find(parents[x]);
        }

        return parents[x];
    }

    public boolean isSameComponent(final int x, final int y) {
        // same root = same component set
        return find(x) == find(y);
    }

    public boolean union(final int x, final int y) {
        final int rootX = find(x);
        final int rootY = find(y);
        if (rootX == rootY) {
            // same root = same component set = no new union to be created
            return false;
        }

        // compare the sizes of each node's root (higher size = more nodes in set)
        // the larger (or equal) sized root becomes the new parent of the other root
        // the "winning" root also increases in size based on the size of its new child
        // this logic isn't strictly required, but makes find() more efficient on average
        // because we are keeping the larger set of nodes closer to root
        if (sizes[rootX] > sizes[rootY]) {
            parents[rootY] = rootX;
            sizes[rootX] += sizes[rootY];
        } else {
            parents[rootX] = rootY;
            sizes[rootY] += sizes[rootX];
        }

        // union successful, so we now have one fewer disjoint component set
        numComponents--;
        return true;
    }

    public int getNumComponents() {
        return numComponents;
    }
}
