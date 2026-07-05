class Solution {
    public int climbStairs(int n) {
        //if (n == 0) return 1;  // 1 way (empty sum)
        if (n == 1) return 1;
        int a = 1; // ways(0)
        int b = 1; // ways(1)
        for (int i = 2; i <= n; i++) {
            int c = a + b; // ways(n) = ways(n-1) + ways(n-2)
            a = b;
            b = c;
        }
        return b;
    }
}