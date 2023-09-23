package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 14891번 톱니바퀴
 * 한 시간동안 헤매다가 직접 그림 그려서 보니까 바로 해결 ㅠㅠ
 */
public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[][] gears = new boolean[5][8];

        for (int i = 1; i < gears.length; ++i) {
            String tmp = br.readLine();
            for (int j = 0; j < gears[i].length; ++j) {
                gears[i][j] = tmp.charAt(j) == '1';
            }
        }

        int K = Integer.parseInt(br.readLine());
        Command[] commands = new Command[K];

        for (int i = 0; i < commands.length; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            commands[i] = new Command(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) == 1);
        }

        int rightSide = 2; // 오른쪽에서 맞닿는 톱니바퀴 인덱스
        int leftSide = 6; // 왼쪽에서 맞닿는 톱니바퀴 인덱스

        for (int i = 0; i < K; ++i) {
            int currentGear = commands[i].target; // 현재 톱니바퀴 번호
            boolean isClockWise = commands[i].isClockWise; // 회전 방향이 시계 방향 여부

            int leftGear = currentGear - 1;
            int rightGear = currentGear + 1;

            // 왼쪽 또는 오른쪽 톱니바퀴와 현재 톱니바퀴의 맞닿는 톱니의 극이 다르다면 회전 가능
            boolean isLeftRotatable = leftGear > 0 && gears[leftGear][rightSide] == !gears[currentGear][leftSide];
            boolean isRightRotatable = rightGear < 5 && gears[rightGear][leftSide] == !gears[currentGear][rightSide];

            // 현재 바퀴 회전
            rotate(gears[currentGear], isClockWise);

            // 왼쪽 톱니바퀴가 회전 가능하면
            if (isLeftRotatable) {
                List<Command> list = new ArrayList<>(); // 회전이 가능한 톱니바퀴 번호와 회전방향을 저장하는 리스트
                // 현재 톱니바퀴의 바로 왼쪽은 반대방향으로 저장
                list.add(new Command(leftGear, !isClockWise));
                for (int left = leftGear; left > 0; --left) {
                    // 그 왼쪽 톱니바퀴 체크
                    int moreLeftGear = left - 1;
                    isLeftRotatable = moreLeftGear > 0 && gears[moreLeftGear][rightSide] == !gears[left][leftSide];

                    // 회전 가능하면 이전 톱니바퀴의 반대방향으로 회전
                    if (isLeftRotatable)
                        list.add(new Command(moreLeftGear, !list.get(list.size() - 1).isClockWise));
                    else
                        break;
                }

                // 저장한 회전 정보를 바탕으로 회전 실행
                for (Command c : list)
                    rotate(gears[c.target], c.isClockWise);
            }

            if (isRightRotatable) {
                List<Command> list = new ArrayList<>();
                // 현재 톱니바퀴의 바로 오른쪽은 반대방향으로 저장
                list.add(new Command(rightGear, !isClockWise));
                for (int right = rightGear; right < 5; ++right) {
                    // 오른쪽 톱니바퀴 체크
                    int moreRightGear = right + 1;
                    isRightRotatable = moreRightGear < 5 && gears[moreRightGear][leftSide] == !gears[right][rightSide];

                    // 회전 가능하면 이전 톱니바퀴의 반대방향으로 회전
                    if (isRightRotatable)
                        list.add(new Command(moreRightGear, !list.get(list.size() - 1).isClockWise));
                    else
                        break;
                }
                for (Command c : list)
                    rotate(gears[c.target], c.isClockWise);
            }
        }
        int point = 0;

        point += gears[1][0] ? 1 : 0;
        point += gears[2][0] ? 2 : 0;
        point += gears[3][0] ? 4 : 0;
        point += gears[4][0] ? 8 : 0;

        System.out.println(point);
    }

    private static void rotate(boolean[] gear, boolean isClockWise) {
        boolean[] tmp = new boolean[gear.length];
        if (isClockWise) {
            for (int i = 0; i < gear.length; ++i) {
                tmp[(i + 1) % gear.length] = gear[i];
            }
        } else {
            for (int i = 0; i < gear.length; ++i) {
                tmp[(i - 1 + gear.length) % gear.length] = gear[i];
            }
        }
        System.arraycopy(tmp, 0, gear, 0, gear.length);
    }

    static class Command {
        int target;
        boolean isClockWise;

        public Command(int target, boolean isClockWise) {
            this.target = target;
            this.isClockWise = isClockWise;
        }
    }
}
