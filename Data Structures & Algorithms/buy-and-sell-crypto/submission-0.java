class Solution {
    public int maxProfit(final int[] prices) {
        // edge case handling
        if(prices.length < 2) {
            return 0;
        }

        // iterate through 2nd day forward, keeping track of the following:
        
        // minimum buy value for all days prior to the current sell date being considered
        int minBuy = prices[0]; 

        // maximum possible profit across all possible sell dates
        int maxProfit = 0;
        
        for(int sell = 1; sell < prices.length; sell++) {
            minBuy = Math.min(minBuy, prices[sell-1]);
            maxProfit = Math.max(maxProfit, prices[sell] - minBuy);
        }

        return maxProfit;
    }
}
