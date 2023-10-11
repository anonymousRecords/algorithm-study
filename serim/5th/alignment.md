# 1. 기준에 따라 데이터를 정렬

## 정렬 알고리즘 개요

정렬 Sorting

- 데이터를 특정한 기준에 따라서 순서대로 나열
- 선택 정렬, 퀵 정렬, 계수 정렬…

## 선택 정렬

- selection sort
- 가장 작은 데이터를 선택해 맨 앞에 있는 데이터와 바꾸고
- 그다음 작은 데이터를 선택해 앞에서 두 번째 데이터와 바꾸는 과정 반복
- ‘가장 작은 것을 선택’

```python
array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

for i in range(len(array)):
	min_index = i # 가장 작은 원소의 인덱스
	for j in range(i + 1, len(array)):
		if array[min_index] > array[j]:
			min_index = j
		array[i], array[min_index] = array[min_index], array[i] # 스와프

print(array)
```

- 스와프
    - 특정한 리스트가 주어졌을 때
    - 두 변수의 위치를 변경하는 작업

```python
# 0 인덱스와 1 인덱스의 원소 교체하기
array = [3, 5]
array[0], array[1] = array[1], array[0]

print(array)
```

### 선택 정렬의 시간 복잡도

- N - 1번 만큼 가장 작은 수를 찾아서 맨 앞으로 보내기
- 매번 가장 작은 수를 찾기 위해서 비교 연산 필요

## 삽입 정렬

- 선택 정렬에 비해 구현 난이도가 높음
- 선택 정렬에 비해 실행 시간 측면에서 더 효율적
- 데이터가 거의 정렬되어 있을 때 훨씬 효율적
- 특정한 데이터를 적절한 위치에 삽입

```python
array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

for i in range(1, len(array)):
	for j in range(i, 0, -1): # 인덱스 i부터 1까지 감소하며 반복하는 문법
		if array[j] < array[j - 1]: # 한 칸씩 왼쪽으로 이동
				array[j], array[j - 1] = array[j - 1], array[j]
		else: # 자기보다 작은 데이터를 만나면 그 위치에서 멈춤
			break

print(array)
```

- range의 세 번째 매개 변수
    - range(start, end step)
    - step = -1
        - start 인덱스부터 시작해서 end + 1 인덱스까지 1씩 감소
        

### 삽입 정렬의 시간 복잡도

- O(N^2)
- 거의 정렬되어 있는 상태로 입력이 주어지는 문제에서 효율적

### 퀵 정렬

- 방향 정렬 알고리즘
- 기준을 설정한 다음 큰 수와 작은 수를 교환한 후 리스트를 반으로 나누는 방식
- 피벗 pivot
    - 큰 숫자와 작은 숫자를 교환할 때, 교환하기 위한 ‘기준’

**분할 방식 hoare partition**

- 리스트에 첫 번째 데이터를 피벗으로 정함

```python
array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]

def quick_sort(array, start, end):
	if start >= end: # 원소가 1개인 경우 종료
		return
	pivot = start # 피벗은 첫 번째 원소
	left = start + 1
	right = end
	while left <= right:
		# 피벗보다 큰 데이트러르 찾을 때까지 반복
		while left <= end and array[left] <= array[pivot]:
			left += 1
		# 피벗보다 작은 데이터를 찾을 때까지 반복
		while right > start and array[right] >= array[pivot]:
			right -= 1
		if left > right: # 엇갈렸다면 작은 데이터와 피벗을 교체
			array[right], array[right] = array[pivot], array[right]
		else: # 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
			array[left], array[right] = array[right], array[left]
	# 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
	quick_sort(array, start, right - 1)
	quick_sort(array, right + 1, end)

quick_sort(array, 0, len(array) - 1)
print(print)
```

```python
array = [5, 7, 9, 0, 3, 1, 6, 2, 4, 8]

def quick_sort(array):
	# 리스트가 하나 이하의 원소만을 담고 있다면 종료
	if len(array) <= 1:
		return array

	pivot = array[0] # 피벗은 첫 번째 원소
	tail = array[1:] # 피벗을 제외한 리스트

	left_side = [x for x in tail if x <= pivot] # 분할된 왼쪽 부분
	right_side = [x for x in tail if x > pivot] # 분할된 오른쪽 부분

	# 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬을 수행하고, 전체 리스트를 반환
	return quick_sort(left_side) + [pivot] + quick_sort(right_side)

print(quick_sort(array))
```

### 퀵 정렬의 시간 복잡도

- O(N^2)
- 데이터가 무작위로 입력되는 경우 퀵 정렬은 빠르게 동작할 확률이 노픙ㅁ
- 리스트의 가장 왼쪽 데이터를 피벗으로 삼을 때, ‘이미 데이터가 정렬되어 있는 경우’에는 매우 느리게 동작함

## 계수 정렬

- 특정한 조건이 부합할 때만 사용할 수 있지만 매우 빠른 정렬 알고리즘
- 데이터의 크기 범위가 제한되어 정수 형태로 표현할 수 있을 때만 사용할 수 있음
- 모든 범위를 담을 수 있는 크기의 리스트(배열)를 선언해야 하기 때문

```python
# 모든 원소의 값이 0보다 크거나 같다고 가정
array = [7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2]
# 모든 범위를 포함하는 리스트 선언(모든 값은 0으로 초기화)
count = [0] * (max(array) + 1)

for i in range(len(array)):
	count[array[i]] += 1 # 각 데이터에 해당하는 인덱스의 값 증가

for i in range(len(count)): # 리스트에 기록된 정렬 정보 확인
	for j in range(count[i]):
		print(i, end= ' ') # 띄어쓰기를 구분으로 등장한 횟수만큼 인덱스 출력
```

### 계수 정렬의 시간 복잡도

- O(N + K)
- 기수 정렬과 더불어 가장 빠름

### 계수 정렬의 공간 복잡도

- 때에 따라서 심각한 비효율성 초래 가능
- 동일한 값을 가지는 데이터가 여러 개 등장할 때 적합
- 데이터의 크기가 한정되어 있음
- 데이터의 크기가 많이 중복되어 있을수록 유리
- 항상 사용할 수는 없음
- 조건만 만족한다면 계수 정렬은 정렬해야 하는 데이터의 개수가 매우 많을 때에도 효과적으로 사용할 수 있음

## 파이썬의 정렬 라이브러리

sorted()

- 리스트, 딕셔너리 자료형 등을 입력받아서 정렬된 결과를 출력함

```python
array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

result = sorted(array)
print(result)
```

sort()

```python
array = [7, 5, 9, 0, 3, 1, 6, 2, 4, 8]

array.sort()
print(array)
```

key 매개변수

```python
array = [('바나나', 2), ('사과', 5), ('당근', 3)]

def setting(data):
	return data[1]

result = sorted(array, key = setting)
print(result)
```

### 정렬 라이브러리의 시간 복잡도

1. 정렬 라이브러리로 풀 수 있는 문제
- 단순히 정렬 기법을 알고 있는지 물어보는 문제로 기본 정렬 라이브러리의 사용 방법을 숙지
1. 정렬 알고리즘의 원리에 대해서 물어보는 문제
- 선택 정렬, 삽입 정렬, 퀵 정렬 등의 원리 숙지 필요
1. 더 빠른 정렬이 필요한 문제
- 계수 정렬 등의 다른 정렬 알고리즘을 이용
- 문제에서 기존에 알려진 알고리즘의 구조적인 개선

# 2. 위에서 아래로

- 첫 번째 줄
    - N : 수열에 속해 있는 수의 개수
- 둘째 줄
    - N개의 수

```python
# N을 입력받기
n = int(input())

# N개의 정수를 입력받아 리스트에 저장
array = []
for i in range(n):
	array.append(int(input()))

# 파이썬 기본 정렬 라이브러리를 이용하여 정렬 수행
array = sorted(array, reverse=True)

# 정렬이 수행된 결과를 출력
for i in array:
	print(i, end=' ')
```

# 3. 성적이 낮은 순서로 학생 출력하기

- 첫 번째 줄
    - N : 학생의 수
- 두 번째 줄
    - A : 학생의 이름
    - B : 학생의 성적

```python
# N을 입력받기
n = int(input())

# N명의 학생 정보를 입력받아 리스트에 저장
array = []
for i in range(n):
	input_data = input().split()
	# 이름은 문자열 그대로, 점수는 정수형으로 변환하여 저장
	array.append((input_data[0], int(input_data[1])))

# 키(Key)를 이용하여, 점수를 기준으로 정렬
array = sorted(array, key=lambda student: student[1])

# 정렬이 수행된 결과를 출력
for student in array:
	print(student[0], end=' ')
```

# 4. 두 배열의 원소 교체

- 첫 번째 줄
    - N : 원소의 개수
    - K : 바꿔치기 횟수
- 두 번째 줄
    - 배열 A의 원소들이 공백으로 구분되어 입력
- 세 번째 줄
    - 배열 B의 원소들이 공백으로 구분되어 입력

```python
n, k = map(int, input().split()) # N과 K를 입력받기
a = list(map(int, input().split())) # 배열 A의 모든 원소를 입력받기
b = list(map(int, input().split())) # 배열 B의 모든 원소를 입력받기

a.sort() # 배열 A는 오름차순 정렬 수행
b.sort(reverse=True) # 배열 B는 내림차순 정렬 수행

# 첫 번째 인덱스부터 확인하며, 두 배열의 원소를 최대 K번 비교
for i in rnage(k):
	# A의 원소가 B의 원소보다 작은 경우
	if a[i] < b[i]:
		# 두 원소를 교체
		a[i], b[i] = b[i], a[i]
	else: # A의 원소가 B의 원소보다 크거나 같을 때, 반복문을 탈출
		break

print(sum(a)) # 배열 A의 모든 원소의 합을 출력
```