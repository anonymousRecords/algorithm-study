package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14503번 로봇 청소기
 */
public class Main {
    public static final int[] dx = {0, 1, 0, -1}; // 북-동-남-서 순서
    public static final int[] dy = {-1, 0, 1, 0};
    public static final int NORTH = 0;
    public static final int WEST = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[][] room = new int[N][M];
        Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int answer = 0;

        // 방 상태 입력
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 1. 현재 칸 청소되지 않았으면 청소 수행
            if (room[robot.r][robot.c] == 0) {
                room[robot.r][robot.c] = -1;
                answer++;
            }

            boolean isCleanable = false;

            // 현재 칸 주변 4칸 빈칸 여부 검사
            for (int i = 0; i < dx.length; ++i) {
                Robot temp = new Robot(robot.r + dy[i], robot.c + dx[i], -1);

                // 3. 주변 4칸 중 빈 칸이 있는 경우
                if (room[temp.r][temp.c] == 0) {
                    isCleanable = true; // 청소 가능함
                    robot.d = rotateAsCounterClockwise90(robot.d); // 3-1. 반시계 회전

                    temp.r = robot.r + dy[robot.d];
                    temp.c = robot.c + dx[robot.d];
                    // 3-2. 빈 칸이면 전진
                    if (room[temp.r][temp.c] == 0) {
                        robot.r = temp.r;
                        robot.c = temp.c;
                        break;
                    }
                }
            }

            // 2. 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
            if (!isCleanable) {
                // 2-1. 후진 (= 현재의 역방향)
                robot.r += dy[getReverseDirection(robot.d)];
                robot.c += dx[getReverseDirection(robot.d)];

                // 2-2. 후진이 불가능한 경우 작동 중지
                if (room[robot.r][robot.c] == 1)
                    break;
            }
        }
        System.out.println(answer);
    }

    private static int rotateAsCounterClockwise90(int current) {
        if (current <= NORTH)
            return WEST;
        return current - 1;
    }

    private static int getReverseDirection(int direction) {
        return (direction + 2) % 4;
    }

    static class Robot {
        int r;
        int c;
        int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}