class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        // Worst case: cut before every character
        for (int i = 0; i <= n; i++) {
            dp[i] = i - 1;
        }

        for (int center = 0; center < n; center++) {

            // Odd-length palindromes
            for (int l = center, r = center;
                 l >= 0 && r < n && s.charAt(l) == s.charAt(r);
                 l--, r++) {

                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }

            // Even-length palindromes
            for (int l = center, r = center + 1;
                 l >= 0 && r < n && s.charAt(l) == s.charAt(r);
                 l--, r++) {

                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }
        }

        return dp[n];
    }
}

/*class Solution {
    public int minCut(String s) {
        int n= s.length();
        int ans=0;
        int dp[n+1];
        dp[0]=0;
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            sb.append(c);
        }
        int j=0;
        for(int i=0; i<n-1; i++;){
            for(int j=1; j<n-1; j++;){
            dp[j]=sb.substring(i,j);
            }
        }
        for(int i=0; i<n-1; i++){
            
        }
        return ans;
    }
}*/
/*class Solution {
    int min = Integer.MAX_VALUE;

    public int minCut(String s) {
        dfs(s, 0, -1);
        return min;
    }

    void dfs(String s, int start, int cuts) {
        if (start == s.length()) {
            min = Math.min(min, cuts);
            return;
        }

        for (int end = start; end < s.length(); end++) {
            String sub = s.substring(start, end + 1);
            if (isPalindrome(sub)) {
                dfs(s, end + 1, cuts + 1);
            }
        }
    }

    boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}
*/
/*class Solution {
    Integer[] memo;

    public int minCut(String s) {
        memo = new Integer[s.length()];
        return dfs(s, 0) - 1;
    }

    int dfs(String s, int start) {
        if (start == s.length()) return 0;
        if (memo[start] != null) return memo[start];

        int min = Integer.MAX_VALUE;
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                min = Math.min(min, 1 + dfs(s, end + 1));
            }
        }
        return memo[start] = min;
    }

    boolean isPalindrome(String s, int l, int r) {
        while (l < r)
            if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }
}

class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];

        for (int g = 0; g < n; g++) {
            for (int i = 0, j = g; j < n; i++, j++) {
                if (g == 0) isPal[i][j] = true;
                else if (g == 1) isPal[i][j] = s.charAt(i) == s.charAt(j);
                else isPal[i][j] = s.charAt(i) == s.charAt(j) && isPal[i+1][j-1];
            }
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) dp[i] = 0;
            else {
                dp[i] = i;
                for (int j = 1; j <= i; j++) {
                    if (isPal[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }
}

class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        // Worst case: cut before every character
        for (int i = 0; i <= n; i++) {
            dp[i] = i - 1;
        }

        for (int c = 0; c < n; c++) {

            // ✅ Odd-length palindromes
            for (int l = c, r = c;
                 l >= 0 && r < n && s.charAt(l) == s.charAt(r);
                 l--, r++) {

                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }

            // ✅ Even-length palindromes
            for (int l = c, r = c + 1;
                 l >= 0 && r < n && s.charAt(l) == s.charAt(r);
                 l--, r++) {

                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }
        }

        return dp[n];
    }
}

class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) dp[i] = i - 1;

        for (int center = 0; center < n; center++) {
            for (int l = center, r = center; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r--) {
                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }
            for (int l = center, r = center + 1; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r--) {
                dp[r + 1] = Math.min(dp[r + 1], dp[l] + 1);
            }
        }
        return dp[n];
    }
}
*/