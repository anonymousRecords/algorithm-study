import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][] wheels = new int[4][8];
        for (int i = 0; i < wheels.length; i++) {
            String[] line = reader.readLine().split("");
            for (int j = 0; j < wheels[i].length; j++) {
                wheels[i][j] = Integer.parseInt(line[j]);
            }
        }

        int K = Integer.parseInt(reader.readLine());

        for (int i = 0; i < K; i++) {
            String[] line = reader.readLine().split(" ");
            int gear = Integer.parseInt(line[0]) - 1;
            int dir = Integer.parseInt(line[1]);
            turn(gear, dir, wheels);
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += Math.pow(2, i) * wheels[i][0];
        }
        System.out.println(answer);
    }


    public static void turn(int gear, int dir, int[][] wheels) {
        // 좌측 기어로
        left(gear - 1, -dir, wheels);

        // 우측 기어로
        right(gear + 1, -dir, wheels);
        rotation(gear, dir, wheels);
    }

    public static void left(int gear, int dir, int[][] wheels) {
        if (gear < 0) return;
        // 좌측 기어랑 우측 기어랑 같으면 패스
        if (wheels[gear][2] == wheels[gear + 1][6]) return;

        // 좌측기어 회전
        left(gear - 1, -dir, wheels);
        // 본인 회전
        rotation(gear, dir, wheels);
    }

    public static void right(int gear, int dir, int[][] wheels) {
        if (gear > 3) return;
        // 우측 기어랑 좌측 기어랑 같으면 패스
        if (wheels[gear][6] == wheels[gear - 1][2]) return;

        // 우측 회전
        right(gear + 1, -dir, wheels);
        // 본인 회전
        rotation(gear, dir, wheels);
    }

    public static void rotation(int idx, int dir, int[][] wheels) {
        if (dir == 1) {
            int temp = wheels[idx][7];
            for (int i = 7; i > 0; i--) {
                wheels[idx][i] = wheels[idx][i - 1];
            }
            wheels[idx][0] = temp;
            return;
        }

        int tmp = wheels[idx][0];
        for (int i = 0; i < 7; i++) {
            wheels[idx][i] = wheels[idx][i + 1];
        }
        wheels[idx][7] = tmp;
    }
}