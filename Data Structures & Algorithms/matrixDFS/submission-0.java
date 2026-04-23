class Solution {
    int rowSize;
    int colSize;

    public int countPaths(int[][] grid) {
        rowSize = grid.length;
        colSize = grid[0].length;

        return travel(grid, 0, 0, new HashSet<>());
    }

    private int travel(final int[][] grid, final int row, final int col, final Set<Pair<Integer, Integer>> visited)
    {
        final Pair<Integer, Integer> here = new Pair(row, col);

        // check for illegal movement
        if(row < 0 || col < 0 || row >= rowSize || col >= colSize // OOB
            || grid[row][col] == 1 // rock
            || visited.contains(here)) // visited
        {
            return 0;
        }

        // check for destination
        if (row == rowSize - 1 && col == colSize - 1)
        {
            return 1;
        }

        // log this travel coordinate to block it out for future travel
        visited.add(here);

        // get your move(s) on
        int pathsFromHere =
            travel(grid, row - 1, col, visited) // previous row
            + travel(grid, row + 1, col, visited) // next row
            + travel(grid, row, col - 1, visited) // previous col
            + travel(grid, row, col + 1, visited); // next col

        // remove this travel coordinate as we "backtrack" back up the stack
        visited.remove(here);

        return pathsFromHere;
    }


}
