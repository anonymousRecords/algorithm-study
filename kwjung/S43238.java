class Solution {
    public long solution(int n, int[] times) {
        long start = 0;
        long end = Long.MAX_VALUE;

        long totalNum = 0;
        long midTime = 0;
        long min = Long.MAX_VALUE;

        // 0 ~ Long Max Value 시간 사이에서 조건 걸어보면서 갱신
        while (start <= end) {
            boolean pass = false;
            totalNum = 0;
            midTime = (start + end) / (long) 2;

            for (int time : times) {
                totalNum += midTime / (long) time;

                // 순회 사이에 비효율적인지 검사
                if (totalNum > n) {
                    end = midTime - 1;
                    min = Math.min(min, midTime);
                    pass = true;
                    break;
                }
            }

            if (pass) {
                continue;
            }

            if (totalNum == n) {
                min = Math.min(min, midTime);
                end = midTime - 1;
                continue;
            }

            start = midTime + 1;
        }

        return min;
    }
}