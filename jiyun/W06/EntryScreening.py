def solution(n, times):
    answer = 0
    start = 1

    end = max(times) * n
    while start <= end:
        people = 0
        mid = (start + end) // 2
        for time in times:
            people += mid//time

        if people >= n:
            answer = mid
            end = mid - 1
        else:
            start = mid + 1
    return answer