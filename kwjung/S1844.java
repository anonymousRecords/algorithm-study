import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static int BFS(int[][] maps) {
        Queue<Integer[]> queue = new LinkedList<>();
        int[][] dp = new int[maps.length][maps[0].length];
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        queue.add(new Integer[]{0, 0, 0});

        while (!queue.isEmpty()) {
            Integer[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];
            int dt = poll[2];

            // 벽 or 이미 방문
            if (maps[y][x] == 0 || visited[y][x]) continue;

            visited[y][x] = true;

            // 최단 거리인지? 그러면 갱신
            if (dp[y][x] != 0 && dp[y][x] < dt) continue;
            dp[y][x] = dt;

            // 상하좌우 방문
            for (int i = 0; i < dy.length; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= maps.length || nx < 0 || nx >= maps[0].length) continue;
                queue.add(new Integer[]{ny, nx, dt + 1});
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public int solution(int[][] maps) {
        int answer = 0;
        answer = BFS(maps);
        if (answer == 0) return -1;
        // 시작지점 + 1
        return answer + 1;
    }
}