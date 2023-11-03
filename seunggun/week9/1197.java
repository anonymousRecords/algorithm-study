package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1197 최소 스패닝 트리
 */
public class Main11 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int cost = 0;

        int[] parents = new int[V + 1];
        Node[] graph = new Node[E];

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            graph[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i < parents.length; ++i) {
            parents[i] = i;
        }

        Arrays.sort(graph, (n1, n2) -> Integer.compare(n1.cost, n2.cost)); // 간선 비용을 오름차순 정렬

        for (Node node : graph) {
            /*
                current와 next는 이미 연결된 간선임
                current의 부모와 next의 부모가 같으면 삼각형으로 사이클이 발생함(사이클 형태의 3개의 연결된 간선)
                그래서 부모가 같지 않아야만 사이클 없는 집합에 속하는 것
             */
            if (find(parents, node.current) != find(parents, node.next)) {
                union(parents, node.current, node.next); // 집합을 합치는 것이지 MST를 연결하는 것은 아님 MST의 집합에는 (current, next) 간선이 추가
                cost += node.cost; // 최소 비용 더함
            }
        }
        System.out.println(cost);
    }

    public static int find(int[] parents, int current) {
        /*
            현재 노드부터 연결된 부모로 타고 들어갔을 때, 끝을 찾음
         */
        if (parents[current] == current) {
            return current;
        } else {
            return find(parents, parents[current]);
        }
    }

    public static void union(int[] parents, int a, int b) {
        a = find(parents, a); // 현재 a의 가장 끝의 부모 노드
        b = find(parents, b); // 현재 b의 가장 끝의 부모 노드
        if (a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }

    static class Node {
        int current;
        int next;
        int cost;

        public Node(int current, int next, int cost) {
            this.current = current;
            this.next = next;
            this.cost = cost;
        }
    }
}
