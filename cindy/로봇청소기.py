import sys

# n, m = map(int, input().split())
# r, c, d = map(int, input().split())
# graph = []
# for i in range(n):
#     graph.append(list(map(int, sys.stdin.readline().split())))

n, m = list(map(int,sys.stdin.readline().split()))
r, c, d = list(map(int,sys.stdin.readline().split()))
graph = [list(map(int,sys.stdin.readline().split())) for _ in range(n)]

# N, E, S, W
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
answer = 1

graph[r][c] = -1

while graph[r][c] != 1:
    end = False
    for _ in range(4):
        d -= 1
        if d == -1:
            d = 3
        nr = r + dx[d]
        nc = c + dy[d]
        if graph[nr][nc] == 0:
            r = nr
            c = nc
            graph[r][c] = -1
            answer += 1
            end = True
            break
    if not end:
        r += dx[d-2]
        c += dy[d-2]
        
print(answer)
    
