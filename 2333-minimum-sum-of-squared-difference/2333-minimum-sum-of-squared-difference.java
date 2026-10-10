class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        return mySol(nums1, nums2, k1, k2);
    }

    public long mySol(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }

        int adj = k1 + k2;

        if (sum <= adj) return 0;

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : diff) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // println(map);

        while (adj > 0) {
            int high = map.lastKey();
            int counter = map.get(high);

            int mod = Math.min(adj, counter);
            adj -= mod;

            map.put(high, counter - mod);

            if (map.get(high) == 0) {
                map.remove(high);
            }

            int lower = high - 1;

            map.put(lower, map.getOrDefault(lower, 0) + mod);
        }

        println(map);

        long ans = 0;

        List<Integer> desc = map.keySet().stream().collect(Collectors.toList());
        Collections.reverse(desc);

        for (int num : desc) {
            // println(num);
            if (num <= 0) break;
            
            long sq = 1l * num * num;
            ans += sq * map.get(num);
        }

        return ans;
    }

    private void println(Object o) {
        System.out.println(o);
    }
}