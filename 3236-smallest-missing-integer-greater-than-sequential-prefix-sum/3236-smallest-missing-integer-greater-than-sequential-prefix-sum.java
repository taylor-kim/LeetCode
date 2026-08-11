class Solution {
    public int missingInteger(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        int sum = nums[0];
        int totalSum = 0;

        Set<Integer> set = new HashSet();

        for (int num : nums) {
            set.add(num);
            totalSum += num;
        }

        for (int right = 1; right < nums.length; right++) {
            if (nums[right - 1] + 1 != nums[right]) {
                break;
            } else {
                sum += nums[right];
            }
        }

        for (int ans = sum; ans <= totalSum + 1; ans++) {
            if (!set.contains(ans)) {
                return ans;
            }
        }

        return -1;
    }
}