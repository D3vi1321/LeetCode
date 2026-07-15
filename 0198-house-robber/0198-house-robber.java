class Solution {
    public int rob(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        return robberHouse(0,nums,map);
    }

    public static int robberHouse(int index,int[] nums, HashMap<Integer, Integer> map)
    {
        if (index >= nums.length) return 0;

        if (map.containsKey(index)) {
            return map.get(index);
        }

        int numInclude = nums[index] + robberHouse(index+2,nums,map);
        int numSkip = robberHouse(index+1,nums,map);

        int result = Math.max(numInclude, numSkip);

        map.put(index, result);

        return result;
    }
}