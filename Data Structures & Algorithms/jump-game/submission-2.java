class Solution {
    public boolean canJump(int[] nums) {
        Map<Integer, Boolean> cache = new HashMap<>();
        return jump(0, nums, cache);
    }

    private boolean jump(final int pos, final int[] nums, Map<Integer, Boolean> cache) {
        if(cache.containsKey(pos)) {
            return cache.get(pos);
        }

        if (pos >= nums.length - 1) {
            return true;
        }

        for (int jumpLength = nums[pos]; jumpLength > 0; jumpLength--) {
            if (jump(jumpLength + pos, nums, cache)) {
                cache.put(pos, true);
                return true;
            }
        }

        cache.put(pos, false);
        return false;
    }
}
