answer = 0

def solution(numbers, target):
    global answer

    calculation(numbers, target, sum = 0, depth = 0)
    return answer
    
def calculation(numbers, target, sum, depth):
    global answer
    if depth == len(numbers):
        # reach the target answer
        if sum == target:
            answer += 1
        else:
            return 0
    else:
        # sum + numbers[depth]
        calculation(numbers, target, sum + numbers[depth], depth + 1)
        # sum - numbers[depth]
        calculation(numbers, target, sum - numbers[depth], depth + 1)