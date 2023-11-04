import sys
from collections import deque

input = sys.stdin.readline

n, m, k, x = map(int, input().split())
graph = [[] for _ in range(n + 1)]

answer = []
visited = [-1 for _ in range(n + 1)]

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)


def bfs(v):
    queue = deque()
    queue.append(v)
    visited[v] += 1
    while queue:
        now = queue.popleft()
        for next in graph[now]:
            if visited[next] == -1:
                visited[next] = visited[now] + 1
                queue.append(next)


bfs(x)
for i in range(n + 1):
    if visited[i] == k:
        print(i)
        answer.append(i)
if len(answer) == 0:
    print(-1)
