# 1. 꼭 필요한 자료구조 기초

**탐색 search**

- 많은 양의 데이터 중에서 원하는 데이터를 찾는 과정
- 스택, 큐에 대한 이해가 전제되어야 함

**자료구조 data structure**

- 데이터를 표현하고 관리하고 처리하기 위한 구조
- `삽입` : 데이터를 삽입한다
- `삭제`  : 데이터를 삭제한다
- `오버플로`
    - 특정한 자료구조가 수용할 수 있는 데이터의 크기를 이미 가득 찬 상태에서 삽입연산을 수행할 때 발생
    - 저장 공간을 벗어나 데이터가 넘쳐흐를 때 발생
- `언더플로`
    - 특정한 자료구조가 전혀 들어 있지 않은 상태에서 삭제 연산을 수행
    - 데이터가 전혀 없는 상태

## 스택

스택 stack

- 박스 쌓기
- 선입후출, 후입선출 구조

```python
stack = []

# 삽입(5) - 삽입(2) - 삽입(3) - 삽입(7) - 삭제() - 삽입(1) - 삽입(4) - 삭제()
stack.append(5)
stack.append(2)
stack.append(3)
stack.append(7)
stack.pop()
stack.append(1)
stack.append(4)
stack.pop()

print(stack) # 최하단 원소부터 출력
print(stack[::-1]) # 최상단 원소부터 출력
```

- 파이썬에서 스택을 이용할 때에는 별도의 라이브러리 불필요
- append(), pop()

## 큐

큐Queue

- 대기줄
- ‘공정한’ 자료구조
- 선입선출 구조

```python
from collections import deque

# 큐(Queue) 구현을 위해 dequeue 라이브러리 사용
queue = deque()

# 삽입(5) - 삽입(2) - 삽입(3) - 삽입(7) - 삭제() - 삽입(1) - 삽입(4) - 삭제()
queue.append(5)
queue.append(2)
queue.append(3)
queue.append(7)
queue.append(5)
queue.popleft()
queue.append(1)
queue.append(4)
queue.popleft()

print(queue) # 먼저 들어온 순서대로 출력
queue.reverse() # 다음 출력을 위해 역순으로 바꾸기
print(queue) # 나중에 들어온 원소부터 출력
```

- 큐를 구현할 때는 collections 모듈에서 제공하는 deque 자료구조 활용
- deque
    - 데이터를 넣고 빼는 속도가 리스트 자료형에 비해 효율적
    - 간단
    - list() : deque 객체를 리스트 자료형으로 변경할 시

## 재귀 함수

재귀 함수 Recursive Function

- 자기 자신을 다시 호출하는 함수

```python
def recursive_function():
	print('재귀 함수를 호출합니다.')
	recursive_function()

recursive_function()
```

- ‘재귀 함수를 호출합니다.’ 무한히 출력

### 재귀 함수의 종료 조건

- 종료 조건 꼭 명시
    - 명시하지 않을 시, 무한 호출

```python
def recursive_function(i):
	# 100번째 출력했을 때 종료되도록 종료 조건 명ㅇ시
	if i == 100:
		return
		print(i, '번째 재귀 함수에서', i + 1, '번째 재귀 함수를 호출합니다.')
		recursive_function(i+1)
		print(i, '번째 재귀 함수를 종료합니다.')

recursive_function(1)
```

- 재귀 함수는 내부적으로 스택 자료구조와 동일
- 스택 자료구조를 활용해야 하는 상당수 알고리즘은 재귀 함수를 이용해서 간편하게 구현될 수 있음(예시 : DFS)

```python
# 반복적으로 구현한 n!
def factorial_iterative(n):
	result = 1
	# 1부터 n까지의 수를 차례대로 곱하기
	for i in range(1, n +1):
		result *= i
	return result

# 재귀적으로 구현한 n!
def factorial_recursive(n):
	if n <= 1: # n이 1이하인 경우 1을 반환
		return 1
	# n! = n * (n - 1)!를 그대로 코드로 작성하기
	return n * factorial_recursive(n - 1)

# 각각의 방식으로 구현한 n! 출력(n = 5)
print('반복적으로 구현:', factorial_iterative(5))
print('재귀적으로 구현:', factorial_recursive(5))
```

# 2. 탐색 알고리즘 DFS/BFS

## DFS

- Depth-First Search
- 깊이 우선 탐색
- 그래프에서 깊은 부분을 우선적으로 탐색하는 알고리즘

- 인접 행렬(Adjacency Matrix) : 2차원 배열로 그래프의 연결 관계를 표현하는 방식
- 인접 리스트(Adjacency List) : 리스트로 그래프의 연결 관계를 표현하는 방식

**인접 행렬 Adjacency Matrix**

```python
INF = 99999999999 # 무한의 비용 선언

# 2차원 리스트를 이용해 인접 행렬 표현
graph = [
	[0, 7, 5],
	[7, 0, INF],
	[5, INF, 0]
]

print(graph)
```

**인접 리스트 방식 Adjacency List**

```python
# 행(Row)이 3개인 2차원 리스트로 인접 리스트 표현
graph = [[] for _ in range(3))]

# 노드 0에 연결된 노드 정보 저장(노드, 거리)
graph[0].append(1, 7))
graph[0].append(2, 5))

# 노드 1에 연결된 노드 정보 저장(노드, 거리)
graph[1].append((0, 7))

# 노드 2에 연결된 노드 정보 저장(노드, 거리)
graph[2].append((0, 5))

pring(graph)
```

|  | 인접 행렬 방식 | 인접 리스트 방식 |
| --- | --- | --- |
| 메모리 | 모든 관계를 저장하므로 노드 개수가 많을수록 메모리가 불필요하게 낭비됨 | 연결된 정보만을 저장하기 때문에 메모리를 효율적으로 사용함 |
| 정보 얻는 속도 | 빠름 | 느림 (연결된 데이터를 하나씩 확인해야 하기 때문) |

**DFS 동작 과정**

1. 탐색 시작 노드를 스택에 삽입하고 방문 처리를 한다.
2. 스택의 최상단 노드에 방문하지 않은 인접 노드가 있으면 그 인접 노드를 스택에 넣고 방문을 처리를 한다. 방문하지 않은 인접 노드가 없으면 스택에서 최상단 노드를 꺼낸다.
3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.

```python
# DFS 메서드 정의
def dfs(graph, v, visited):
	# 현재 노드를 방문 처리
	visited[v] = True
	print(v, end=' ')
	# 현재 노드와 연결된 다른 노드를 재귀적으로 방문
	for i in graph[v]:
		if not visited[i]:
			dfs(graph, i, visited)

# 각 노드가 연결된 정보를 리스트 자료형으로 표현(2차원 리스트)
graph = [
	[],
	[2, 3, 8],
	[1, 7],
	[1, 4, 5],
	[3, 5],
	[3, 4],
	[7],
	[2, 6, 8],
	[1, 7]
]

# 각 노드가 방문된 정보를 리스트 자료형으로 표현 (1차원 리스트)
visited = [False] * 9

# 정의된 DFS 함수 호출
dfs(graph, 1, visited)
```

## BFS

- 너비 우선 탐색
- 가까운 노드부터 탐색하는 알고리즘

**BFS 동작 방식**

1. 탐색 시작 노드를 큐에 삽입하고 방문 처리를 한다.
2. 큐에서 노드를 꺼내 해당 노드의 인접 노드 중에서 방문하지 않은 노드를 모두 큐에 삽입하고 방문 처리를 한다.
3. 2번의 과정을 더 이상 수행할 수 없을 때까지 반복한다.

- 일반적인 경우 실제 수행 시간은 DFS보다 좋은 편

```python
from collections import deque

# BFS 메서드 정의
def bfs(graph, start, visited):
	# 큐(Queue) 구현을 위해 dequeue 라이브러리 사용
	queue = deque([start])
	# 현재 노드를 방문 처리
	visited[start] = True
	# 큐가 빌 때까지 반복
	while queue:
		# 큐에서 하나의 원소를 뽑아 출력
		v = queue.popleft()
		print(v, end= ' ')
		# 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
		for i in graph[v]:
			if not visited[i]:
				queue.append(i)
				visited[i] = True

# 각 노드가 연결된 정보를 리스트 자료형으로 표현(2차원 리스트)
graph = [
	[],
	[2, 3, 8],
	[1, 7],
	[1, 4, 5],
	[3, 5],
	[3, 4],
	[7],
	[2, 6, 8],
	[1, 7],
]

# 각 노드가 방문된 정보를 리스트 자료형으로 표현(1차원 리스트)
visited = [False] * 9

# 정의된 BFS 함수 호출
bfs(graph, 1, visited)
```

# 3. 음료수 얼려 먹기

- 첫 번째 줄
    - N : 얼음 틀의 세로 길이
    - M : 얼음 틀의 가로 길이
- 두 번째 줄
    - N+1 번째 줄까지 얼음 틀의 형태
- 0 : 구멍이 뚫려있는 부분
- 1: 그렇지 않은 부분
- 한 번에 만들 수 있는 아이스크림의 개수 출력

1. 특정한 지점의 주변 상, 하 좌, 우를 살펴본 뒤에 주변 지점 중에서 값이 ‘0’이면서 아직 방문하지 않은 지점이 있다면 해당 지점을 방문한다.
2. 방문한 지점에서 다시 상, 하, 좌, 우를 살펴보면서 방문을 다시 진행하면, 연결된 모든 지점을 방문할 수 있다.
3. 1 ~ 2번의 과정을 모든 노드에 반복하며 방문하지 않은 지점의 수를 센다.

```python
# N,M을 공백으로 구분하여 입력받기
n, m = map(int, input().split())

# 2차원 리스트의 맵 정보 입력받기
graph = []
for i in range(n):
	graph.append(list(map(int, input()))

# DFS로 특정한 노드를 방문하 뒤에 연결된 모든 노드들도 방문
def dfs(x, y):
	# 주어진 범위를 벗어나는 경우에는 즉시 종료
	if x <= -1 or x > n or y <= -1 or y >= m:
		return False
	# 현재 노드를 아직 방문하지 않았다면
	if graph[x][y] == 0:
		# 해당 노드 방문 처리
		graph[x][y] = 1
		# 상, 하, 좌, 우의 위치도 모두 재귀적으로 호출
		dfs(x - 1, y)
		dfs(x, y - 1)
		dfs(x + 1, y)
		dfs(x, y + 1)
		return True
	return False

# 모든 노드(위치)에 대하여 음료수 채우기
result = 0
for i in range(n):
	for j in range(m):
		# 현재 위치에서 DFS 수행
		if dfs(i, j) == True:
			result += 1
```

# 4. 미로 탈출

- 첫 번째 줄
    - N x M
- 두 번째 줄
    - 0 : 괴물이 있는 부분
    - 1 : 괴물이 없는 부분

```python
from collection import deque

# N, M을 공백으로 구분하여 입력받기
n, m = map(int, input().split())
# 2차원 리스트의 맵 정보 입력받기
graph = []
for i in range(n):
	graph.append(list(map(int, input())))

# 이동할 네 방향 정의(상, 하 좌, 우)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# BFS 소스코드 구현
def bfs(x, y):
	# 큐(Queue) 구현을 위해 deque 라이브러리 사용
	queue = deque()
	queue.append((x, y))
	# 큐가 빌 때까지 반복
	while queue:
		x, y = queue.popleft()
		# 현재 위치에서 네 방향으로의 위치 확인
		for i in range(4):
			nx = x + dx[i]
			ny = y + dy[i]
			# 미로 찾기 공간을 벗어난 경우 무시
			if nx < 0 or ny < 0 or nx >= n or ny >= m:
				continue
			# 벽인 경우ㅑ 무시
			if graph[nx][ny] == 0:
				continue
			# 해당 노드를 처음 방문하는 경우에만 최단 거리 기록
			if graph[nx][ny] = 1:
				graph[x][y] = graph[x][y] + 1
				queue.append((nx, ny))
	# 가장 오른쪽 아래까지의 최단 거리 반환
	return graph[n - 1][m - 1]

# BFS를 수행한 결과 출력
print(bfs(0, 0))
```
