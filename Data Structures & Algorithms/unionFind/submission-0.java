class UnionFind {
    final int[] parents;
    final int[] ranks;
    int numComponents;

    public UnionFind(final int n) {
        parents = new int[n];
        ranks = new int[n];
        for (int i = 0; i < n; i++) {
            // all nodes start as individual, disjoint roots
            // roots identify themselves by parent = self
            parents[i] = i;
            // give each node an arbitrary starting rank number
            // not required, but incorporating into union() improves find()
            ranks[i] = 0;
        }

        // number of components = number of disjoint sets = all of them (to start)
        numComponents = n;
    }

    // finds the root node for a given node
    public int find(final int x) {
        if (x != parents[x]) {
            // keep traversing parents
            // also "compress" the path as we go (set all parents along the way to root)
            // this will make any repeated find() later on more efficient for any nodes along the
            // way e.g., c --> b and b --> a would update such that c --> a, making any future
            // find(c) faster
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
            // same root = same component set = no new union to be established
            return false;
        }

        // the rank of a component set is tracked at the parent
        final int rankX = ranks[rootX];
        final int rankY = ranks[rootY];

        // for two unequal ranks, the larger rank should be the parent of a new union
        // this is not strictly required, but is more efficient, because it keeps the
        // larger component set closer to the root, so find() is faster for more components
        if (rankX > rankY) {
            parents[rootY] = rootX;
        } else if (rankY > rankX) {
            parents[rootX] = rootY;
        }

        // the two sets are equal in rank, so arbitrarily choose one to be the parent
        // then increment the rank of that parent
        else {
            parents[rootX] = rootY;
            ranks[rootY]++;
        }

        // union successful, so we now have one fewer disjoint set
        numComponents--;
        return true;
    }

    public int getNumComponents() {
        return numComponents;
    }
}
