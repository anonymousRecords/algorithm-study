def solution(citations):
    citations.sort(reverse=True) #내림차순 정렬
    for idx , citation in enumerate(citations):
        if idx >= citation:
            return idx
    return len(citations)

# 아래 풀이로 푼 것을 보고 이해해보려고 적어둔 것임.
def solution(citations):
    citations.sort(reverse=True)
    answer = max(map(min, enumerate(citations, start=1)))
    return answer