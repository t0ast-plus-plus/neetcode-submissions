class Solution {
    // coordinate offsets for traversal directions
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(final int[][] heights) {
        // traverse pacific border cells (top row, leftmost column)
        final Set<List<Integer>> flowsToPacific = new HashSet<>();
        for (int r = 0; r < heights.length; r++) {
            for (int c = 0; c < heights[0].length; c++) {
                if (r == 0 || c == 0) {
                    flowSearch(List.of(r, c), heights, flowsToPacific);
                }
            }
        }

        // traverse atlantic border cells (bottom row, rightmost column)
        final Set<List<Integer>> flowsToAtlantic = new HashSet<>();
        for (int r = 0; r < heights.length; r++) {
            for (int c = 0; c < heights[0].length; c++) {
                if (r == heights.length - 1 || c == heights[0].length - 1) {
                    flowSearch(List.of(r, c), heights, flowsToAtlantic);
                }
            }
        }

        // determine solution by looking for coordinates in both flow sets
        final List<List<Integer>> solution = new ArrayList<>();
        for (final List<Integer> pacificFlowCoord : flowsToPacific) {
            if (flowsToAtlantic.contains(pacificFlowCoord)) {
                solution.add(pacificFlowCoord);
            }
        }

        return solution;
    }

    // Invoke for each ocean-adjacent coordinate and any neighbors with greater or equal height.
    // The 'flows' param will store all coordinates that flow to the same ocean.
    private void flowSearch(final List<Integer> coords, final int[][] heights, final Set<List<Integer>> flows) {
        // skip if already known
        if (flows.contains(coords)) {
            return;
        }

        // mark provided coordinates as flowing into the ocean
        flows.add(coords);

        // traverse each possible direction if its height is equal or higher
        for(int[] d : directions) {
            final int nextRow = coords.get(0) + d[0];
            final int nextCol = coords.get(1) + d[1];

            if(nextRow >= 0 && nextRow < heights.length
                && nextCol >= 0 && nextCol < heights[0].length
                && heights[nextRow][nextCol] >= heights[coords.get(0)][coords.get(1)]) {
                    flowSearch(List.of(nextRow, nextCol), heights, flows);
                }
        }
    }
}