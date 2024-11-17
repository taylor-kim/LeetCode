class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        return mySol(nums);
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
        Map<Integer, Integer> map = new HashMap();

        List<List<Integer>> ans = new ArrayList();

        for (int i = start; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) == 0) {
                map.put(target - nums[i], 1);
                List<Integer> two = new ArrayList();
                two.add(target - nums[i]);
                two.add(nums[i]);
                ans.add(two);
            }

            map.put(nums[i], 0);
        }

        // System.out.println(String.format("start:%d, target:%d\n", start, target) + ans);

        return ans;
    }
}