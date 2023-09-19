import java.util.ArrayList;
import java.util.List;

class Solution {

    public int solution(String name) {
        int answer = 0;
        if (name.equals("A".repeat(name.length()))) return 0;

        // 방문 해야할 위치
        List<Integer> list = new ArrayList<>();

        // 알파벳 교체 비용
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (name.charAt(i) == 'A') continue;
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
            list.add(i);
        }

        // 정방향 시작
        int min = name.length();
        for (int i = 0; i < list.size(); i++) {

            // 뒤로 턴 안하는 경우
            if (i == list.size() - 1) {
                min = Math.min(min, list.get(i));
                continue;
            }

            // 해당 칸까지 이동하는거 + 돌아가는거
            int cnt = list.get(i) * 2 ;

            // 뒤로 한칸 이동 + 뒤로 이동
            cnt += (name.length() - list.get(i + 1));
            min = Math.min(min, cnt);
        }


        // 역방향
        for (int i = list.size() - 1; i >= 0; i--) {
            // 앞으로 턴 안하는 경우
            if (i == 0) {
                min = Math.min(min, name.length() - list.get(i));
                continue;
            }

            // 뒤로 해당 칸까지 이동하는거 + 돌아가는거
            int cnt = (name.length() - list.get(i)) * 2;

            // 앞으로 이동
            cnt += list.get(i - 1);
            min = Math.min(min, cnt);
        }

        return answer + min;
    }
}