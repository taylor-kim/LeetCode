class Solution {
    public int minimumDistance(int[] nums) {
        return mySol(nums);
    }

    public int mySol(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        for (List<Integer> indices : map.values()) {
            for (int i = 0; i < indices.size() - 2; i++) {
                ans = Math.min(ans, indices.get(i + 2) - indices.get(i));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans * 2;
    }
}