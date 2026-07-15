class Solution {

    public boolean canCross(int[] stones) {

        if (stones.length > 1 && stones[1] != 1)
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }

        return solve(stones, 1, 1, map, new HashMap<>());
    }

    public boolean solve(int[] stones,
                         int currentIndex,
                         int lastJump,
                         HashMap<Integer, Integer> map,
                         HashMap<String, Boolean> memo) {

        if (currentIndex == stones.length - 1)
            return true;

        String key = currentIndex + "-" + lastJump;

        if (memo.containsKey(key))
            return memo.get(key);

        boolean ans = false;

        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) {

            if (jump <= 0)
                continue;

            int nextStone = stones[currentIndex] + jump;

            if (map.containsKey(nextStone)) {

                if (solve(stones,
                          map.get(nextStone),
                          jump,
                          map,
                          memo)) {

                    ans = true;
                    break;
                }
            }
        }

        memo.put(key, ans);

        return ans;
    }
}