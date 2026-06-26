class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int lowest = nums[0];

        while(left <= right) {
            if (nums[left] < nums[right]) {
                // segment is sorted, take the value @ left if new lowest
                lowest = Math.min(lowest, nums[left]);
                break;
            }

            // calculate midpoint and update lowest if applicable
            int mid = (right+left)/2;
            lowest = Math.min(lowest, nums[mid]);

            // rotated sorted arrays, when divided into two subsets will
            // have the lowest value in the subset that is *not* sorted
            if(nums[left] <= nums[mid]) {
                // left half is sorted, so the answer will be in the right half
                left = mid+1;
            } else {
                // right half is sorted, so the answer will be in the left half
                right = mid-1;
            }
        }

        return lowest;
    }
}
