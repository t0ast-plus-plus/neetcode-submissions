class Solution {
    public int maximumProfit(List<Integer> profit, List<Integer> weight, int capacity) {
        final int[][] cache = new int[profit.size()][capacity + 1];
        Arrays.stream(cache).forEach(a -> Arrays.fill(a, -1));
        return cachePack(0, capacity, profit, weight, cache);
    }

    private int cachePack(final int index, final int remainingCapacity, final List<Integer> profits,
        final List<Integer> weights, int[][] cache) {
        if (index >= profits.size()) {
            // ran out of items
            return 0;
        }

        // return cached value for this current weight and index, if present
        if (cache[index][remainingCapacity] != -1) {
            return cache[index][remainingCapacity];
        }

        // traverse exclusion case and store it as the (tentative) max profit
        int maxProfit = cachePack(index + 1, remainingCapacity, profits, weights, cache);

        // traverse inclusion case if possible and overwrite max profit if larger than the above
        // KEY DIFFERENCE FROM 0-1 KNAPSACK: we recurse on the same index instead of advancing
        // to the next item, which allows us to reconsider this same item for multiple inclusion
        final int newCap = remainingCapacity - weights.get(index);
        if (newCap >= 0) {
            final int includeProfit =
                profits.get(index) + cachePack(index, newCap, profits, weights, cache);
            maxProfit = Math.max(maxProfit, includeProfit);
        }

        cache[index][remainingCapacity] = maxProfit;
        return maxProfit;
    }
}
