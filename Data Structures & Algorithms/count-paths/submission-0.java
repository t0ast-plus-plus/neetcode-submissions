class Solution {
    public int uniquePaths(int m, int n) {
        // initialize array representing the available paths along the last row
        int[] paths = new int[n];
        Arrays.fill(paths, 1);

        // per row, starting from 2nd to last (because we have that one already) and going to the top
        for(int i = m-2; i >= 0; i--) {
            // per column, starting from the 2nd to last and going to the left
            for(int j = n-2; j >= 0; j--) {
                // grow each index by the value to its right
                paths[j] += paths[j+1];
            }
        }

        // the value accumulated into the first index is the answer
        return paths[0];
    }
}
