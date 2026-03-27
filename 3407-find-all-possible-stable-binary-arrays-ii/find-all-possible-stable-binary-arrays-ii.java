class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {

        int mod = 1_000_000_007;
        int dp[][][] = new int[zero+1][one+1][2];

        for(int i=1;i<=zero;i++){
            dp[i][0][0] = (i<=limit) ? 1 : 0;
        }

        for(int j=1;j<=one;j++){
            dp[0][j][1] = (j<=limit) ? 1 : 0;
        }

        for(int i=1;i<=zero;i++){
            for(int j=1;j<=one;j++){

                
                dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % mod;

                if(i > limit){
                    dp[i][j][0] = (dp[i][j][0] - dp[i-limit-1][j][1] + mod) % mod;
                }

                // ending with 1
                dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % mod;

                if(j > limit){
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j-limit-1][0] + mod) % mod;
                }
            }
        }

        return (dp[zero][one][0] + dp[zero][one][1]) % mod;
    }
}