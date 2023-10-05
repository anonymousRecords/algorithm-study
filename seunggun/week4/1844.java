import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {1, 0, -1, 0};
    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;

        Queue<Point> queue = new LinkedList<>(); // BFS 탐색을 위한 큐 정의
        queue.add(new Point(0, 0));

        // BFS 시작
        while (!queue.isEmpty()) {
            Point c = queue.poll();

            // 현재 칸이 상대방 진영이면
            if (c.x == maps[0].length - 1 && c.y == maps.length - 1) {
                // 현재 최솟값과 지금까지 지나온 길에 대한 칸 개수 비교
                if (answer > maps[c.y][c.x])
                    answer = maps[c.y][c.x];
                break;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; ++i) {
                int tx = c.x + dx[i];
                int ty = c.y + dy[i];
                // 이동 가능한 범위면
                if (tx >= 0 && tx < maps[0].length && ty >= 0 && ty < maps.length) {
                    // 벽이 아니고 방문한 칸이 아니라면
                    if (maps[ty][tx] == 1) {
                        queue.add(new Point(tx, ty)); // 다음 너비 탐색을 위해 큐에 추가
                        maps[ty][tx] = maps[c.y][c.x] + 1; // 이전 경로에서 1 추가
                    }
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer; // 상대방 진영으로 갈 수 없는 경우 -1
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
