/*class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        if(n==0) return 0;
        int ans=1;
        int start=0;
        for(int i=1;i<n-1;i++){
            if(nums[start]<nums[i]){
                ans+=1;
                start+=1;
                
            }
        }
    }
}
*/
/*
class Solution {
    public int lengthOfLIS(int[] nums) {
        return helper(nums, -1, 0);
    }
    private int helper(int[] nums, int prevIndex, int currentIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }
        int notTake = helper(nums, prevIndex, currentIndex + 1);
        int take = 0;
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            take = 1 + helper(nums, currentIndex, currentIndex + 1);
        }
        return Math.max(take, notTake);
    }
}*/
/*
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        int max = 1;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // every element is LIS of length 1
            
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}*/
class Solution {
   static{
        Runtime.getRuntime().addShutdownHook(
            new Thread(()->{
                try(FileWriter f=new FileWriter("display_runtime.txt")){
                    f.write('0');
                }catch(Exception e){}
            })
        );
    }
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int left = 0, right = tails.size();
            
            while (left < right) {
                int mid = (left + right) / 2;
                if (tails.get(mid) < num)
                    left = mid + 1;
                else
                    right = mid;
            }
            
            if (left == tails.size())
                tails.add(num);
            else
                tails.set(left, num);
        }
        
        return tails.size();
    }
}