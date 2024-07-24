class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        return mySol(mapping, nums);
    }

    public int[] mySol(int[] mapping, int[] nums) {
        int n = nums.length;

        Integer[] result = new Integer[n];

        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
        }

        Arrays.sort(result, (a, b) -> {
            int ra = replace(a, mapping);
            int rb = replace(b, mapping);

            return ra - rb;
        });

        for (int i = 0; i < n; i++) {
            nums[i] = result[i];
        }

        return nums;
    }

    private int replace(int num, int[] map) {
        int ret = 0;

        int pos = 1;

        int origin = num;

        do {
            int digit = num % 10;
            num /= 10;

            ret += pos * map[digit];
            pos *= 10;
        } while (num > 0);

        return ret;
    }
}