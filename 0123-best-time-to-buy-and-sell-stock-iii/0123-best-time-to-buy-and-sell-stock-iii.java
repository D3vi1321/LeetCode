class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int buy1 = prices[0];
        int profit1 = 0;

        int buy2 = prices[0];
        int profit2 = 0;

        for (int i = 1; i < n; i++) {
            // First transaction
            buy1 = Math.min(buy1, prices[i]);
            profit1 = Math.max(profit1, prices[i] - buy1);

            // Second transaction
            buy2 = Math.min(buy2, prices[i] - profit1);
            profit2 = Math.max(profit2, prices[i] - buy2);
        }

        return profit2;
    }
}
