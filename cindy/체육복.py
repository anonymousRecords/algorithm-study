def solution(n, lost, reserve):
    # lost -1, reserve +1
    lost_n_reserve = set(lost) & set(reserve)
    lost = set(lost) - lost_n_reserve
    reserve = set(reserve) - lost_n_reserve
    answer = n - len(lost)
    
    for l in lost:
        front = l - 1
        back = l + 1
        if front in reserve:
            reserve.remove(front)
            answer += 1
        elif back in reserve:
            reserve.remove(back)
            answer += 1  
    return answer