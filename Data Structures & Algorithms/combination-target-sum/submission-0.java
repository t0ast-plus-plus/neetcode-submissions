class Solution {
    public List<List<Integer>> combinationSum(final int[] nums, final int target) {
        final List<List<Integer>> solutions = new ArrayList<>();
        find(nums, target, 0, 0, new ArrayList<>(), solutions);

        return solutions;
    }

    private void find(final int[] nums, final int target, final int curIndex, final int curTotal,
        final List<Integer> curGroup, final List<List<Integer>> solutions) {
        if (curIndex >= nums.length) {
            // ran out of numbers
            return;
        }

        // branch 1: choose not to include this number and move on to the next
        find(nums, target, curIndex + 1, curTotal, curGroup, solutions);

        // branch 2: try to include this number
        final int newTotal = curTotal + nums[curIndex];

        // disregard if new total would exceed target
        if (newTotal > target) {
            return;
        }

        // append this number to the possible solution group
        curGroup.add(nums[curIndex]);

        if (newTotal == target) {
            // solution found
            solutions.add(new ArrayList<>(curGroup));
        } else {
            // target not yet reached, recurse on this same index again
            find(nums, target, curIndex, newTotal, curGroup, solutions);
        }

        // done considering this value, so pull it out of the possible solution group
        curGroup.removeLast();
    }
}
