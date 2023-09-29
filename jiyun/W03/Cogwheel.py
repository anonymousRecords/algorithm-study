import sys
# deque를 사용해서 풀던데 ..아직 이해를 잘 하지 못한 것 같습니다.
from collections import deque

input = sys.stdin.readline
t = [deque(list(map(int, input().rstrip()))) for _ in range(4)]  # 톱니의 상태 저장

# 몇 번 회전시킬지 입력 받기
k = int(input())
# 위에 입력 받은 k만큼 몇번 바퀴를 시계/반시계 입력 받기
for _ in range(k):
    r = []  # 처음 톱니 상태 저장
    for i in range(4):
        r.append([t[i][6], t[i][2]])
    n, d = map(int, input().split())
    n = n - 1

    # 왼쪽에 있는 톱니들 돌리기
    # 아직 collections rotate 몰라서 공부할 예정입니다!
    if n != 0:
        for i in range(n, 0, -1):
            if r[i][0] != r[i - 1][1]:
                if (n - (i - 1)) % 2 == 0:
                    t[i - 1].rotate(d)
                elif (n - (i - 1)) % 2 != 0:
                    t[i - 1].rotate(-1 * d)
            elif r[i][0] == r[i - 1][1]:
                break

    # 오른쪽에 있는 톱니들 돌리기
    if n != 3:
        for i in range(n, 3):
            if r[i][1] != r[i + 1][0]:
                if (n - (i + 1)) % 2 == 0:
                    t[i + 1].rotate(d)
                elif (n - (i + 1)) % 2 != 0:
                    t[i + 1].rotate(-1 * d)
            elif r[i][1] == r[i + 1][0]:
                break
    t[n].rotate(d)


score = 0
if t[0][0] == 1:
    score += 1
if t[1][0] == 1:
    score += 2
if t[2][0] == 1:
    score += 4
if t[3][0] == 1:
    score += 8
print(score)