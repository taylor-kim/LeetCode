class FindSumPairs {
    private int[] nums1;
    private int[] nums2;

    private Map<Integer, Integer> map1;
    private Map<Integer, Integer> map2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;

        map1 = createMap(nums1);
        map2 = createMap(nums2);
    }

    private Map<Integer, Integer> createMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map;
    }
    
    public void add(int index, int val) {
        int origin = nums2[index];
        nums2[index] += val;

        map2.put(origin, map2.get(origin) - 1);

        if (map2.get(origin) == 0) {
            map2.remove(origin);
        }

        map2.put(nums2[index], map2.getOrDefault(nums2[index], 0) + 1);
    }
    
    public int count(int tot) {
        int ret = 0;

        for (int a : map1.keySet()) {
            int countA = map1.get(a);
            if (map2.containsKey(tot - a)) {
                int countB = map2.get(tot - a);

                ret += countA * countB;
            }
        }

        return ret;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */