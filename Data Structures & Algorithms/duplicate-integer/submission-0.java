class Solution {
    public boolean hasDuplicate(final int[] nums) {
        final Set<Integer> set = new HashSet<>(nums.length);
        for(int num : nums) {
            // Set.add() returns false if element already exists
            if(!set.add(num)) {
                return true;
            }
        }

        return false;
    }
}