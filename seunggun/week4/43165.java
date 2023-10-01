class Solution {
    private int answer = 0;
    public void dfs(int[] numbers, int current, int target, int result){
        if(current == numbers.length){
            if(target == result)
                answer++;
            return;
        }
        // 덧셈
        dfs(numbers, current + 1, target, result + numbers[current]);
        // 뺄셈
        dfs(numbers, current + 1, target, result - numbers[current]);
    }

    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return answer;
    }
}