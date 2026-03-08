class Solution {
    public String findDifferentBinaryString(String[] nums) {
        return try_20260308(nums);
    }

    public String try_20260308(String[] nums) {
        Set<String> set = new HashSet();
        for (String num : nums) set.add(num);

        return topdown(set, "");
    }

    public String topdown(Set<String> nums, String s) {
        if (nums.size() == s.length()) {
            return !nums.contains(s) ? s : null;
        }

        String a = topdown(nums, s + "0");

        if (a != null) return a;

        return topdown(nums, s + "1");
    }

    public String try_20250215(String[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int max = (int)Math.pow(2, n) - 1;

        for (int i = 0; i <= max; i++) {
            String target = Integer.toBinaryString(i);

            if (target.length() < n) {
                target = "0".repeat(n - target.length()) + target;
            }

            if (Arrays.binarySearch(nums, target) < 0) {
                return target;
            }
        }

        return "";
    }
}