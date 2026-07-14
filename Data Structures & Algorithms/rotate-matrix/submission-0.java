class Solution {
    public void rotate(int[][] matrix) {
        // start with outer layer as left/right boundaries
        int left = 0;
        int right = matrix.length - 1;

        // temporary storage for in-place rotation
        int topLeftTemp;

        // go until we've traversed all rotateable layers (left and right pointers meet)
        while (left < right) {
            // top and bottom bounds match left and right bounds because square
            // separate variables not strictly needed, but this helps readability
            int top = left;
            int bottom = right;

            // use incrementing offset to traverse all cells in this "layer"
            for (int i = 0; i < right - left; i++) {
                // store top left
                topLeftTemp = matrix[top][left + i];

                // bottom left into top left
                matrix[top][left + i] = matrix[bottom - i][left];

                // bottom right into bottom left
                matrix[bottom - i][left] = matrix[bottom][right - i];

                // top right into bottom right
                matrix[bottom][right - i] = matrix[top + i][right];

                // (stored) top left into top right
                matrix[top + i][right] = topLeftTemp;
            }

            // walk the left and right boundaries inward
            right--;
            left++;
        }
    }
}
