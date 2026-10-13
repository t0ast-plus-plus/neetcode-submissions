class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> tracker = new HashSet<>();
        for(int i : nums) {
            if(tracker.contains(i)) {
                tracker.remove(i);
            } else {
                tracker.add(i);
            }
        }

        return tracker.iterator().next();
    }
}
