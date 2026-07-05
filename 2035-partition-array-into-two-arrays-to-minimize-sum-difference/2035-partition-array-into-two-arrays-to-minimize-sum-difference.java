import java.util.*;

class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int half = n / 2;
        int totalSum = 0;

        for (int num : nums) totalSum += num;

        int[] left = Arrays.copyOfRange(nums, 0, half);
        int[] right = Arrays.copyOfRange(nums, half, n);

        // Generate all subset sums for both halves
        List<List<Integer>> leftSums = generateSums(left);
        List<List<Integer>> rightSums = generateSums(right);

        // Sort rightSums for binary search
        for (int i = 0; i <= half; i++) {
            Collections.sort(rightSums.get(i));
        }

        int ans = Integer.MAX_VALUE;

        // Try each subset size from left half
        for (int i = 0; i <= half; i++) {
            for (int s1 : leftSums.get(i)) {
                // We picked i elements from left, so pick (half - i) from right
                List<Integer> list = rightSums.get(half - i);

                // target: half of totalSum
                int target = totalSum / 2 - s1;

                // Binary search for closest sum in right list
                int idx = Collections.binarySearch(list, target);
                if (idx < 0) idx = -idx - 1;

                // Check candidate at idx and idx-1
                if (idx < list.size()) {
                    int s2 = list.get(idx);
                    int sum1 = s1 + s2;
                    int sum2 = totalSum - sum1;
                    ans = Math.min(ans, Math.abs(sum1 - sum2));
                }
                if (idx > 0) {
                    int s2 = list.get(idx - 1);
                    int sum1 = s1 + s2;
                    int sum2 = totalSum - sum1;
                    ans = Math.min(ans, Math.abs(sum1 - sum2));
                }
            }
        }

        return ans;
    }

    // Generate all subset sums grouped by number of elements used
    private List<List<Integer>> generateSums(int[] arr) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= n; i++) res.add(new ArrayList<>());

        // Generate all subsets (2^n possibilities)
        for (int mask = 0; mask < (1 << n); mask++) {
            int bits = Integer.bitCount(mask);
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) sum += arr[i];
            }
            res.get(bits).add(sum);
        }
        return res;
    }
}
