# 구현
## 1. 아이디어를 코드로 바꾸는 구현

### 피지컬로 승부하기

- 머릿속에 있는 알고리즘을 소스코드로 바꾸는 과정
- 풀이를 떠올리는 것은 쉽지만 소스코드로 옮기기 어려운 문제
- 완전 탐색 : 모든 경우의 수를 주저 없이 다 계산하는 해결 방법
- 시뮬레이션 : 문제에서 제시한 알고리즘을 한 단계씩 차례대로 직접 수행

### 구현 시 고려해야 할 메모리 제약 사항

**C/C++에서 변수의 표현 범위**

- 파이썬 이용 시, 자료형의 표현 범위 제한에 대해 깊게 이해하고 있지 않아도 됨

**파이썬에서 리스트 크기**

- 여러 개의 변수를 사용할 때는 리스트를 이용함 → 메모리 제한 염두
- **데이터 처리량이 많을 때는 꼭 메모리 제한 고려**
- 메모리 사용량 제한보다 더 적은 크기의 메모리를 사용해야 함

### 채점 환경

- 시간 제한, 메모리 제한 제공

### 구현 문제에 접근하는 방법

- 문제의 길이가 긴 편

<img width="475" alt="image" src="https://github.com/anonymousRecords/algorithm-study/assets/97885933/4f2df9d1-a5f6-4309-82ce-eab461a50146">


### 예제4-1 상하좌우
```jsx
L : 왼쪽으로 한 칸 이동
R : 오른쪽으로 한 칸 이동
U : 위로 한 칸 이동
D : 아래로 한 칸 이동
```

<img width="353" alt="image" src="https://github.com/anonymousRecords/algorithm-study/assets/97885933/fd006ddf-8656-4d0b-803f-d0b3d85ae535">


- N : 공간의 크기
- A가 이동할 계획서

**⇒ A가 최종적으로 도착할 지점의 좌표 (X, Y)**

- 일련의 명령에 따라서 개체를 차례대로 이동시킨다는 점에서 시뮬레이션 유형으로 분류됨
- 시뮬레이션 유형, 구현 유형, 완전 탐색 유형은 서로 유사한 점들이 많음.

```python
# N을 입력받기
n = int(input())
x, y = 1, 1
plans = input().split()

# L, R, U, D에 따른 이동 방향
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
move_types = ['L', 'R', 'U', 'D']

# 이동 계획을 하나씩 확인
for plan in plans:
	# 이동 후 좌표 구하기
	for i in range(len(mover_types)):
		if plan == move_types[i]:
			nx = x + dx[i]
			ny = y + dy[i]
	# 공간을 벗어나는 경우 무시
	if nx < 1 or ny < 1 or nx > n or ny > n:
		continue
	# 이동 수행
	x, y = nx, ny

print(x, y)
```

### 예제 4-2 시각

- 00시 00분 00초부터 N시 59분 59초까지의 모든 시각 중에서 3이 하나라도 포함되는 모든 경우의 수
- 완전 탐색 유형으로 분류되기도 함
- 확인(탐색)해야 할 전체 데이터의 개수가 100만 개 이하일 때 완전 탐색을 사용하면 적절함

```python
# H를 입력받기
h = int(input())

count = 0
for i in range(h + 1):
	for j in range(60):
		for k in range(60):
		# 매 시각 안에 '3'이 포함되어 있다면 카우트 증가
		if '3' in str(i) + str(j) + str(k):
			count += 1

print(count)
```

## 2. 왕실의 나이트

```python
1. 수평으로 두 칸 이동한 뒤에 수직으로 한 칸 이동하기
2. 수직으로 두 칸 이동한 뒤에 수평으로 한 칸 이동하기
```

- 현재 나이트가 위치한 곳의 좌표

⇒ 나이트가 이동할 수 있는 경우의 수

```python
# 현재 나이트의 위치 입력받기
input_data = input()
row = int(input_data[1])
column = int(ord(input_data[0])) - int(ord('a')) + 1

# 나이트가 이동할 수 잇는 8가지 방향 정의
steps = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 2), (-1, 2), (-2, 1)]

# 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
result = 0
for step in steps:
	# 이동하고자 하는 위치 확인
	next_row = row + step[0]
	next_column = column + step[1]
	# 해당 위치로 이동이 가능하다면 카운트 증가
	if next_row >= 1 and next_row <= 8 and next_column >= 1 and next_column <= 8: result += 1

print(result)
```

## 3. 게임 개발

- A : 북쪽으로부터 떨어진 칸의 개수
- B : 서쪽으로부터 떨어진 칸의 개수

```python
1. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향(반시계 방향으로 90도 회전한 방향)부터 차례대로 갈 곳을 정한다.
2. 캐릭터의 바로 왼쪽 방향에 아직 가보지 않은 칸이 존재한다면, 왼쪽 방향으로 회전한 다음 왼쪽으로 한 칸을 전진한다. 왼쪽 방향에 가보지 않은 칸이 없다면, 왼쪽 방향으로 회전만 수행하고 1단계로 돌아간다.
3. 만약 네 방향 모두 이미 가본 칸이거나 바다로 되어 있는 칸인 경우에는，바라보는 방향을 유지한 채로 한 칸 뒤로 가고 1단계로 돌아간다. 단, 이때 뒤쪽 방향이 바다인 칸이라 뒤로 갈 수 없는 경우에는 움직임을 멈춘다.
```

**⇒ 첫째 줄에 이동을 마친 후 캐릭터가 방문한 칸의 수**

- 일반적으로 방향을 설정해서 이동하는 문제 유형에서는 **dx, dy라는 별도의 리스트를 만들어 방향을 정하는 것이 효과적**
- **2차원 리스트를 선언할 때는 컴프리헨션을 이용하는 것이 효율적**
```
# N, M을 공백으로 구분하여 입력받기
n, m = map(int, input().split())

# 방문한 위치를 저장하기 위한 맵을 생성하여 0으로 초기화
d = [[0] * m for _ in range(n)]
# 현재 캐릭터의 X 좌표, Y 좌표, 방향을 입력받기
x, y, direction = map(int, input().split())
d[x][y] = 1 # 현재 좌표 방문 처리

# 전체 맵 정보를 입력받기
array = []
for i in range(n):
	array.append(list(map(int, input().split())))

# 북, 동, 남, 서 방향 정의
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

# 왼쪽으로 회전
def turn_left():
	global direction
	direction -= 1
	if direction == -1:
		direction = 3

# 시뮬레이션 시작
count = 1
turn_time = 0
white True:
	# 왼쪽으로 회전
	turn_left()
	nx = x + dx[direction]
	ny = y + dy[direction]
	# 회전한 이후 정면에 가보지 않은 칸이 존재하는 경우 이동
	if d[nx][ny] == 0 and array[nx][ny] == 0:
		d[nx][ny] = 1
		x = nx
		y = ny
		count += 1
		turn_time = 0
		continue
	# 회전한 이후 정면에 가보지 않은 칸이 없거나 바다인 경우
	else:
		turn_time += 1
	# 네 방향 모두 갈 수 없는 경우
	if turn_time == 4:
		nx = x - dx[direction]
		ny = y - dy[direction]
		# 뒤로 갈 수 있다면 이동하기
		if array[nx][ny] == 0:
			x = nx
			y = ny
		# 뒤로 바다로 막혀있는 경우
		else:
			break
		turn_time = 0

# 정답 출력
print(count)
```
