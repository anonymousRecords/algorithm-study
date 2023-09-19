def solution(n, lost, reserve):
    #최적의 참여자 수를 구하기 위해서는 정렬을 해주는 것이 맞음.
    reserve.sort()
    lost.sort()

    #만약 여분의 체육복이 있는 학생 리스트와 체육복이 없는 학생 리스트에 중복으로 들어가 있는 수가 있는지 체크.
    _reserve = [r for r in reserve if r not in lost]
    _lost = [l for l in lost if l not in reserve]

    # 여분 체육복을 가지고 있는 친구를 기준으로 만약 앞에 있는 친구가 lost 리스트에 있으면 remove 해줌.
    # 다른 경우로 reserve 기준 뒤에 있는 친구가 lost 리스트에 있을 시 remove 해줌.
    for r in _reserve:
        f = r - 1
        b = r + 1
        if f in _lost:
            _lost.remove(f)
        elif b in _lost:
            _lost.remove(b)
    # 전체 학생 수에서 lost의 길이만 빼주면 결과값 출력
    return n -len(_lost)

print(solution(5,[2,4],[1,3,5]))