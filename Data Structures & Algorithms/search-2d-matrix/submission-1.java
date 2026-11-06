class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        final int rows = matrix.length, cols = matrix[0].length;
        
        // binary search rows to find one where target is in its range
        int start = 0, end = matrix.length - 1;
        int row = -1;
        while (start <= end) {
            row = (start + end) / 2;
            if (target > matrix[row][cols - 1]) {
                start = row + 1;
            } else if (target < matrix[row][0]) {
                end = row - 1;
            } else {
                break;
            }
        }

        // if our start/end indexes overlapped, we did not find
        // a range in which the target exists from the above
        if(start > end) {
            return false;
        }

        // perform another binary search on the column
        start = 0;
        end = matrix[0].length;
        
        while (start <= end) {
            int col = (start + end) / 2;
            if (target > matrix[row][col]) {
                start = col + 1;
            } else if (target < matrix[row][col]) {
                end = col - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
