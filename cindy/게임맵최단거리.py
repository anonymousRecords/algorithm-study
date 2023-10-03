from collections import deque

def solution(maps):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    n = len(maps)
    m = len(maps[0])
    queue = deque()
    queue.append((0, 0))
    
    while queue:
        x, y = queue.popleft()
        for idx in range(4):
            nx = x + dx[idx]
            ny = y + dy[idx]
            
            # out of graph range
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            
            # if the next position is a wall
            if maps[nx][ny] == 0:
                continue
            
            # if the next position is not visited
            if maps[nx][ny] == 1:
                maps[nx][ny] = maps[x][y] + 1
                queue.append((nx, ny))
                
    # if the destination is blocked
    if maps[n - 1][m - 1] == 1:
        return -1
    else:
        return maps[n - 1][m - 1]