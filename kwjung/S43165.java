class Solution {
    int cnt = 0;
    public void dfs(int[] numbers, int target, int index, int result) {
        if (index == numbers.length){ // 마지막 index 까지 왔으면
            if (target == result) cnt++;
            return;
        }

        dfs(numbers, target, index + 1, result + numbers[index]); // 더하기
        dfs(numbers, target, index + 1, result - numbers[index]); // 빼기
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(numbers, target, 0, answer); // 배열, target, 현재 index, 결과값
        return cnt;
    }
}