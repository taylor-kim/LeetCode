class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        return ref_official(nums, key, k);
    }

    public List<Integer> ref_official(int[] nums, int key, int k) {
        List<Integer> keyIndices = new ArrayList();

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) keyIndices.add(j);
        }

        List<Integer> ans = new ArrayList();

        int last = 0;

        for (int j : keyIndices) {
            last = Math.max(last, j - k);

            int max = Math.min(nums.length, j + k + 1);

            while (last < max) {
                ans.add(last++);
            }
        }

        return ans;
    }

    public List<Integer> mySol(int[] nums, int key, int k) {
        List<Integer> keyIndices = new ArrayList();

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) keyIndices.add(j);
        }

        Set<Integer> ans = new LinkedHashSet();

        for (int j : keyIndices) {
            for (int i = Math.max(0, j - k); i < Math.min(nums.length, j + k + 1); i++) {
                ans.add(i);
            }
        }

        return new ArrayList(ans);
    }
}