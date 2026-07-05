class Solution {
    public int climbStairs(int n) {
        HashMap<Integer,Integer> map= new HashMap<>();
        return climb(0, n, map);
        
    }

        public static int climb(int currentStair, int targetStair, HashMap<Integer, Integer> map) {
            if (currentStair == targetStair) return 1;
            if (currentStair > targetStair) return 0;

            if(map.containsKey(currentStair))
            {
                return map.get(currentStair);
            }
            int firstStep = climb(currentStair+1, targetStair,map);
            int secondStep = climb(currentStair+2, targetStair,map);
           map.put(currentStair, firstStep+secondStep);

           return map.get(currentStair);
        }

}