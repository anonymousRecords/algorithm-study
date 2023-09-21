def solution(n, lost, reserve):

    # 중복 제거를 위한 set()사용
    #set을 사용하면 자동으로 정렬이 됨. 집합의 개념
    # 시간 복잡도 set 사용 시 O(1)
    _lost = set(lost)-set(reserve)
    _reserve = set(reserve)-set(lost)

    #만들어 준 중복을 제거한 _reserve 배열만큼 반복을 해서
    for num in _reserve :
        # 만약 num의 앞에 있는 수가 lost 리스트에 있으면 remove
        # 뒤에 있는 수가 lost 리스트에 있으면 remove 해줌.
        if num-1 in _lost:
            _lost.remove(num-1)
        elif num+1 in _lost:
            _lost.remove(num+1)
    # 전체 학생 수에서 _lost에 있는 값을 빼주면 됨.
    return n-len(_lost)


print(solution(3,[3],[1]))