import heapq

v, e = map(int, input().split())
arr = [[] for _ in range(v + 1)]

visit = [0 for _ in range(v + 1)]
heap = [[0, 1]]
answer = 0
cnt = 0

for _ in range(e):
    a, b, c = map(int, input().split())
    arr[a].append([c, b])
    arr[b].append([c, a])

while heap:
    if cnt == v:
        break

    c, b = heapq.heappop(heap)
    if visit[b] == 0:
        visit[b] = 1
        answer += c
        cnt += 1
        for tmp in arr[b]:
            heapq.heappush(heap, tmp)

print(answer)
