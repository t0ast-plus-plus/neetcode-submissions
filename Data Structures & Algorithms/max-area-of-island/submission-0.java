class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxIslandSize = 0;
        int[][] visited = new int[grid.length][grid[0].length];
        for(int[] v : visited) {
            Arrays.fill(v, 0);
        }

        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                // search for unvisited land
                if (grid[r][c] == 1 && visited[r][c] == 0) {
                    // "walk" the island to determine its size
                    maxIslandSize = Math.max(maxIslandSize, walk(grid, visited, r, c));
                }
            }
        }

        return maxIslandSize;
    }

    private int walk(int[][] grid, int[][] visited, int r, int c) {
        // do not count coordinate if OOB, water, or previously visited
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0 || visited[r][c] == 1) {
            return 0;
        }

        // set initial land size = 1 (this square) and mark it as visited
        int islandSize = 1;
        visited[r][c] = 1;

        // recursively walk surrounding coordinates and combine results
        // up
        islandSize += walk(grid, visited, r-1, c);
        // down
        islandSize += walk(grid, visited, r+1, c);
        // left
        islandSize += walk(grid, visited, r, c-1);
        // right
        islandSize += walk(grid, visited, r, c+1);

        return islandSize;
    }
}
