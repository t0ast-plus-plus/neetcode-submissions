class Solution {
    public List<List<Integer>> threeSum(final int[] nums) {
        // sort the provided array to make traversing / searching for solutions more efficient
        Arrays.sort(nums);

        // store solutions using a Set to guarantee unique results
        final Set<List<Integer>> solutions = new HashSet<>();

        // for the first number, iterate through all but the last two elements of the array
        for(int i = 0; i < nums.length-2; i++) {
            // for the other two, utilize a two-pointer approach for all elements to the right of i
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
                }
            }        
        }

        return solutions.stream().toList();
    }
}
