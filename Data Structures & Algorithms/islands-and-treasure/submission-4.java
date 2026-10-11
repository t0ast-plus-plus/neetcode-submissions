class Solution {
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int UNVISITED_LAND = 2147483647;

    public void islandsAndTreasure(int[][] grid) {    
        Queue<int[]> steps = new LinkedList<>();
        
        // pre-populate step queue with treasure locations
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 0) {
                    steps.add(new int[]{r, c});
                }
            }
        }

        int distance = 1;

        while (!steps.isEmpty()) {
            // process only the initially existing batch of steps per iteration
            int toExplore = steps.size();
            for (int i = 0; i < toExplore; i++) {
                // pop next coordinates
                final int[] step = steps.poll();
                int r = step[0], c = step[1];

                // look up, down, left, and right for valid coordinates
                // (must be in-bounds and represent unvisited land)
                // mark each new coordinate with the current distance then enqueue its coords
                for (int[] dir : DIRECTIONS) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    if (nextR >= 0 && nextC >= 0 && nextR < grid.length && nextC < grid[0].length
                        && grid[nextR][nextC] == UNVISITED_LAND) {
                        grid[nextR][nextC] = distance;
                        steps.add(new int[] {nextR, nextC});
                    }
                }
            }

            // increment distance for next round of steps
            distance++;
        }
    }
}
