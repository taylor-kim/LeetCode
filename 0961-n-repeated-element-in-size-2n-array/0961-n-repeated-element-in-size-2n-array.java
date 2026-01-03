class Solution {
    public int repeatedNTimes(int[] nums) {
        return others_pigeonhole(nums);
    }

    public int others_pigeonhole(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }

    public int others_probability(int[] nums) {
        int n = nums.length;
        Random random = new Random();

        while (true) {
            int i1 = random.nextInt(n);
            int i2 = random.nextInt(n);

            if (i1 != i2 && nums[i1] == nums[i2]) return nums[i1];
        }
    }

    public int try_probability_fail(int[] nums) {
        int n = nums.length;

        Random random = new Random();
        int count = 50;
        int num = -1;
        int matched = 0;

        while (count-- > 0) {
            int cand = nums[random.nextInt(n)];

            if (cand != num) {
                num = cand;
                matched = 0;
            } else if (++matched > 1) {
                return num;
            }
        }

        return -1;
    }

    public int bf(int[] nums) {
        Set<Integer> set = new HashSet();

        for (int num : nums) {
            if (!set.add(num)) return num;
        }

        return -1;
    }

    public int mySol_fail(int[] nums) {
        int ans = -1;
        int count = 1;

        for (int num : nums) {
            if (ans == num) {
                count++;
            } else if (--count == 0) {
                ans = num;
                count = 1;
            }
        }

        return ans;
    }
}