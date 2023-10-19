import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        int K = Integer.parseInt(split[0]);
        int N = Integer.parseInt(split[1]);

        int[] arr = new int[K];
        long max = -1;


        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
            // 자를 때 가장 큰 단위
            max = Math.max(max, arr[i]);
        }

        long min = 1;
        long answer = 0;

        while (min <= max) {
            long count = 0;
            long mid = (min + max) / 2;

            // 각 단위로 잘랐을 떄 나오는 랜선 수
            for (int i = 0; i < K; i++) {
                count += arr[i] / mid;
            }

            // N개 이상 만들 수 있는 경우
            if (count >= N) {
                // 새로 찾은 단위가 기존 단위보다 크면 갱신
                if (answer < mid) {
                    answer = mid;
                }
                min = mid + 1;
                continue;
            }
            max = mid - 1;
        }

        System.out.println(answer);
    }
}