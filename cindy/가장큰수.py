def solution(numbers):
    numbers_str = map(str, numbers)
    
    # sorting with multi conditions
    numbers_sorted = sorted(numbers_str, key = lambda x: x * 4, reverse = True)
    
    # numbers = [0, 0, 0, 0] -> expected = '0', but I got '0000'
    answer = str(int(''.join(numbers_sorted)))
    
    return answer