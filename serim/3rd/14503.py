## 북, 동, 하, 서 ( 시계방향 )
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

n, m = map(int, input().split()) # 방의 크기
r, c, d = map(int, input().split()) # 로봇 청소기의 초기 위치, 초기 방향
arr = [list(map(int, input().split())) for _ in range(n)] # 방의 상태
visited = [[0]*m for _ in range(n)] # 방문 여부

visited[r][c] = 1
cnt = 1

while True:
    flag = 0            # 아직 청소하지 않은 방향
    for _ in range(4):  
        d = (d+3) % 4   # 왼쪽 방향으로 회전
        nr = r + dr[d]
        nc = c + dc[d]

        if 0 <= nr < n and 0 <= nc < m and arr[nr][nc] == 0:
            if visited[nr][nc] == 0:
                visited[nr][nc] = 1
                cnt += 1
                r = nr
                c = nc
                flag = 1      
                break

    if flag == 0:              
        if arr[r-dr[d]][c-dc[d]] == 1:
            print(cnt)
            break
        else:
            r, c = r-dr[d], c-dc[d]