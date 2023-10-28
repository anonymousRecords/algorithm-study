package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main9 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int K = Integer.parseInt(st.nextToken()); // 특정 거리
        int X = Integer.parseInt(st.nextToken()); // 출발 도시

        ArrayList<Node>[] graph = new ArrayList[N + 1];

        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, 1));
        }

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        dist[X] = 0;
        queue.add(new Node(X, 1));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (visited[current.index])
                continue;

            visited[current.index] = true;

            for (Node next : graph[current.index]) {
                if (dist[next.index] > dist[current.index] + next.cost) {
                    dist[next.index] = dist[current.index] + next.cost;
                    queue.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        boolean isThere = false;
        for (int i = 1; i < dist.length; ++i) {
            if (dist[i] == K) {
                isThere = true;
                System.out.println(i);
            }
        }
        if (!isThere)
            System.out.println(-1);
    }

    static class Node {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
