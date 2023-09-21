def solution(name):
    total_moves = 0
    min_horizontal_moves = len(name) - 1

    for i, char in enumerate(name):
        vertical_moves = min(ord(char) - ord('A'), ord('Z') - ord(char) + 1)
        total_moves += vertical_moves

        next_char_index = i + 1
        while next_char_index < len(name) and name[next_char_index] == 'A':
            next_char_index += 1

        min_horizontal_moves = min([min_horizontal_moves, 2 * i + len(name) - next_char_index, i + 2 * (len(name) - next_char_index)])

    total_moves += min_horizontal_moves

    return total_moves