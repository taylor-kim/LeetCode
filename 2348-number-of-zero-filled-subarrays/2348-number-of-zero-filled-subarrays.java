class Solution {
    public long zeroFilledSubarray(int[] nums) {
        return others(nums);
    }

    public long others(int[] nums) {
        long ans = 0;
        long count = 0;

        for (int num : nums) {
            count = num == 0 ? count + 1 : 0;

            ans += count;
        }

        return ans;
    }

    public long mySol2(int[] nums) {
        long ans = 0;

        long count = 0;

        for (int num : nums) {
            if (num == 0) {
                count++;
            } else {
                if (count != 0) {
                    ans += count * (1 + count) / 2;
                    count = 0;
                }
            }
        }

        ans += count * (1 + count) / 2;

        return ans;
    }

    public long mySol(int[] nums) {
        long ans = 0;
        int left = 0;
        long sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (left <= right && sum != 0) {
                sum -= nums[left++];
            }

            ans += right - left + 1;
        }

        return ans;
    }
}