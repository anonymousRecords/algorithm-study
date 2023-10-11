import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int max = Arrays.stream(citations).max().orElse(0);
        // 미리 형변환
        List<Integer> boxedCitation = Arrays.stream(citations).boxed().collect(Collectors.toList());

        for (int i = max; i > 0; i--) {
            int h = i;
            long more = boxedCitation.stream().filter(n -> (n >= h)).count();
            long less = boxedCitation.stream().filter(n -> (n < h)).count();
            if (more >= h && less <= h)
                return h;
        }
        return answer;
    }
}

// 훨씬 효율적인 방식
//import java.util.*;
//
//class Solution {
//    public int solution(int[] citations) {
//        Arrays.sort(citations);
//
//        int max = 0;
//        for(int i = citations.length-1; i > -1; i--){
//            int min = Math.min(citations[i], citations.length - i);
//            if(max < min) max = min;
//        }
//
//        return max;
//    }
//}
