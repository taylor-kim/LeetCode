class Solution {
    public int minMirrorPairDistance(int[] nums) {
        return official_prefixMap(nums);
    }

    public int official_prefixMap(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap();
        int ans = n;

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (map.containsKey(num)) {
                ans = Math.min(ans, i - map.get(num));
            }

            map.put(reverse(num), i);
        }

        return ans == n ? -1 : ans;
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        int[] reversed = new int[n];

        Map<Integer, List<Integer>> map = new HashMap();

        for (int i = 0; i < n; i++) {
            reversed[i] = reverse(nums[i]);
            map.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        int ans = n;

        for (int i = 0; i < n; i++) {
            List<Integer> indices = map.get(reversed[i]);

            if (indices == null) continue;

            int pos = Collections.binarySearch(indices, i + 1);

            if (pos < 0) pos = -(pos + 1);

            if (pos >= indices.size()) continue;

            int j = indices.get(pos);

            ans = Math.min(ans, j - i);
        }

        return ans == n ? -1 : ans;
    }

    private int reverse(int num) {
        int ret = 0;

        while (num > 0) {
            ret *= 10;
            ret += num % 10;

            num /= 10;
        }

        return ret;
    }
}