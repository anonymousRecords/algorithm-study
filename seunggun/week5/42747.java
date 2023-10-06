import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int[] citations) {
        Integer[] cs = new Integer[citations.length];

        for (int i = 0; i < citations.length; i++) {
            cs[i] = citations[i];
        }

        Arrays.sort(cs, Collections.reverseOrder());

        if(cs[0] == 0) return 0;

        for(int i = cs.length - 1; i >= 0; --i){
            if(i + 1 <= cs[i]){
                return i + 1;
            }
        }
        return 0;
    }
}