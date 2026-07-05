/*class Solution {
    public int maxCoins(int[] nums) {
        int n=nums.length;
        int coins=0;
        int prevcoin=1, nextcoin=1;
        for(int i=0; i<n; i++){
            if(i-1<0){
            nextcoin=nums[i+1];
            coins+=prevcoin*nums[i]*nextcoin;
            prevcoin=1;
            nextcoin=1;
            }else if(i+1>=n){
                prevcoin=nums[i-1];
                coins+=prevcoin*nums[i]*nextcoin;
            }
        }
        return coins;
    }
}*/
/*
class Solution {
    int max = 0;

    public int maxCoins(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        burst(list, 0);
        return max;
    }

    private void burst(ArrayList<Integer> balloons, int coins) {
        if (balloons.size() == 0) {
            max = Math.max(max, coins);
            return;
        }

        for (int i = 0; i < balloons.size(); i++) {
            int curr = balloons.get(i);
            int left = (i == 0) ? 1 : balloons.get(i - 1);
            int right = (i == balloons.size() - 1) ? 1 : balloons.get(i + 1);

            int gained = left * curr * right;

            // choose
            balloons.remove(i);

            // explore
            burst(balloons, coins + gained);

            // backtrack
            balloons.add(i, curr);
        }
    }
}*/
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // Add 1 at both ends
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        // dp[left][right] = max coins from bursting balloons between left and right
        int[][] dp = new int[n + 2][n + 2];

        for (int length = 1; length <= n; length++) {
            for (int left = 1; left + length - 1 <= n; left++) {
                int right = left + length - 1;

                for (int k = left; k <= right; k++) {
                    int coins = arr[left - 1] * arr[k] * arr[right + 1]
                              + dp[left][k - 1]
                              + dp[k + 1][right];

                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[1][n];
    }
}
