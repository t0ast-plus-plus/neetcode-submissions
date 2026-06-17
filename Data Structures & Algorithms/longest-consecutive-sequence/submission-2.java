class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        Arrays.sort(nums);
        int maxLength = 1;
        int curLength = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            final int curVal = nums[i];
            final int nextVal = nums[i + 1];
            System.out.println(curVal + " " + nextVal);
            if (nextVal == curVal + 1) {
                // sequence continues
                curLength++;
            } else if (nextVal != curVal) { // ignore repeated values
                // sequence broken, record new max if larger then reset current length
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                curLength = 1;
            }
        }

        // return whichever is larger, the current count or a previously recorded max
        return Math.max(curLength, maxLength);
    }
}
