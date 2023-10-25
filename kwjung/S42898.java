class Solution {

    public int solution(int m, int n, int[][] puddles) {
        if ((n == 1 || m == 1) && puddles.length > 0) {
            return 0;
        }
        int answer = 0;
        int[][] dp = new int[n][m];
        dp[0][0] = 1;

        // 폭우 맵에 반영
        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                // 시작점 스킵
                if (i == 0 && j == 0) {
                    continue;
                }

                // 호수일 때
                if (dp[i][j] == -1) {
                    continue;
                }

                // 맨 윗열
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }

                // 맨 왼열
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                // 왼 위 중에 호수인 경우
                if (dp[i - 1][j] == -1 || dp[i][j - 1] == -1) {
                    // 도착지의 왼쪽과 위쪽이 모두 호수면 -1 이 됨 return 할 때 처리
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
            }
        }
        return Math.max(dp[n - 1][m - 1], 0);
    }
}