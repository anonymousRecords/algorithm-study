def solution(numbers):
    numbers = list(map(str, numbers))
    # 자릿수를 맞춰 비교하기 위해 문자열을 확장시켜줌.
    # 입력되는 숫자의 최대 크기가 1000미만임 (중요!)
    numbers.sort(key=lambda x: x*3, reverse=True)
    return str(int(''.join(numbers)))