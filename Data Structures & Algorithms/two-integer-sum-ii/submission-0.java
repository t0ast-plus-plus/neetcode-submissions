class Solution {
    public int[] twoSum(int[] numbers, int target) {
        final int[] solution = new int[2];
        // note: numbers comes pre-sorted (non-decreasing)

        // two-pointer approach, starting from each end
        int l = 0, r = numbers.length-1;
        while (l < r) {
            final int sum = numbers[l] + numbers[r];
            if(sum == target) {
                solution[0] = l+1;
                solution[1] = r+1;
                break;
            }

            if(sum < target) {
                // increase sum by incrementing the left pointer (towards larger values)
                l++;
            } else {
                // cadecrease sum by decrementing the right pointer (towards smaller values)
                r--;
            }
        }

        return solution;
    }
}
