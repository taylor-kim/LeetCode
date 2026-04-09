class Solution {
        public String largestNumber(int[] nums) {
        String s = Arrays.stream(nums).mapToObj(String::valueOf).sorted((v1, v2) ->  (v2+v1).compareTo(v1+v2)).collect(Collectors.joining());
        // return s.charAt(0) == '0' ? "0" : s;
        return s;
    }
}
