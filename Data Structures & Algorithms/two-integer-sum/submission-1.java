class Solution {
    public int[] twoSum(final int[] nums, final int target) {
        // map of indexes keyed by value
        HashMap<Integer, Integer> diffMap = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            // look to see if the complement of a given number exists in the map
            final Integer complement = diffMap.get(target - nums[i]);
            if(complement != null) {
                return new int[] { complement, i };
            }

            // otherwise add this element and its index to the map
            diffMap.put(nums[i], i);
        }

        return new int[] {};
    }
}
