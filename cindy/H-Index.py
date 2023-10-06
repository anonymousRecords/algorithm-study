def solution(citations):
    n = len(citations)
    citations_sorted = sorted(citations, reverse = True)
    
    for idx in reversed(range(n)):
        # if all citations are 0, should return 0 not null
        # check max citation
        if citations_sorted[0] == 0:
            return 0
        else:
            if citations_sorted[idx] > idx:
                return idx + 1