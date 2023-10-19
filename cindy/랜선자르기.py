k, n = map(int, input().split())
lans = list(int(input()) for _ in range(k))

start = 1
end = max(lans)

while start <= end:
    cnt = 0
    mid = (start + end) // 2
    for lan in lans:
        cnt += (lan // mid)
    if cnt >= n:
        start = mid + 1
    elif cnt < n:
        end = mid - 1

print(end)


# 하나의 길이값이, 다른 길이값을 최장길이로 나눈 값보다도 너무 작은 경우

# 3 3

# 1000 1000 1

# 정답 500

# 오답 1

# max_length = min(lans)

# while True:
#     cnt = 0
#     for lan in lans:
#         cnt += (lan // max_length)
#     max_length -= 1

#     if cnt >= n:
#         break

# print(max_length + 1)

