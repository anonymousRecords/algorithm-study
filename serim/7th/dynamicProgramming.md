Chapter08 다이나믹 프로그래밍 : 한 번 계산 문제는 다시 계산하지 않도록 하는 알고리즘
## 1. 다이나믹 프로그래밍

### 중복되는 연산을 줄이자

- 다이나믹 의미
    - 프로그램이 실행되는 도중에
- 피보나치 수열
    - 수열 → 배열, 리스트

```python
# 피보나치 하마수(Fibonacci Funciton)를 재귀 함수로 구현
def fibo(x):
	if x == 1 or x == 2:
		return 1
	return fibo(x - 1) + fibo(x - 2)

print(fibo(4))
```

다이나믹 프로그래밍 조건

1. 큰 문제를 작은 문제로 나눌 수 있다
2. 작은 문제에서 구한 정답은 그것을 포함하는 큰 문제에서도 동일하다

메모제이션

- 다이나믹 프로그래밍을 구현하는 방법 중 한 종류
- 한 번 구한 결과를 메모리 공간에 메모해두고 같은 식을 다시 호출하면 메모한 겨로가를 그대로 가져오는 기법
- 캐싱 : 값을 저장하는 방법

메모제이션 구현 방법

- **한 번 구한 정보를 리스트에 저장하는 것**
- 다이나믹 프로그래밍을 재귀적으로 수행하다가
    - 같은 정보가 필요할 때는 이미 구한 정답을 그대로 리스트에서 가져오면 됨

```python
# 한 번 계산된 결과를 메모이제이션(Memoization)하기 위한 리스트 초기화
d = [0] * 100

# 피보나치 함수(Fibonacci Function)를 재귀함수로 구현(탑다운 다이나믹 프로그래밍)
def fibo(x):
	# 종료 조건 (1 혹은 2 일 때 1을 반환)
	if x == 1 or x == 2:
		return 1
	# 이미 계산한 적 있는 문제라면 그대로 반환
	if d[x] != 0:
		return d[x]
	# 아직 계산하지 않은 문제라면 점화식에 따라서 피보나치 결과 반환
	d[x] = fibo(x - 1) + fibo(x - 2)
	return d[x]

print(fibo(99))
```

분할 정복 vs 다이나믹 프로그래밍

- 다이나믹 프로그래밍의 경우 문제들이 서로 영향을 미치고 있음

호출되는 함수 확인

```python
d = [0] * 100

def pibo(x):
	print('f(' + str(x) + ')'. end='')
	if x == 1 or x == 2:
		return 1
	if d[x] != 0:
		return d[x]
	d[x] = pibo(x - 1) + pibo(x - 2)
		return d[x]

pibo(6)
```

탑다운 방식 vs 보텀업 방식

- 재귀 함수를 이용하여 다이나믹 프로그래밍 소스코드를 작성하는 방법
    - 탑다운 방식
        - 큰 문제를 해겨랗기 위해 작은 문제 호출
        - 하향식
    - **보텀업 방식**
        - 단순히 반복문을 이용하여 소스코드를 작성하는 경우
            - 작은 문제부터 차근차근 답을 도출
        - 상향식
        - 다이나믹 프로그래밍의 전형적인 형태

```python
# 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [0] * 100

# 첫 번째 피보나치 수와 두 번째 피보나치 수는 1
d[1] = 1
d[2] = 2
n = 99

# 피보나치 함수(Fibonacci Function) 반복문으로 구현(보텀업 다이나믹 프로그래밍)
for i in range(3, n + 1):
	d[i] = d[i - 1] + d[i - 2]

print(d[n])
```

## 2. 1로 만들기

```python
# 정수 X를 입력받기
x = int(input())

# 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [0]. * 30001

# 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
for i in range(2, x + 1):
	# 현재의 수에서 1을 빼는 경우
	d[i] = d[i - 1] + 1
	# 현재의 수가 2로 나누어 떨어지는 경우
	if  i % 2 == 0:
		d[i] = min(d[i], d[i // 2] + 1)
	# 현재의 수가 3로 나누어 떨어지는 경우
	if  i % 3 == 0:
		d[i] = min(d[i], d[i // 3] + 1)
	# 현재의 수가 5로 나누어 떨어지는 경우
	if  i % 5 == 0:
		d[i] = min(d[i], d[i // 5] + 1)

print(d[x])
```

## 3. 개미 전사

```python
# 정수 N을 입력받기
n = int(input())
# 모든 식량 정보 입력받기
array = list(map(int, input().split()))

# 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [0] * 100

# 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
d[0] = array[0]
d[1] = max(array[0], array[1])
for i in range(2, n):
	d[i] = max(d[i - 1], d[i - 2] + array[i])

# 계산된 결과 출력
print(d[n - 1])
```

## 4. 바닥 공사

```python
# 정수 N을 입력받기
n = int(input())

# 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [0] * 1001

# 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
d[1] = 1
d[2] = 3
for i in range(3, n + 1):
	d[i] = d[i - 1] + 2 * d[i - 2]) % 796796

# 계산된 결과 출력
print(d[n])
```

## 5. 효율적인 화폐 구성

```python
# 정수 N, M을 입력받기
n, m = map(int, input().split())

# N개의 화폐 단위 정보를 입력받기
array = []
for i in range(n):
	array.append(int(input())

# 한 번 계산된 결과를 저장하기 위한 DP 테이블 초기화
d = [10001] + (m + 1)

# 다이나믹 프로그래밍(Dynamic Programming) 진행 (보텀업)
d[0] = 0
for i in range(n):
	for j in range(array[i], m + 1):
		if d[j - array[i]] != 10001: # (i - k)원을 만드는 방법이 존재하는 경우
			d[j] = min(d[j], d[j - array[i]] + 1)

# 계산된 결과 출력
if d[m] == 10001: # 최종적으로 M원을 만드는 방법이 없는 경우
	print(-1)
else: 
	print(d[m])

```