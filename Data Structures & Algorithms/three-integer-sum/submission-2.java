class Solution {
    // return all unique combinations of three values from nums that total to 0
    public List<List<Integer>> threeSum(final int[] nums) {
        final List<List<Integer>> solutions = new ArrayList<>();

        // sort the provided array to make traversing / searching for solutions more efficient
        Arrays.sort(nums);

        // for the first number, iterate through all but the last two elements of the array
        for(int i = 0; i < nums.length-2; i++) {
            // stop processing if the first number is > 0, as all following numbers will be larger
            if(nums[i] > 0) {
                break;
            }

            // skip this index if its value is the same as before, to avoid duplicate solutions (and processing)
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // for the remaining indexes > i, utilize a two-pointer approach
            int j = i+1;
            int k = nums.length-1;
            while (j < k) {
                final int threeSum = nums[i] + nums[j] + nums[k];
                if(threeSum < 0) {
                    // increment j to increase total value
                    j++;
                } else if (threeSum > 0) {
                    // decrement k to decrease total value
                    k--;
                } else {
                    // add to solution
                    solutions.add(List.of(nums[i], nums[j], nums[k]));
                    j++;
                    // keep incrementing j until its value changes (to avoid duplicate solutions)
                    while(j < k && nums[j] == nums[j-1]) {
                        j++;
                    }
                }
            }        
        }

        return solutions;
    }
}
