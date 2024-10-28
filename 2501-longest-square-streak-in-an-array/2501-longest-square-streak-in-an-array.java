class Solution {
    public int longestSquareStreak(int[] nums) {
        return mySol2(nums);
    }

    public int mySol2(int[] nums) {
        TreeSet<Integer> set = new TreeSet();

        for (int num : nums) set.add(num);

        int ans = 0;

        while (set.size() > 0) {
            int start = set.first();
            set.remove(start);
            int length = 1;

            while (set.remove(start * start)) {
                length++;
                start = start * start;
            }

            ans = Math.max(ans, length);
        }

        return ans > 1 ? ans : -1;
    }

    public int mySol(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;

        int min = nums[0];
        int max = nums[nums.length - 1];

        int[] bucket = new int[max - min + 1];

        for (int num : nums) bucket[num - min]++;

        int base = min;

        while (base <= max) {
            int length = 0;
            int num = base;

            while (min <= num && num <= max && bucket[num - min] > 0) {
                length++;
                bucket[num - min] = 0;
                
                num = num * num;
            }

            ans = Math.max(ans, length);
            base++;
        }

        return ans > 1 ? ans : -1;
    }
}