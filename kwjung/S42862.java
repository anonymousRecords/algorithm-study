class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 현재 참여 가능한 학생 수: 전체 학생수 - 안가져온 수
        int answer = n - lost.length;

        // 예외처리 귀찮아서 양옆으로 한칸씩 늘려서 만들기
        int students[] = new int[n + 2];

        // 없는 애들은 0으로
        for (int i : lost) {
            students[i]--;
        }

        // 있는 애들은 1로
        for (int i : reserve) {
            // 단 여분이 있는 애들중에 본인 거를 잃어버린애는 본인만 써야함
            if (students[i] != 0) {
                answer++;
            }
            students[i]++;
        }

        // 전체 학생 순회
        for (int i = 1; i < students.length; i++) {
            // 없는 애중에 앞뒤로 여분 있는 애들 체크
            if (students[i] < 0) {
                if(students[i - 1] == 1) {
                    students[i - 1] = 0;
                    answer++;
                    continue;
                }

                if(students[i + 1] == 1) {
                    students[i + 1] = 0;
                    answer++;
                }
            }
        }


        return answer;
    }
}
