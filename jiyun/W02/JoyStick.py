# 다른 분의 코드를 보고 했습니다..

def solution(name):
    # 조이스틱 조작 횟수
    answer = 0

    # 기본 최소 좌 우 이동 횟수는 길이 - 1
    min_move = len(name) - 1


    # Eunerate()는 항목에 대한 자동 카운터/인덱스를 제공
    for i, char in enumerate(name):

        #ord 함수는 문자를 인자로 받고 해당 문자에 대하하는 유니코드 정수 반환
        # 해당 알파벳 변경 최솟값 추가
        answer += min(ord(char) - ord('A'), ord('Z') - ord(char) + 1)

        # 이 부분부터 도저히 이해가 되지 않습니다..
        # 해당 알파벳 다음부터 연속된 A 문자열 찾기
        next = i + 1
        while next < len(name) and name[next] == 'A':
            next += 1


        min_move = min([min_move, 2 * i + len(name) - next, i + 2 * (len(name) - next)])


    answer += min_move
    return answer