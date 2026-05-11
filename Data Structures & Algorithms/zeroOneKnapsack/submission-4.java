class Solution {
    List<Integer> profits;
    List<Integer> weights;
    int cap;
    int items;
    int[][] cache;

    public int maximumProfit(List<Integer> profits, List<Integer> weights, int cap) {
        this.profits = profits;
        this.weights = weights;
        this.cap = cap;
        this.items = profits.size();

        // not viable for large item quantities
        // return brutePack(0,0);

        // fast/lazy solution for fun, accuracy not guaranteed
        // return fastPack();

        // initialize 2D cache (rows = item count, cols = capacity +1) with all values = -1
        cache = new int[items][cap + 1];
        Arrays.stream(cache).forEach(a -> Arrays.fill(a, -1));
        return cachePack(0, 0);
    }

    // brute force implementation (2^n)
    public int brutePack(int currentWeight, int index) {
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

    // adding a cache to the above improves it to N * M (capacity size * item count)
    public int cachePack(int currentWeight, int index) {
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

        final int addWeight = weights.get(index);

        // if inclusion is possible, traverse and overwrite cache with its profit value if higher
        if (currentWeight + addWeight <= cap) {
            final int newProfit =
                profits.get(index) + cachePack(currentWeight + addWeight, index + 1);
            cache[index][currentWeight] = Math.max(newProfit, cache[index][currentWeight]);
        }

        // return the final value
        return cache[index][currentWeight];
    }

    // fast/lazy solution for fun: insert in order of highest profit per weight
    public int fastPack() {
        final List<Item> itemsByPPW = new ArrayList<>(weights.size());
        for (int i = 0; i < items; i++) {
            itemsByPPW.add(new Item(weights.get(i), profits.get(i)));
        }

        itemsByPPW.sort((i1, i2) -> Double.compare(i1.ppw, i2.ppw));

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
