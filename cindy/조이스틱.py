def solution(name):
    answer = 0
    for letter in name:
        answer += min(ord(letter) - ord('A'), ord('Z') - ord(letter) + 1)
    
    move = len(name) - 1
    for idx, letter in enumerate(name):
        next_idx = idx + 1
        while next_idx < len(name) and name[next_idx] == 'A':
            next_idx += 1
        move = min(move, 2 * idx + len(name) - next_idx, idx + 2 * (len(name) - next_idx))
    
    answer += move
    return answer