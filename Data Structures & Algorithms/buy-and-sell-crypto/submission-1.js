class Solution {
    /**
     * @param {number[]} prices
     * @return {number}
     */
    maxProfit(prices) {
        let minBuy = prices[0];
        let maxProfit = 0;

        for(let i = 1; i < prices.length; i++) {
            minBuy = Math.min(minBuy, prices[i-1]);
            maxProfit = Math.max(maxProfit, prices[i] - minBuy);
        }

        return maxProfit;
    }
}
