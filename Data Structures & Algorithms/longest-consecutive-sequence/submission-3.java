class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        // convert to Set to eliminate duplicates
        // and for efficient value searching
        Set<Integer> vals = new HashSet<>();
        for(int i : nums) {
            vals.add(i);
        }

        int longest = 0;

        for(int i : vals) {
            // only evaluate items that start a sequence
            if(!vals.contains(i-1)) {
                int length = 1;
                int currentValue = i;

                // keep seeking the next number
                while (vals.contains(currentValue+1)) {
                    length++;
                    currentValue++;
                }

                // update the longest count if applicable
                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}
