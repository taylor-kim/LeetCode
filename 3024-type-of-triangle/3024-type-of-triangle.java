class Solution {
    public String triangleType(int[] nums) {
        return mySol(nums);
    }

    public String mySol(int[] nums) {
        Set<Integer> set = new HashSet();

        int sum = 0;
        int max = 0;

        for (int num : nums) {
            set.add(num);
            sum += num;
            max = Math.max(max, num);
        }

        if (sum - max <= max) return "none";

        if (set.size() == 1) {
            return "equilateral";
        } else if (set.size() == 2) {
            return "isosceles";
        } else if (set.size() == 3) {
            return "scalene";
        }

        return "";
    }
}