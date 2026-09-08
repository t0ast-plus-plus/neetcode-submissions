class Solution {
    public int rob(int[] nums) {
        // edge case handling
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }

        // keep memo representing known best values of choosing to rob a given house
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robSearch(nums, memo, 0);
    }

    private int robSearch(int[] nums, int[] memo, int i) {
        if(i >= nums.length) {
            // OOB, no value to be gained
            return 0;
        }
        if(memo[i] != -1) {
            // value of robbing this house already known
            return memo[i];
        }

        // take the best of two options and store it as the "value" of choosing to rob this house:
        // 1) skip this house and rob the next one instead
        // 2) rob this house and the one after the next
        memo[i] = Math.max(robSearch(nums, memo, i+1), nums[i]+robSearch(nums, memo, i+2));

        return memo[i];
    }
}
