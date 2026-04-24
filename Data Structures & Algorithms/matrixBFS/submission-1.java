class Solution {
    public int shortestPath(final int[][] grid) {
        final int rowSize = grid.length;
        final int colSize = grid[0].length;
        final int[][] neighborMoves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // track previously visited coordinates to prevent revsiting them
        final int[][] visited = new int[rowSize][colSize];
        visited[0][0] = 1;

        // establish a queue for travel, starting with the top left corner
        final Queue<int[]> travelQueue = new LinkedList<>();
        travelQueue.add(new int[]{0,0});

        // track the number of steps taken to reach the solution (not counting start)
        int length = 0;

        while(!travelQueue.isEmpty())
        {
            // for each element at the current queue's "depth"...
            final int depthSize = travelQueue.size();
            for(int i = 0; i < depthSize; i++)
            {
                // pop for the next travel location
                final int[] coords = travelQueue.poll();
                final int row = coords[0];
                final int col = coords[1];

                // check for solution condition (BFS first found = shortest)
                if(row == rowSize - 1 && col == colSize - 1)
                {
                    return length;
                }

                // add valid neighbors to the travel queue and flag them as visited
                for (final int[] neighborMove : neighborMoves) {
                    final int neighborRow = row + neighborMove[0];
                    final int neighborCol = col + neighborMove[1];
                    if(isValidNeighbor(grid, visited, neighborRow, neighborCol))
                    {
                        travelQueue.add(new int[]{neighborRow, neighborCol});
                        visited[neighborRow][neighborCol] = 1;
                    }
                }
            }

            // current "depth" fully processed w/o solution, so increment length
            length++;
        }
            
        // exhausted valid travel without a solution
        return -1;
    }

    private boolean isValidNeighbor(final int[][] grid, final int[][] visited, final int row, final int col)
    {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length // in bounds
            && grid[row][col] == 0 // not blocked
            && visited[row][col] == 0; // not previously visited
    }
}
