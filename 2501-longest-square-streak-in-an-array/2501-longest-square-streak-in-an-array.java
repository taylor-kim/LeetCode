class Solution {
    public int longestSquareStreak(int[] nums) {
        return official_binarySearch(nums);
    }

    public int official_binarySearch(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> visit = new HashSet();

        int ans = 0;

        for (int num : nums) {
            if (!visit.add(num)) continue;

            int length = 1;

            while ((long)num * num <= 1e5 && Arrays.binarySearch(nums, num * num) >= 0) {
                num = num * num;
                visit.add(num);
                length++;
            }

            ans = Math.max(ans, length);
        }

        return ans > 1 ? ans : -1;
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