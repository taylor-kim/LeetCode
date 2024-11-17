class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return pastSol(nums);
    }

    public List<List<Integer>> pastSol(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList();
        }

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i + 1, -nums[i], ans);
            }
        }

        return ans;
    }

    private void twoSum(int[] nums, int start, int target, List<List<Integer>> ans) {
        int left = start;
        int right = nums.length - 1;

        while (left < right) {
            if (start < left && nums[left - 1] == nums[left]) {
                left++;
                continue;
            }

            if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                right--;
                continue;
            }

            int sum = nums[left] + nums[right];

            if (sum == target) {
                ans.add(Arrays.asList(-target, nums[left++], nums[right--]));
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
    }

    public List<List<Integer>> mySol(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList();

        Set<Integer> visit = new HashSet();

        for (int i = 0; i < nums.length; i++) {
            if (!visit.add(nums[i])) continue;

            List<List<Integer>> twoSum = twoSum(nums, i + 1, -nums[i]);

            for (List<Integer> two : twoSum) {
                two.add(0, nums[i]);
                ans.add(two);
            }
        }

        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        Set<Integer> set = new HashSet();

        Set<List<Integer>> ans = new HashSet();

        for (int i = start; i < nums.length; i++) {
            int complement = target - nums[i];

            // System.out.println(String.format("complement:%d, num:%d, set:%s", complement, nums[i], set));

            if (set.contains(complement)) {
                List<Integer> two = new ArrayList();
                two.add(complement);
                two.add(nums[i]);

                ans.add(two);
            }

            set.add(nums[i]);
        }

        // System.out.println(String.format("start:%d, target:%d, set:%s\n", start, target, set) + ans);

        return new ArrayList(ans);
    }
}