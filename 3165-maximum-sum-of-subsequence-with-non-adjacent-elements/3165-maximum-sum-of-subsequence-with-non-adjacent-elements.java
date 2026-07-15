class Solution {

    class Node {
        long f00, f01, f10, f11;
    }

    private Node[] tree;
    private int[] nums;
    private static final int MOD = 1_000_000_007;

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int n = nums.length;
        this.nums = nums;
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        long ans = 0;

        for (int[] q : queries) {
            update(1, 0, n - 1, q[0], q[1]);
            ans = (ans + tree[1].f11) % MOD;
        }

        return (int) ans;
    }

    private void build(int idx, int l, int r) {
        tree[idx] = new Node();

        if (l == r) {
            tree[idx].f11 = Math.max(0, nums[l]);
            return;
        }

        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        pushUp(idx);
    }

    private void update(int idx, int l, int r, int pos, int val) {
        if (l == r) {
            tree[idx].f11 = Math.max(0, val);
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid)
            update(idx * 2, l, mid, pos, val);
        else
            update(idx * 2 + 1, mid + 1, r, pos, val);

        pushUp(idx);
    }

    private void pushUp(int idx) {
        Node left = tree[idx * 2];
        Node right = tree[idx * 2 + 1];

        tree[idx].f00 = Math.max(
                left.f00 + right.f10,
                left.f01 + right.f00);

        tree[idx].f01 = Math.max(
                left.f00 + right.f11,
                left.f01 + right.f01);

        tree[idx].f10 = Math.max(
                left.f10 + right.f10,
                left.f11 + right.f00);

        tree[idx].f11 = Math.max(
                left.f10 + right.f11,
                left.f11 + right.f01);
    }
}
