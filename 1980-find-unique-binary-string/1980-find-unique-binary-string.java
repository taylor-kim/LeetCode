class Solution {
    public String findDifferentBinaryString(String[] nums) {
        return mySol(nums);
    }

    public String mySol(String[] nums) {
        int n = nums.length;
        int max = (int)Math.pow(2, n);

        Set<Integer> set = new HashSet();

        for (String bin : nums) {
            set.add(Integer.parseInt(bin, 2));
        }

        int missing = -1;
        Random random = new Random();

        do {
            missing = random.nextInt(max);
        } while (set.contains(missing));

        String s = Integer.toBinaryString(missing);

        return s.length() == n ? s : "0".repeat(n - s.length()) + s;
    }
}