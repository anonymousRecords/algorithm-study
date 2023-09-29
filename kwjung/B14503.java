import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int N = 0;
    public static int M = 0;
    public static int map[][];
    public static int answer = 0;

    public static int[][] xy = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void DFS(int y, int x, int direction) {
        if (y >= N || y < 0 || x >= M || x < 0 || map[y][x] == 1) return;
        if (map[y][x] == 0) {
            map[y][x] = 2;
            answer++;
        }

        boolean isLeft = false;
        for (int i = 0; i < 4; i++) {
            direction = direction == 0 ? 3 : direction - 1;
            int nextY = y + xy[direction][0];
            int nextX = x + xy[direction][1];

            if (nextY >= N || nextY < 0 || nextX >= M || nextX < 0) {
                continue;
            }

            // 청소할 칸이 있는 경우
            if (map[nextY][nextX] == 0) {
                DFS(nextY, nextX, direction);
                isLeft = true;
                break;
            }
        }

        // 빈칸이 없는 경우
        if (!isLeft) {
            // 후진
            DFS(y - xy[direction][0], x - xy[direction][1], direction);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        line = reader.readLine().split(" ");
        int start[] = new int[3];
        start[0] = Integer.parseInt(line[0]);
        start[1] = Integer.parseInt(line[1]);
        start[2] = Integer.parseInt(line[2]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        DFS(start[0], start[1], start[2]);
        System.out.println(answer);

    }
}