class Solution {
    public int numIslands(char[][] grid) {
        int islandCount = 0;
        
        // iterate through all coordinates looking for unvisited land
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == '1') {
                    // mark connected land mass as visited and increment count
                    visit(r,c,grid);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    private void visit(int r, int c, char[][] grid) {
        // ignore if OOB, water, or previously visited
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }

        // mark coordinate as visited, then visit adjacent coordinates
        grid[r][c] = '2';
        visit(r-1, c, grid);
        visit(r+1, c, grid);
        visit(r, c-1, grid);
        visit(r, c+1, grid);
    }
}
