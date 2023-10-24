import sys

N = int(input())

schedule = [list(map(int, sys.stdin.readline().split())) for i in range(N)]

dp = [0 for i in range(N+1)]

for i in range(N):
    for j in range(i+schedule[i][0], N+1):
        if dp[j] < dp[i] + schedule[i][1]:
            dp[j] = dp[i] + schedule[i][1]

print(dp[-1])


# 블로그에서 찾은 코드 보면서 다른 방법 생각해볼 예정!
# n = int(input())
# dp = [0] * (n + 1)
#
# arr = []
# for _ in range(n):
#     arr.append(list(map(int, input().split())))
#
# for i in range(n):
#     t, p = arr[i][0], arr[i][1]
#
#     if i + t > n:
#         continue
#
#     dp[i] = max(dp[:i + 1])
#     dp[i + t] = max(dp[i] + p, dp[i + t])
#
# print(max(dp))