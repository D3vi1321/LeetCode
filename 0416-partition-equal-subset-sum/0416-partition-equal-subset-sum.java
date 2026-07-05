class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;

        // If total sum is odd, cannot divide equally
        if (total % 2 != 0) return false;

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }
}
/*class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;

        // If total sum is odd, we can’t divide equally
        if (total % 2 != 0) return false;

        int target = total / 2;
        return canPartitionRec(nums, 0, target);
    }

    private boolean canPartitionRec(int[] nums, int index, int target) {
        // Base case 1: target reached → valid subset found
        if (target == 0) return true;

        // Base case 2: end of array or target < 0 → invalid path
        if (index >= nums.length || target < 0) return false;

        // Choice 1: Include current number
        boolean include = canPartitionRec(nums, index + 1, target - nums[index]);

        // Choice 2: Exclude current number
        boolean exclude = canPartitionRec(nums, index + 1, target);

        // If any path works, return true
        return include || exclude;
    }
}
*/
