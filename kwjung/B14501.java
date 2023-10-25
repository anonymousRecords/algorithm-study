import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[] T = new int[N + 10];
        int[] P = new int[N + 10];

        // T의 값이 5이하 이므로 넉넉하게
        int[] dp = new int[N + 10];

        for (int i = 1; i <= N; i++) {
            String[] data = reader.readLine().split(" ");
            // 상담 걸리는 일
            T[i] = Integer.parseInt(data[0]);
            // 돈
            P[i] = Integer.parseInt(data[1]);
        }

        int max = 0;
        for (int i = 1; i <= N + 1; i++) {
            // 기존 수입을 비교하면서 최대 수입 갱신
            dp[i] = Math.max(dp[i], max);

            // 기존 수입 vs 새로 일할 때 수입
            dp[T[i] + i] = Math.max(dp[T[i] + i], P[i] + dp[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }

}