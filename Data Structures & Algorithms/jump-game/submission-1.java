class Solution {
    public boolean canJump(int[] nums) {
        return jump(0, nums);
    }

    private boolean jump(final int pos, final int[] nums) {
        if (pos >= nums.length - 1) {
            return true;
        }

        for (int jumpLength = nums[pos]; jumpLength > 0; jumpLength--) {
            if (jump(jumpLength + pos, nums)) {
                return true;
            }
        }

        return false;
    }
}
