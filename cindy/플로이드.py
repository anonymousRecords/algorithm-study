import sys
from collections import deque

# 플로이드 알고리즘 풀이
n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
max_cost = 1e9 # max of int
answer = [[max_cost] * n for _ in range(n)]

for _ in range(m):
    s, e, c  = map(int, sys.stdin.readline().split())
    answer[s-1][e-1] = min(c, answer[s-1][e-1])

for start in range(1, n+1):
    for end in range(1, n+1):
        if start == end:
            answer[start-1][end-1] = 0

for mid in range(1, n+1):
    for start in range(1, n+1):
        for end in range(1, n+1):
            answer[start-1][end-1] = min(answer[start-1][end-1], answer[start-1][mid-1] + answer[mid-1][end-1])

for r in range(n):
    for c in range(n):
        if answer[r][c] == max_cost:
            answer[r][c] = 0
    print(*answer[r])


# 최단거리 풀이
# n = int(sys.stdin.readline()) # node
# m = int(sys.stdin.readline()) # route
# max_cost = 1e9 # max of int
# graph = [[] for _ in range(n + 1)]

# for _ in range(m):
#     s, e, c = map(int, sys.stdin.readline().split())
#     graph[s].append([e, c])

# # set start point as each column of output 2d list
# for start in range(1, n+1):
#     pay = [max_cost] * (n + 1)
#     queue = deque([(start, 0)]) # destination, cost

#     while(queue):
#         destination, cost = queue.popleft()
#         # if not visited or find the lower pay -> update pay
#         if pay[destination] == max_cost or cost < pay[destination]:
#             pay[destination] = cost
#             # try all given next bus routes
#             for route in graph[destination]:
#                 # set new_cost as (next_cost + current_cost)
#                 # because current_cost is minimun for start to current's dest
#                 queue.append([route[0], route[1] + cost])

#     for d in range(1, n+1):
#         # change unreachable destination's pay to 0
#         if pay[d] == max_cost:
#             pay[d] = 0
#     print(*pay[1:])