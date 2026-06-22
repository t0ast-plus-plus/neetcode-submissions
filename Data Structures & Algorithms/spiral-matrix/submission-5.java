class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        final List<Integer> solution = new ArrayList<>(matrix.length * matrix[0].length);

        int colStart = 0;
        int colEnd = matrix[0].length-1;
        int rowStart = 0;
        int rowEnd = matrix.length-1;

        while (colStart <= colEnd && rowStart <= rowEnd) {
            // go right
            for (int i = colStart; i <= colEnd; i++) {
                solution.add(matrix[rowStart][i]);
            }
            rowStart++; // shrink top

             // go down
            for (int i = rowStart; i <= rowEnd; i++) {
                solution.add(matrix[i][colEnd]);
            }
            colEnd--; // shrink right

            // check edge case for 1xN or Nx1 arrays
            if (colStart > colEnd || rowStart > rowEnd) {
                break;
            }

            // go left
            for (int i = colEnd; i >= colStart; i--) {
                solution.add(matrix[rowEnd][i]);
            }
            rowEnd--; // shrink bottom

            // go up
            for (int i = rowEnd; i >= rowStart; i--) {
                solution.add(matrix[i][colStart]);
            }
            colStart++; // shrink left
        }

        return solution;
    }
}