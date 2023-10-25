n = int(input()) # 7
arr = [list(map(int, input().split())) for _ in range(n)] # [[3, 10], [5, 20], ...]
result = [0] * (n + 1)

for i in range(n):
    start_date = i
    end_date = i + arr[i][0]
    pay = arr[i][1]
    # print(pay)
    
    # select ith
    if end_date <= n:
        result[end_date] = max(result[end_date], result[start_date] + pay)
    
    # no select ith
    if (start_date + 1) <= n:
        result[start_date+1] = max(result[start_date+1], result[start_date])
    
    
    # print()
    # print(start_date, end_date)
    # print(result)

print(max(result))