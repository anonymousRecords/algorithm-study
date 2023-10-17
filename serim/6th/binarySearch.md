Chapter 07. 이진 탐색 : 탐색 범위를 반으로 좁혀가며 빠르게 탐색하는 알고리즘

## 1. 범위를 반씩 좁혀가는 탐색

### 순차 탐색

- 리스트 안에 있는 특정한 데이터를 찾기 위해 앞에서부터 데이터를 하나씩 차례대로 확인하는 방법
- 보통 정렬되지 않은 리스트에서 데이터를 찾아야 할 때 사용
- 리스트 내에 데이터가 아무리 많아도 시간만 충분하다면 항상 원하는 원소(데이터)를 찾을 수 있음

```python
# 순차 탐색 소스코드 구현
def sequential_search(n, target, array):
	# 각 원소를 하나씩 확인하며
	for i in range(n):
		# 현재의 원소가 찾고자 하는 원소와 동일한 경우
		if array[i] == target:
			return i + 1 # 현재의 위치 반환(인덱스는 0부터 시작하므로 1 더하기)

print("생성할 원소 개수를 입력한 다음 한 칸 띄고 찾을 문자열을 입력하세요.")
input_data = input().split()
n = int(input_data[0]) # 원소의 개수
target = input_data[1] # 찾고자 하는 문자열

print("앞서 적은 원소 개수만큼 문자열을 입력하세요. 구분은 띄어쓰기 한 칸으로 합니다.")
array = input().split()

# 순차 탐색 수행 결과 출력
print(sequential_search(n, target, array))
```

### 이진 탐색 : 반으로 쪼개면서 탐색하기

**이진탐색**

- 배열 내부의 데이터가 정렬되어 있어야만 사용할 수 있음
- 탐색 범위를 절반씩 좁혀가면 데이터를 탐색
- 변수
    - 시작점, 끝점, 중간점
    - 찾으려는 데이터와 중간점 위치에 있는 데이터를 반복적으로 비교해서 원하는 데이터를 찾음

**이진 탐색을 구현하는 방법**

1. 재귀 함수

```python
# 이진 탐색 소스코드 구현(재귀 함수)
def binary_search(array, target, start, end):
	if start > end:
		return None
	mid = (start + end) // 2
	# 찾은 경우 중간점 인덱스 반환
	if array[mid] == target:
		return. mid
	# 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
	elif array[mid] > targer:
		 return binary_search(array, target, start, mid - 1)
	# 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
	else:
		return binary_search(array, target, mid + 1, end)

# n(원소의 개수)과 target(찾고자 하는 문자열)을 입력받기
n, target = list(map(int, input().split())
# 전체 원소 입력받기
array = list(map(int, input().split()))

# 이진 탐색 수행 결과 출력
result = binary_search(arrya, target, 0, n - 1)
if result == None:
	print("원소가 존재하지 않습니다.")
else:
	print(result + 1)
```

1. 반복문

```python
# 이진 탐색 소스코드 구현(반복문)
def binary_search(array, target, start, end):
	while start <= end:
		mid = (start + end) // 2
		# 찾은 경우 중간점 인덱스 반환
			if array[mid] == target:
				return mid
			# 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
			elif array[mid] > target:
				end = mid - 1
			# 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
			else:
				start = mid + 1
		return None

# n(원소의 개수)과 target(찾고자 하는 문자열)을 입력받기
n, target = list(map(int, input().split()))
# 전체 원소 입력받기
array = list(map(int, input().split()))

# 이진 탐색 수행 결과 출력
result = binary_search(array, target, 0, n -1)
if result == None:
	print("원소가 존재하지 않습니다.")
else: 
	print(result + 1)
```

**코딩 테스트에서의 이진 탐색**

- 탐색 범위가 큰 상황에서의 탐색 가정하는 경우가 많음(탐색 범위가 2,000만을 넘을 경우)

### 트리 자료구조

- 전체 조건이 데이터 정렬

트리 자료구조

- 노드와 노드의 연결
- 노드
    - 정보의 단위
    - 어떠한 정보를 가지고 있는 개체로 이해 가능
- 트리는 부모 노드와 자식 노드의 관계로 표현됨
- 트리의 최상단 노드를 루트 노드라고 함
- 트리의 최하단 노드를 단말 노드라고 함
- 트리에서 일부를 떼어내도 트리 구조이며 이를 서브 트리라고 함
- 트리는 파일 시스템과 같이 계층적이고 정렬된 데이터를 다루기에 적합함

### 이진 탐색 트리

이진탐색 트리

- 이진 탐색이 동작할 수 있도록 고안된, 효율적인 탐색이 가능한 자료구조

```markdown
- 부모 노드보다 왼쪽 자식 노드가 작다.
- 부모 노드보다 오른쪽 자식 노드가 크다.
```

- 왼쪽 자식 노드 < 부모 노드 < 오른쪽 자식 노드

**빠르게 입력받기**

- 입력 데이터가 많거나, 탐색 범위가 매우 넓은 편
- input() 함수 사용 시,
    - 동작 속도가 느려서 시간 초과로 오답 판정 가능
    - sys 라이브러리의 readline() 함수

```python
import sys
# 하나의 문자열 데이터 입력받기
input_data = sys.stdin.readline().rstrip()

# 입력받은 문자열 그대로 출력
print(input_data)
```

- sys 라이브러이 이용 시,
    - 한 줄 입력받고 나서 rstrip() 함수 꼭 호출해야함
    - readline()으로 입력하면 입력 후 엔터가 줄 바꿈 기호로 입력됨
    - 이 공백 문자를 제거하려면 rstrip() 함수를 사용해야 함

## 2. 부품 찾기

- 첫째 줄
    - N : 부품
- 둘째 줄
    - 부품 번호 나열
- 셋째 줄
    - M
        - 부품
- 넷째 줄
    - 부품 번호 나열

---

- 매장 내 N개의 부품을 번호를 기준으로 정렬
- M개의 찾고자 하는 부품이 각각 매장에 존재하는지 검사
- 매장의 부품들이 정렬되어 잇기 때문에 이진 탐색 수행 가능

```python
# 이진 탐색 소스코드 구현(반복문)
def binary_search(array, target, start, end):
	while start <= end:
		mid = (start + end); // 2
		# 찾은 경우 중간점 인덱스 반환
		if arr[mid] == target:
			return mid
		# 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
		elif array[mid] > target:
			end = mid - 1
		# 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
		else:
			start = mid + 1
		return None

# N(가게의 부품 개수) 입력
n = int(input())
# 가게에 있는 전체 부품 번호를 공백으로 구분하여 입력
array = list(map(int, input().split())
array.sort() # 이진 탐색을 수행하기 위해 사전에 정렬 수행
# M(손님이 확인 요청한 부품 개수) 입력
m = int(input())
# 손님이 확인 요청한 전체 부품 번호를 공백으로 구분하여 입력
x = list(map(int, input().split()))

# 손님이 확인 요청한 부품 번호를 하나씩 확인
for i in x:
	# 해당 부품이 존재하는지 확인
	result = binary_search(array, i, 0, n - 1)
	if result != None:
		print('yes', end='')
	else:
		print('no', end='') 
```

- 계수의 정렬 개념
- 모든 원소의 번호를 포함할 수 있는 크기의 리스트를 만든 뒤에
    - 리스트의 인덱스에 직접 접근하여
    - 특정한 번호의 부품이 매장에 존재하는지 확인

```python
# N(가게의 부품 개수)을 입력받기
n = int(input())
array = [0] * 1000001

# 가게에 있는 전체 부품 번호를 입력받아서 기록
for i in input().split()
	array[int(i)] = 1

# M(손님이 확인 요청한 부품 개수)을 입력바딕
m = int(input())
# 손님이 확인 요청한 전체 부품 번호를 공백으로 구분하여 입력
x = list(map(int, input().split()))
# 손님이 확인 요청한 부품 번호를 하나씩 확인
for i in x:
	# 해당 부품이 존재하는지 확인
	if array[i] == 1:
		print('yes', end='')
	else: 
		print('no', end='')
```

- 특정한 수가 한 번이라도 등장했는지를 검사하면 되므로
    - 집합 자료형을 이용해서 문제 해결 가능

```python
# N(가게의 부품 개수)을 입력바딕
n = int(input())
# 가게에 있는 전체 부품 번호를 입력받아서 집합(Set) 자료형에 기록
array = set(map(int, input().split()))

# M(손님이 확인 요청한 부품 개수)을 입력받기
m = int(input())
# 손님이 확인 요청한 전체 부품 번호를 공백으로 구분하여 입력
x = list(map(int, input().split())

# 손님이 확인 요청한 부품 번호를 하나씩 확인
for i in x:
	# 해당 부품이 존재하는지 확인
	if i in array:
		print('yes', end='')
	else:n
		print('no', end='')
```

## 3. 떡볶이 떡 만들기

- 첫째 줄
    - N : 떡의 개수
    - M : 떡의 길이
- 둘째 줄
    - 떡의 개별 높이

---

- 전형적인 이진 탐색 문제
- 파라메트릭 서치 유형의 문제

파라메트릭 서치

- 최적화 문제를 결정 문제로 바꾸어 해결하는 기법
- 원하는 조건을 만족하는 가장 알맞은 값을 찾는 문제