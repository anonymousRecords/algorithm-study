def solution(maps):
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    xLen = len(maps)
    yLen = len(maps[0])

    queue = [(0, 0, 1)]  

    while queue:
        x, y, cnt = queue.pop(0)

        if x == xLen - 1 and y == yLen - 1:
            return cnt

        if maps[x][y]:
            maps[x][y] = 0 

            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]

                if 0 <= nx < xLen and 0 <= ny < yLen:
                    queue.append((nx, ny, cnt + 1))

    return -1