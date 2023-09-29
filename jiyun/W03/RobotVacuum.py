import sys
input = sys.stdin.readline

# NxM 입력
n, m = map(int,input().split())
# (r,c)-> 로봇청소기의 시작좌표, d-> 시작 방향 입력
# 0:북 1:동 2:남 3:서
r,c,d = map(int, input().split())

# 로봇청소기 맵 생성
vac_map = [[0] * m for _ in range(n)]

# 지정된 좌표 이동 처리
vac_map[r][c] = 1

# 맵 입력받기
vm = []
for i in range(n):
    vm.append(list(map(int,input().split())))

# 북,동,남,서의 좌표 위치 정의
dx = [-1,0,1,0]
dy = [0,1,0,-1]

# 로봇 청소기 반시계회전
# 북쪽에서 서쪽을 갈 때만 값이 -1이 나오기 때문에 -1일 경우 3으로 방향 지정
def turn_left():
    global d
    d -= 1
    if d == -1:
        d = 3

# 처음 로봇 청소기 위치가 청소되기 때문에 +1
cnt = 1

#회전한 수
turn_cnt = 0

while True:
    turn_left()
    nx = r + dx[d]
    ny = c + dy[d]

    if vac_map[nx][ny] == 0 and vm[nx][ny] == 0:
        vac_map[nx][ny] = 1
        cnt += 1
        r, c = nx, ny
        turn_cnt = 0
        continue
    else:
        turn_cnt += 1

    if turn_cnt == 4:
        nx = r - dx[d]
        ny = c - dy[d]

        if vm[nx][ny] == 0:
            r, c = nx, ny
        else:
            break
        turn_cnt = 0

print(cnt)
