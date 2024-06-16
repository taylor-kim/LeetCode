class Solution {
    public int minPatches(int[] nums, int n) {
        return others(nums, n);
    }

    public int others(int[] nums, int n) {
        long miss = 1;
        int ans = 0;
        int i = 0;

        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                ans++;
            }
        }

        return ans;
    }

    public int mySol_fail(int[] nums, int n) {
        List<Integer> list = new ArrayList();
        Map<Integer, Integer> freq = new HashMap();

        for (int num : nums) {
            list.add(num);
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int index = Collections.binarySearch(list, i);

            if (index >= 0) continue;

            if (canBeFormed(list, 0, i)) continue;

            ans++;
        }

        return ans;
    }

    private boolean canBeFormed(List<Integer> list, int index, int sum) {
        if (index >= list.size() || list.get(index) > sum) return false;

        int num = list.get(index);

        if (sum - num == 0) {
            return true;
        } else if (sum - num >= num) {
            return canBeFormed(list, index + 1, sum - num) || canBeFormed(list, index + 1, sum);
        } else {
            return false;
        }
    }
}