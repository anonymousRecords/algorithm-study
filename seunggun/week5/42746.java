import java.util.Comparator;
import java.util.Arrays;
import java.math.BigInteger;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];

        for(int i = 0; i < numbers.length; ++i){
            nums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.repeat(4).compareTo(a.repeat(4));
            }
        });

        StringBuilder sb = new StringBuilder();

        for(String n : nums){
            sb.append(n);
        }

        BigInteger big = new BigInteger(sb.toString());

        return big.toString();
    }
}