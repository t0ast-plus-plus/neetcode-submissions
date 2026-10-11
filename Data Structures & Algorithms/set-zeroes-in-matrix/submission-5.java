class Solution {
    public void setZeroes(int[][] matrix) {
        // check to see if first row or first column contains a zero to start
        boolean zeroFirstRow = false;
        for(int c = 0; c < matrix[0].length; c++) {
            if(matrix[0][c] == 0) {
                zeroFirstRow = true;
                break;
            }
        }

        boolean zeroFirstCol = false;
        for(int r = 0; r < matrix.length; r++) {
            if(matrix[r][0] == 0) {
                zeroFirstCol = true;
                break;
            }
        }

        // first pass: look at all cells excluding first row and first column
        // when encountering a zero, flip the first row+col cells in alignment to 0
        // this turns the first row+col cells into our storage for what should be 0'd
        for (int r = 1; r < matrix.length; r++) {
            for (int c = 1; c < matrix[0].length; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        // second pass: run through first row and column, excluding [0][0]
        // (because we don't want to immediately wipe out the whole first row+col)
        // if we encounter a 0, it indicates the whole column or row should be 0s        
        for(int r = 1; r < matrix.length; r++) {
            if(matrix[r][0] == 0) {
                for(int c = 1; c < matrix[0].length; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        for(int c = 1; c < matrix[0].length; c++) {
            if(matrix[0][c] == 0) {
                for(int r = 1; r < matrix.length; r++) {
                    matrix[r][c] = 0;
                }
            }
        }


        // finally: circle back to zero out the whole first row or column
        // if their initial values contained a zero (before we started changing them)
        if(zeroFirstRow) {
            Arrays.fill(matrix[0], 0);
        }

        if(zeroFirstCol) {
            for(int r = 0; r < matrix.length; r++) {
                matrix[r][0] = 0;
            }
        }
    }
}
