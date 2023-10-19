def solution(n, times):
    max_time = min(times)
    
    start = 0
    end = n * max_time
    
    while start <= end:
        cnt = 0
        mid = (start + end) // 2
        for time in times:
            cnt += mid // time
        
        if cnt < n:
            start = mid + 1
            # print("[1]", start, mid, end, cnt)
        elif cnt >= n:
            end = mid - 1
            # print("[2]", start, mid, end, cnt)
    
    answer = start
    return answer