class LRUCache {
    private final Map<Integer, Integer> cache;
    private final int capacity;

    public LRUCache(final int capacity) {
        this.capacity = capacity;

        // The built-in LinkedHashMap has features that can be used to create a LRU Cache:
        // 1) Fundamentally, this object provides an iterable key-value storage
        // 2) By setting its 3rd constructor param to 'true', ordering will be based on usage
        // 3) By overriding removeEldestEntry(), we can force it to prune based on our capacity
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) { // 0.75f = default load factor
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRUCache.this.capacity;
            }
        };
    }

    public int get(final int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(final int key, final int value) {
        cache.put(key, value);
    }
}
