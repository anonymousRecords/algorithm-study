class Solution {
    public long solution(int n, int[] times) {
        long min = Long.MAX_VALUE;
        for(int i = 0; i< times.length; ++i){
            if(times[i] < min){
                min = times[i];
            }
        }
        long maxTime = min * n; // 심사하는데 최소가 되는 최대 시간

        long start = 0;
        long end = maxTime;

        while(start <= end){
            long mid = (start + end) / 2;
            long sum = 0;
            for(int i = 0; i < times.length; ++i){
                sum += mid / times[i]; // 걸리는 시간과 심사에 걸리는 시간을 나눴을 때의 인원 수 구하기
            }

            if(n <= sum){ // 가능한 인원이 심사를 기다리는 사람보다 크거나 같으면(= 더 적은 시간으로 심사를 받을 수 있음) (같을 때는 end를 줄여야함)
                end = mid - 1; // 가능한 시간을 더 줄이기 위해 end을 줄임
            }
            else{ // 가능한 인원이 기다리는 사람보다 작으면 (= 시간을 더 늘려야 모두 심사 가능)
                start = mid + 1; // 가능한 시간을 더 늘이기 위해 start를 늘림
            }
        }
        return start; // 최종 while문 탈출했을 때의 start 값이 최솟 값이 됨
    }
}