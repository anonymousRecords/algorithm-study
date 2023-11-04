from collections import deque
import sys

n, m, k, x = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    s, e = map(int, sys.stdin.readline().split())
    graph[s].append(e)

visited = [-1] * (n + 1)
q = deque([x])
visited[x] = 0
answer = []

while q:
    # print(visited)
    # print()
    current_node = q.popleft()
    if visited[current_node] == k:
        answer.append(current_node)
    elif visited[current_node] < k:
        for next_node in graph[current_node]:
            # next node which can move
            if visited[next_node] == -1:
                visited[next_node] = visited[current_node] + 1
                q.append(next_node)
# print(visited)

if len(answer) == 0:
    print("-1")
else:
    answer.sort()
    for a in answer:
        print(a)