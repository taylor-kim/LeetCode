class Solution {
    public int minIncrementForUnique(int[] nums) {
        return retry_counting_sort(nums);
    }

    public int retry_counting_sort(int[] nums) {
        int max = Integer.MIN_VALUE;

        int n = nums.length;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        int[] freq = new int[n + max];

        for (int num : nums) {
            freq[num]++;
        }

        int ans = 0;

        for (int num = 0; num < freq.length; num++) {
            if (freq[num] <= 1) continue;

            int dup = freq[num] - 1;
            ans += dup;
            freq[num + 1] += dup;
            freq[num] = 1;
        }

        return ans;
    }

    public int mySol_sort(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                ans += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }

        return ans;
    }

    public int mySol_counting_fail(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] freq = new int[(int)1e5 + 1];

        for (int num : nums) {
            freq[num - min]++;
        }

        int num = 0;
        int emptySlot = 0;

        while (emptySlot < freq.length && freq[emptySlot] > 0) {
            emptySlot++;
        }

        int ans = 0;

        while (num <= max - min + 1) {
            while (freq[num] > 1) {
                ans ++;
                freq[num]--;
                freq[emptySlot]++;

                while (emptySlot < freq.length && freq[emptySlot] > 0) emptySlot++;
            }

            num++;
        }

        System.out.println(num);

        return ans;
    }
}