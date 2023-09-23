import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 프리패스(도난 당한 학생 제외한 모든 학생)
        int answer = n - lost.length;
        
        // 체격 순이기 때문에 가능한 최대 학생 수를 구하기 위해 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        // 여벌을 가져온 학생이 도난당한 경우 체크
        for(int i = 0; i < reserve.length; ++i){
            for(int j = 0; j < lost.length; ++j){
                if(reserve[i] == lost[j]){
                    lost[j] = Integer.MIN_VALUE;
                    reserve[i] = Integer.MIN_VALUE;
                    answer++; // 도난 당해도 여벌 가져온 학생은 수업 들을 수 있음
                    break;
                }
            }   
        }
        
        for(int i = 0; i < lost.length; ++i){
            for(int j = 0; j < reserve.length; ++j){
                // 앞 뒤 비교
                if(lost[i] + 1 == reserve[j] || lost[i] - 1 == reserve[j]){
                    answer++;
                    reserve[j] = Integer.MIN_VALUE; // 여벌 빌려준 학생 제외
                    break;
                }
            }
        }
        return answer;
    }
}