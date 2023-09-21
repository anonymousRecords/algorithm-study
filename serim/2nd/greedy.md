# 그리디
## 1. 당장 좋은 것만 선택하는 그리디

- 그리디 = 탐욕법
- 현재 상황에서 지금 당장 좋은 것만 고르는 방법
- 사전에 외우고 있지 않아도 풀 수 있는 가능성이 높은 문제 유형
- ‘가장 큰 순서대로’, ‘가장 작은 순서대로’와 같이 기준 제시하는 경우 다수
- 정렬 알고리즘과 자주 출제

### 거스름돈

- 가장 큰 화폐 단위부터 돈을 거슬러 줌

```python
n = 1260
count = 0

coint_types = [500, 100, 50, 10]

for coin in coin_types:
	count += n // coin
	n %= coin

print(count)
```

- 시간 복잡도 O(K)
    - 화폐의 종류 : K
    - 동전의 총 종류에만 영향을 받음
    - 거슬러 줘야 하는 금액의 크기와는 무관

### 그리디 알고리즘의 정당성

- 최적의 해를 찾을 수 없는 가능성 다분
- 해법이 정당한 지 검토할 필요성 존재
- 문제 풀이를 위한 최소한의 아이디어를 떠올리고 이것이 정당한지 검토할 수 있어야 답을 도출할 수 있음
- 문제 유형 파악하기 어려우면, 그리디 알고리즘인지 의심하기

## 2. 큰 수의 법칙

- N : 배열의 크기
- M : 숫자가 더해지는 횟수
- K : 배열의 특정한 인덱스(번호)에 해당하는 수가 연속해서 K번 초과 불가능

⇒ 가장 큰 수를 K번 더하고 두 번째로 큰 수를 한 번 더하는 연산 반복

풀이1.

- M이 커질 경우, 시간 초과가 발생 가능함

```python
n, m, k = map(int, input().split())
data = list(map(int, input().split()))

data.sort()
first = data[n - 1]
second = data[n - 2]

result = 0

while True:
	for i in range(k):
		if m == 0:
			break
		result += first
		m -= 1
	if m == 0:
		break
	result += second
	m -= 1

print(result)

```

풀이2.

- 반복되는 수열에 대해서 파악함
- int(M / (K+1)) * K + M % (K+1)

```python
n, m, k = map(int, input().split())
data = list(map(int, input().split()))

data.sort()
first = data[n - 1]
second = data[n - 2]

count = int(m / (k+1)) * k
count += m % (k+1)

result = 0
result += (count) * first
result += (m - count) * second

print(result)
```

## 3. 숫자 카드 게임

- N : 행의 개수
- M : 열의 개수
- 각 행마다 가장 작은 수를 찾은 뒤에 그 수 중에서 가장 큰 수를 찾는 것

풀이1 : min()함수

```python
n, m = map(int, input().split())

result = 0
for i in range(n):
	data = list(map(int, input().split()))
	min_value = min(data)
	result = max(result, min_value)

print(result)
```

풀이2 : 2중 반복문 구조

```python
n, m = map(int, input().split())

result = 0
for i in range(n):
	data = list(map(int, input().split()))
	min_value = 1001
	for a in data:
		min_value = min(min_value, a)
	result = max(result, min_value)

print(result)
```

## 4. 1이 될 때까지

- N이 될 때까지
- 두 번째 연산은 N이 K로 나누어떨어질 때만 선택 가능
- 과정
    - N에서 1을 뺀다
    - N을 K로 나눈다
- 최대한 많이 나누기를 수행

풀이1

```python
n, k = map(int, input().split())
result = 0

while n >= k:
	while n % k != 0:
		n -= 1
		result = += 1
	n //= k
	result += 1

while n > 1:
	n -= 1
	result += 1

print(result)
```

풀이2

- N이 클 경우, N이 K의 배수가 되도록 효율적으로 한 번에 빼는 방식

```python
n, k = map(int, input().split())
result = 0

while True:
	target = (n // k) * k
	result += (n - target)
	n = target
	if n < k:
		break
	result += 1
	n //= k

result += (n - 1)
print(result)
```