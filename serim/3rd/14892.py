from collections import deque

# x : 현재 기어의 번호, d : 회전 방향
# 시계 방향으로 회전하는 함수 정의
def rotate_right(x, d):
    if x > 4 or gears[x - 1][2] == gears[x][6]:
        return

    if gears[x - 1][2] != gears[x][6]:
        rotate_right(x + 1, -d)
        gears[x].rotate(d)

# 반시계 방향으로 회전하는 함수 정의
def rotate_left(x, d):
    if x < 1 or gears[x][2] == gears[x + 1][6]:
        return

    if gears[x][2] != gears[x + 1][6]:
        rotate_left(x - 1, -d)
        gears[x].rotate(d)

# 4개의 기어 저장하는 변수
gears = {}
for i in range(1, 5):
    gears[i] = deque((map(int, input())))
# 회전 정보를 입력받을 횟수를 지정
for _ in range(int(input())):
    x, d = map(int, input().split())

    rotate_right(x + 1, -d)
    rotate_left(x - 1, -d)
    gears[x].rotate(d)

ans = 0
for i in range(4):
    ans += gears[i + 1][0] * (2 ** i)

print(ans)