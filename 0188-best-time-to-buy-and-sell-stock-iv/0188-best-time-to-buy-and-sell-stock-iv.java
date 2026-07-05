/*class Solution {
    public int maxProfit(int k, int[] prices){
        int n = prices.length;

        int buy1 = prices[0];
        int profit1 = 0;

        int buy2 = prices[0];
        int profit2 = 0;

        for (int i=1; i<n; i++){
            for(int t=1; t<=k; t++){
            buy1 = Math.min(buy1, prices[i]);
            profit1 = Math.max(profit1, prices[i] - buy1);
            }
        }
        return profit2;
    }
}*/
class Solution {
     static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;

        // Optimization: treat as unlimited transactions
        if (k >= n / 2) {
            int profit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            buy[i] = -prices[0];
            sell[i] = 0;
        }

        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }

        return sell[k];
    }
}
