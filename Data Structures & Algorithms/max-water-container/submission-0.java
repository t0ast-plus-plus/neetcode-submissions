class Solution {
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length-1;
        int max = getArea(left, right, heights);
        System.out.println("initial max="+max);

        while(left != right)
        {
            // walk the shorter side inward
            if(heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }

            // see if we found a larger max
            max = Math.max(max, getArea(left, right, heights));
        }

        return max;
    }

    private int getArea(int left, int right, int[] heights) {
        return Math.min(heights[left], heights[right]) * (right-left);
    }
}
