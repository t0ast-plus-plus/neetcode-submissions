class Solution {
    public int search(final int[] nums, final int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right) {
            // pick a midpoint to split the current subarray in half
            final int mid = (left+right)/2;
            
            // check midpoint index for the targeted number
            if(nums[mid] == target) {
                return mid;
            }

            // when dividing a sorted and rotated array around a given mid(point)
            // it is guaranteed that at least one side will be sorted, providing a usable range
            // if the target is within that range, it is guaranteed to be in the sorted half
            // if the target is *not* within that range, it is guaranteed to be in the other half
            // keep iterating by removing the half that the target is *not* inside
            // each iteration removes half, so the target, if it exists, will eventually appear at mid
            if(nums[left] <= nums[mid]) {
                // left half is sorted
                if(target > nums[mid] || target < nums[left]) {
                    // target is not in the left half, so drop numbers from left to mid
                    left = mid+1;
                } else {
                    // target is inside left half, so drop numbers from mid to right
                    right = mid-1;
                }
            } else {
                // right half is sorted
                if(target < nums[mid] || target > nums[right]) {
                    // target is not in the right half, so drop numbers from mid to right
                    right = mid-1;
                } else {
                    // target is inside the right half, so drop numbers from left to mid
                    left = mid+1;
                }
            }
        }
        
        // if the loop above didn't return a value, the target was not present
        return -1;
    }
}
