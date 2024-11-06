class Solution {
    public boolean canSortArray(int[] nums) {
        return try_onepass(nums);
    }

    public boolean try_onepass(int[] nums) {
        int lastMax = 0;
        int min = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (Integer.bitCount(nums[i - 1]) == Integer.bitCount(nums[i])) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            } else {
                if (lastMax > min) return false;

                lastMax = max;

                min = nums[i];
                max = nums[i];
            }
        }

        return lastMax <= min;
    }

    public boolean mySol3(int[] nums) {
        int lastMax = nums[0];
        int groupMax = nums[0];

        int index = 0;
        List<int[]> minMaxList = new ArrayList();

        while (index < nums.length) {
            int min = nums[index];
            int max = nums[index];

            int start = index + 1;

            while (start < nums.length && Integer.bitCount(nums[start - 1]) == Integer.bitCount(nums[start])) {
                min = Math.min(min, nums[start]);
                max = Math.max(max, nums[start++]);
            }

            minMaxList.add(new int[] {min, max});

            index = start;
        }

        for (int i = 1; i < minMaxList.size(); i++) {
            if (minMaxList.get(i - 1)[1] > minMaxList.get(i)[0]) return false;
        }

        return true;
    }

    public boolean mySol2_fail(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < arr.length; i++) arr[i] = nums[i];

        Arrays.sort(arr, (a, b) -> {
            if (Integer.bitCount(a) == Integer.bitCount(b)) {
                return a - b;
            } else {
                return 0;
            }
        });

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }

        return true;
    }

    public boolean mySol_fail(int[] nums) {
        int min = 0;
        int max = 1024;

        int last = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                last = i - 1;
                continue;
            }
            if (nums[i - 1] > nums[i] && Integer.bitCount(nums[i - 1]) != Integer.bitCount(nums[i])) {
                return false;
            }

            // if ()
        }

        return true;
    }
}