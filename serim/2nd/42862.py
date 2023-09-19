def solution(n, lost, reserve):
    available_count = 0

    available_reserve = set(reserve) - set(lost)
    available_lost = set(lost) - set(reserve)

    for r in available_reserve:
        if r - 1 in available_lost:
            available_lost.remove(r - 1)
        elif r + 1 in available_lost:
            available_lost.remove(r + 1)

    available_count = n - len(available_lost)

    return available_count