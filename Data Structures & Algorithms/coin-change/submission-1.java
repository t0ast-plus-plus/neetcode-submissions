class Solution {
    public int coinChange(int[] coins, int amount) {
        int minCoins = calc(coins, amount, new HashMap<>());
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    // hashmap memo stores min coins by amount
    private int calc(int[] coins, int amount, Map<Integer, Integer> memo) {
        if(amount == 0) {
            // no amount requires no coins to satisfy
            return 0;
        }

        if (memo.containsKey(amount)) {
            // return previously calculated min coins for this amount
            return memo.get(amount);
        }

        // start from an unreachable worst case int value to represent "unsolvable"
        // this is safe because coin values are >= 1 and amount is <= 10000
        // in other circumstances, null would be more appropriate
        int coinCount = Integer.MAX_VALUE;  

        // recurse on each usable coin to find min coin count from this amount
        for(int coin : coins) {
            if(amount - coin >= 0) {
                int result = calc(coins, amount-coin, memo);
                if (result != Integer.MAX_VALUE) {
                    coinCount = Math.min(coinCount, 1+result);
                }
            }
        }

        // update memo with best minimum coin count for this amount
        memo.put(amount, coinCount);
        return coinCount;
    }
}
