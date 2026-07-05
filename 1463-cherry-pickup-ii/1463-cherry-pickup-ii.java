class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // dpNext[j][k] will represent dp for row i+1 (next row)
        int[][] dpNext = new int[n][n];

        // Base case: last row (i = m-1)
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                dpNext[j][k] = (j == k) ? grid[m - 1][j] : grid[m - 1][j] + grid[m - 1][k];
            }
        }

        // Traverse rows bottom-up (i from m-2 down to 0)
        for (int i = m - 2; i >= 0; i--) {
            int[][] dpCurr = new int[n][n]; // dp for row i

            for (int j = 0; j < n; j++) {        // Alice's column
                for (int k = 0; k < n; k++) {    // Bob's column
                    int maxNext = 0;

                    // Try all 9 move combinations for next row
                    for (int dj = -1; dj <= 1; dj++) {
                        for (int dk = -1; dk <= 1; dk++) {
                            int nj = j + dj;
                            int nk = k + dk;
                            if (nj >= 0 && nj < n && nk >= 0 && nk < n) {
                                maxNext = Math.max(maxNext, dpNext[nj][nk]);
                            }
                        }
                    }

                    // add current row's cherries for positions j and k
                    if (j == k) {
                        dpCurr[j][k] = grid[i][j] + maxNext; // same cell counted once
                    } else {
                        dpCurr[j][k] = grid[i][j] + grid[i][k] + maxNext;
                    }
                }
            }

            // move up: current becomes next for the next iteration
            dpNext = dpCurr;
        }

        // Starting positions (top row): Alice at (0,0), Bob at (0,n-1)
        return dpNext[0][n - 1];
    }
}
