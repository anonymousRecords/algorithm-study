def solution(m, n, puddles):
    # m = col(4), n = row(3)
    graph = list([0] * m for _ in range(n))
    graph[0][0] = 1
    
    # mark puddles
    for puddle in puddles:
        px, py = puddle[1] - 1, puddle[0] - 1
        graph[px][py] = -1
    
    for r in range(n):
        for c in range(m):
            # except puddles and home
            if graph[r][c] == -1 or (r == 0 and c == 0):
                continue
            else:
                lx, ly = r, (c - 1)
                tx, ty = (r - 1), c
                left, top = graph[lx][ly], graph[tx][ty]
                
                # puddle or out of range
                if left == -1 or ly < 0:
                    left = 0
                if top == -1 or tx < 0:
                    top = 0
                    
                graph[r][c] = left + top
                # print(graph[r][c])
                # print()
    # print(graph)
    if graph[n-1][m-1] == 0:
        return 0
    return graph[n-1][m-1] % 1000000007