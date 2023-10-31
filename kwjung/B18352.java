import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    private void solution() throws Exception {
        String[] data = br.readLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);
        int K = Integer.parseInt(data[2]);
        int X = Integer.parseInt(data[3]);

        // 메모리 초과로 List로 변환
        List<Integer>[] nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            data = br.readLine().split(" ");
            int from = Integer.parseInt(data[0]);
            int to = Integer.parseInt(data[1]);
            nodes[from].add(to);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        dist[X] = 0;

        List<Integer> answers = new ArrayList<>();

        while (!queue.isEmpty()) {
            int now = queue.poll();
            // 더 갈필요 X
            if (dist[now] > K) break;
            if (dist[now] == K) answers.add(now);

            // 나머지 연결된 길들
            for (int next : nodes[now]) {
                // 못가는 길 패스
                if (dist[next] != -1) continue;
                dist[next] = dist[now] + 1;
                queue.add(next);
            }
        }

        Collections.sort(answers);
        StringBuilder builder = new StringBuilder();
        for (int now : answers) {
            builder.append(now).append('\n');
        }

        System.out.print(answers.isEmpty() ? -1 : builder);
    }
}