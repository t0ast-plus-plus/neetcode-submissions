class Solution {
    public int maxArea(final int[] heights) {
        int left = 0;
        int right = heights.length-1;
        int max = -1;

        while(left < right)
        {
            // calculate area and update max if larger
            max = Math.max(max, getArea(left, right, heights));

            // move inward from the shorter side
            if(heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    private int getArea(final int left, final int right, final int[] heights) {
        return Math.min(heights[left], heights[right]) * (right-left);
    }
}
