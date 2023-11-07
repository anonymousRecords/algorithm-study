import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static class Node implements Comparable<Node> {
        int to;
        int value;

        public Node(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Node e) {
            return this.value - e.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] data = reader.readLine().split(" ");

        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        ArrayList<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            data = reader.readLine().split(" ");
            int A = Integer.parseInt(data[0]);
            int B = Integer.parseInt(data[1]);
            int C = Integer.parseInt(data[2]);

            nodes[A].add(new Node(B, C));
            nodes[B].add(new Node(A, C));
        }

        boolean visited[] = new boolean[N + 1];

        int result = 0;
        int cnt = 0;

        // 최소신장 트리를 만들고나서
        // 가중치가 가장 큰 다리 자르면 최대효율
        int max = 0;

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            // 가장 낮은 가중치 pick
            Node now = queue.poll();

            if (visited[now.to]) continue;

            visited[now.to] = true;
            result += now.value;
            max = Math.max(max, now.value);
            cnt++;

            if (cnt == N) break;

            // 다음 연결 지점 찾기
            for (Node next : nodes[now.to]) {
                if (!visited[next.to]) {
                    queue.add(new Node(next.to, next.value));
                }
            }
        }

        System.out.println(result - max);
    }
}