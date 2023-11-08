import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int value;

        public Node(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] data = reader.readLine().split(" ");
        int V = Integer.parseInt(data[0]);
        int E = Integer.parseInt(data[1]);

        List<Node>[] list = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            data = reader.readLine().split(" ");
            int A = Integer.parseInt(data[0]);
            int B = Integer.parseInt(data[1]);
            int C = Integer.parseInt(data[2]);
            list[A].add(new Node(B, C));
            list[B].add(new Node(A, C));
        }

        int answer = 0;
        boolean[] visited = new boolean[V + 1];

        // 프림 알고리즘
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            // 가장 낮은 가중치 pick
            Node now = queue.poll();
            int now = now.to;
            int weight = now.value;

            if (visited[now]) continue;

            visited[now] = true;
            answer += weight;

            // 다음 연결 지점 찾기
            for (Node next : list[now]) {
                if (!visited[next.to]) {
                    queue.add(next);
                }
            }
        }

        System.out.println(answer);
    }
}