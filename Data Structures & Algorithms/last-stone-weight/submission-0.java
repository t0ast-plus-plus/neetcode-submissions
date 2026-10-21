class Solution {
    public int lastStoneWeight(final int[] in) {
        // establish a queue of stones to smash together, ordered by decreasing size
        Queue<Integer> stones = new PriorityQueue<>(Comparator.reverseOrder());
        for(final int i : in) {
            stones.add(i);
        }
        
        // SMASH!
        while(stones.size() > 1) {
            // grab first two stones and smash them together
            // remaining stone amount = larger stone minus smaller stone
            final int remainingStone = stones.poll() - stones.poll();
            
            // re-enqueue the remaining stone amount, if any
            if(remainingStone > 0) {
                stones.offer(remainingStone);
            }
        }

        // result
        return stones.isEmpty() ? 0 : stones.poll();
    }
}
