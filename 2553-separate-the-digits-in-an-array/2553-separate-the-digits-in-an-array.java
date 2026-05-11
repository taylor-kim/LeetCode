class Solution {
    public int[] separateDigits(int[] nums) {
        return mySol(nums);
    }

    public int[] mySol(int[] nums) {
        List<Integer> list = new ArrayList();

        for (int num : nums) {
            for (char c : String.valueOf(num).toCharArray()) {
                list.add(c - '0');
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}