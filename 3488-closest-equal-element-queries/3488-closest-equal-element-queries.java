class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        return mySol(nums, queries);
    }

    public List<Integer> mySol(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> indicesMap = new HashMap();

        for (int i = 0; i < n; i++) {
            indicesMap.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i];
            List<Integer> indices = indicesMap.get(nums[index]);

            if (indices == null || indices.size() == 1) {
                ans.add(-1);
            } else {
                int size = indices.size();
                int pos = Collections.binarySearch(indices, index);

                int next = indices.get((pos + 1) % size);
                int prev = indices.get((pos - 1 + size) % size);

                // System.out.println("indices:%s, pos:%d, next:%d, prev:%d".formatted(indices, pos, next, prev));

                int diff1 = Math.abs(index - next);
                int minNext = Math.min(diff1, n - diff1);

                int diff2 = Math.abs(index - prev);
                int minPrev = Math.min(diff2, n - diff2);

                ans.add(Math.min(minNext, minPrev));
            }
        }

        return ans;
    }
}