package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 도시 개수 (= 노드)
        int m = Integer.parseInt(br.readLine()); // 버스 개수 (= 간선)
        long[][] graph = new long[n + 1][n + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i != j) // 자기 자신은 제외
                    graph[i][j] = 100000000L;
            }
        }
        for (int i = 0; i < m; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 도시
            int b = Integer.parseInt(st.nextToken()); // 도착 도시
            int c = Integer.parseInt(st.nextToken()); // 비용

            graph[a][b] = Math.min(graph[a][b], c); // 연결 노선은 하나가 아니므로 최소로 사용
        }

        for (int i = 1; i <= n; ++i) { // 거쳐가는 노드
            for (int j = 1; j <= n; ++j) { // 출발 노드
                if (i == j)
                    continue;
                for (int k = 1; k <= n; ++k) { // 비교 노드
                    // x에서 y로 가는 최소비용은 d[x][y] vs. d[x][k] + d[k][y]
                    if (j == k || k == i) // 자기 자신인 경우 제외
                        continue;
                    if (graph[j][k] > graph[j][i] + graph[i][k]) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                System.out.print((graph[i][j] == 100000000L ? "0" : graph[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
