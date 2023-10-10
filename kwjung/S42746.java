import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

        // 앞뒤로 붙였을 때 뒤앞으로 붙였을 때 비교
        Arrays.sort(nums, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        StringBuilder builder = new StringBuilder();
        Arrays.stream(nums).forEach(builder::append);

        return builder.charAt(0) == '0' ? "0" : builder.toString();
    }
}