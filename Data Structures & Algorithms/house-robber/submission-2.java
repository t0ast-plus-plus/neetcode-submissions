class Solution {
    public int rob(final int[] nums) {
        // edge case handling
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }

        // keep memo representing known best values from a given index
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robSearch(nums, memo, 0);
    }

    private int robSearch(final int[] nums, final int[] memo, final int i) {
        if(i >= nums.length) {
            // OOB, no value to be gained
            return 0;
        }
        if(memo[i] != -1) {
            // best value from this position already known
            return memo[i];
        }

        // from any given position, we have two options:
        // 1) skip this house and rob the next one instead
        // 2) rob this house and ignore the very next house
        // recursively repeat this algorithm for each scenario and store the best in the memo for this index
        memo[i] = Math.max(robSearch(nums, memo, i+1), nums[i]+robSearch(nums, memo, i+2));

        // return the best value (as updated above) from this index
        return memo[i];
    }
}
