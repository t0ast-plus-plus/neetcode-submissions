class Solution {
    public int[] topKFrequent(final int[] nums, final int k) {
        // gather counts of each number
        final Map<Integer, Integer> counts = new HashMap<>();
        for(final int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // initialize an array of List-holding buckets where index = quantity, list elements = numbers in that quantity
        final List<Integer>[] countBuckets = new List[nums.length+1];
        for (int i = 0; i < countBuckets.length; i++) {
            countBuckets[i] = new ArrayList<>();
        }

        // fill the buckets using the previously gathered counts
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            final int key = entry.getKey();
            countBuckets[entry.getValue()].add(entry.getKey());
        }

        // start from the end (highest quantity) index of the buckets and pull numbers until we reach k
        final int[] solution = new int[k];
        int solutionIndex = 0;
        for(int i = countBuckets.length - 1; i > 0 && solutionIndex < k; i--) {
            for(int n : countBuckets[i]) {
                solution[solutionIndex++] = n;
                if(solutionIndex == k) {
                    return solution;
                }
            }
        }
        return solution;
    }
}
