class Solution {
    // storing these at the class level for convenience and brevity
    List<Integer> profits;
    List<Integer> weights;
    int cap;
    int items;
    int[][] cache;

    public int maximumProfit(final List<Integer> profits, final List<Integer> weights, final int cap) {
        this.profits = profits;
        this.weights = weights;
        this.cap = cap;
        this.items = profits.size();

        // fast/lazy solution for fun, accuracy not guaranteed
        // return fastPack();

        // accurate brute force solution
        // return brutePack(0,0);

        // adding in a cache to improve performance over the brute force method
        cache = new int[items][cap + 1];

        // initialize array with all values set to -1 for this approach
        // Arrays.stream(cache).forEach(a -> Arrays.fill(a, -1));
        // return cachePack(0, 0);

        // alternative memoization approach to the above (array value initialization not required)
        return memoPack();
    }

    // brute force implementation requires O(2^n), which isn't viable for large calculations
    private int brutePack(final int currentWeight, final int index) {
        if (index >= items) {
            // ran out of items to add
            return 0;
        }

        // try excluding this item
        int maxProfit = brutePack(currentWeight, index + 1);

        // try including this item if possible, then use it as max profit if higher
        final int newWeight = currentWeight + weights.get(index);
        if (newWeight <= cap) {
            maxProfit = Math.max(profits.get(index) + brutePack(newWeight, index + 1), maxProfit);
        }

        return maxProfit;
    }

    // adding a cache to the above improves it to O(n*m)
    // where n = number of items, and m = all available levels of capacity
    private int cachePack(final int currentWeight, final int index) {
        if (index >= items) {
            // ran out of items
            return 0;
        }

        // return cached value for this current weight and index, if present
        if (cache[index][currentWeight] != -1) {
            return cache[index][currentWeight];
        }

        // traverse exclusion case and apply its result to the cache for these inputs
        cache[index][currentWeight] = cachePack(currentWeight, index + 1);

        // if inclusion is possible, calculate its possible profit and overwrite cache if larger
        final int newWeight = currentWeight + weights.get(index);
        if (newWeight <= cap) {
            final int includeProfit = profits.get(index) + cachePack(newWeight, index + 1);
            cache[index][currentWeight] = Math.max(includeProfit, cache[index][currentWeight]);
        }

        return cache[index][currentWeight];
    }

    // an alternative, non-recursive "memoization" solution to cachePack(), still O(n*m)
    // TODO: space complexity can be improved, as we technically don't need the full n*m cache
    private int memoPack() {
        // note: the meaning of the capacity index is inverted here, compared to other methods
        // in this case, it represents the REMAINING capacity, not the FILLED capacity

        // start by filling in two edges to streamline the "main" logic of this method:
        // 1) set all item profits to 0 when the remaining capacity = 0, as nothing will fit
        for(int itemIndex = 0; itemIndex < items; itemIndex++) {
            cache[itemIndex][0] = 0;
        }
        // 2) populate profit values for first item, as the "main" logic needs to be able
        // to look back at the previous item's profit values
        for(int remainingCap = 0; remainingCap <= cap; remainingCap++) {
            if(weights.get(0) <= remainingCap) {
                cache[0][remainingCap] = profits.get(0);
            } else {
                cache[0][remainingCap] = 0;
            }
        }

        // iterate through all other items and all other levels of remaining capacity
        for(int itemIndex = 1; itemIndex < items; itemIndex++) {
            for (int remainingCap = 1; remainingCap <= cap; remainingCap++) {
                
                // calculate profit if this item is EXCLUDED at this level of remaining capacity
                // all we have to do here is look at the previuos item's profit for this capacity
                final int excludeProfit = cache[itemIndex-1][remainingCap];
                
                // calculate profit if this item is INCLUDED at this level of remaining capacity
                int includeProfit = 0; // default value = 0, if this can't fit

                // check to see if this item can fit at this level of remaining capacity
                final int newCap = remainingCap - weights.get(itemIndex);
                if (newCap >= 0) {
                    // profit = this item's profit + max calculated profit of all previous items
                    // at the level of capacity that this item would reduce it to
                    includeProfit = profits.get(itemIndex) + cache[itemIndex-1][newCap];
                }

                // populate this cache position with the higher of the two calculated profits
                cache[itemIndex][remainingCap] = Math.max(includeProfit, excludeProfit);
            }
        }

        // the "last" cell holds the answer
        // as it represents the max profit w/ all items and full capacity accounted for
        return cache[items-1][cap];
    }

    // fast/lazy/approximate solution for fun: prioritize profit per weight, O(n log n)
    private int fastPack() {
        // calculate profit per weight of each item
        final List<Item> itemsByPPW = new ArrayList<>(weights.size());
        for (int i = 0; i < items; i++) {
            itemsByPPW.add(new Item(weights.get(i), profits.get(i)));
        }

        // sort items by profit per weight
        itemsByPPW.sort((i1, i2) -> Double.compare(i1.ppw, i2.ppw));

        // start with highest profit per weight and keep adding until we can't
        int totalWeight = 0;
        int totalProfit = 0;
        for (int i = items - 1; i >= 0; i--) {
            final Item item = itemsByPPW.get(i);
            if (totalWeight + item.weight > cap) {
                return totalProfit;
            }

            totalWeight += item.weight;
            totalProfit += item.profit;
        }

        return totalProfit;
    }

    private class Item {
        final int weight;
        final int profit;
        final double ppw;

        Item(int weight, int profit) {
            this.weight = weight;
            this.profit = profit;
            ppw = (double) profit / weight;
        }
    }
}
