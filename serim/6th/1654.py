K, N = map(int, input().split())
lan_cables = [int(input()) for _ in range(K)]
start = 1  
end = max(lan_cables)  
result = 0

while start <= end:
    total = 0
    mid = (start + end) // 2

    for cable in lan_cables:
        total += cable // mid

    if total >= N:
        result = mid
        start = mid + 1
    else:
        end = mid - 1

print(result)