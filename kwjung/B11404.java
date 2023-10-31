import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        int[][] answer = new int[n][n];
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                if (i == j) {
                    answer[i][j] = 0;
                    continue;
                }

                answer[i][j] = 100001 * 100;
            }
        }

        for (int i = 0; i < m; i++) {
            String[] data = reader.readLine().split(" ");
            int start = Integer.parseInt(data[0]) - 1;
            int end = Integer.parseInt(data[1]) - 1;
            int cost = Integer.parseInt(data[2]);

            answer[start][end] = Math.min(answer[start][end], cost);
        }

        for (int k = 0; k < n; k++) {
            // 노드 i에서 j로 가는 경우.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // k번째 노드를 거쳐가는 비용이 기존 비용보다 더 작은 경우 갱신
                    answer[i][j] = Math.min(answer[i][j], answer[i][k] + answer[k][j]);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 연결이 안되어 있는 경우
                if (answer[i][j] == 100001 * 100) {
                    builder.append("0 ");
                    continue;
                }
                builder.append(answer[i][j]).append(" ");

            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

}