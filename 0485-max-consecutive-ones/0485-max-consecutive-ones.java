class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int freq = 0, maxfreq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                freq++;
            }
            if (nums[i] != 1) {
                if (freq > maxfreq) {
                    maxfreq = freq;
                }
                freq = 0;
            }
        }
        if(freq>maxfreq){
            maxfreq=freq;
        }
        return maxfreq;
    }
}