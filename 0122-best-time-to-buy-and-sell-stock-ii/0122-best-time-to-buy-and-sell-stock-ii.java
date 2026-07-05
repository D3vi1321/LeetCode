/*class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buyday = 0;
        int buy = prices[0];
        int sell = 0;
        int maxProfit = 0;
        int nextbuy=0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
                buyday = i;
            }
            if (i > buyday) {
                sell = prices[i];
                dp[i]=sell-buy;
                maxProfit = Math.max(maxProfit, sell - buy);
            }
        }
        int max2=0,lastmax2=0;
        for(int j=0; j<=n; j++){
            if(dp[j]>max2){
                lastmax2=max2;
                max2=dp[j];
            }
        }
        int result2=max2+lastmax2;
        return Math.max(maxProfit,result2);
    }
}
*/
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = prices[0];
        int sell = 0;
        int maxProfit = 0;

        for (int i = 1; i < n; i++) {
            if (prices[i] > buy) {
                // sell today
                sell = prices[i];
                maxProfit += sell - buy;

                // buy again immediately (allowed in Stock II)
                buy = prices[i];
            } else {
                // find cheaper buy
                buy = prices[i];
            }
        }
        return maxProfit;
    }
}
