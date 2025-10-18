class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(int[] nums, int k) {
        // 1, 2, 2, 3, 3, 4, 5
        // -1, 0, 1, 2, 3, 
        // -2, -2, -1, -1, 0, 0, 0        
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num , 0) + 1);
        }

        int ans = 0;
        int delta = 0;
        int prev = -k;

        Set<Integer> set = new HashSet();

        for (int num : map.keySet()) {
            if (prev <= num) {
                delta = -Math.min(num - prev - 1, k);
            } else {
                delta = prev - num + 1;
            }
            // if (num - prev - 1 < 0) {
            //     delta = Math.max(num - prev - 1, -k);
            // } else {
            //     delta = Math.min(num - prev - 1, k);
            // }

            // System.out.println(String.format("num:%d, delta:%d", num, delta));

            int count = map.get(num);

            while (count-- > 0 && delta <= k) {
                int change = num + delta;

                // System.out.println(change);

                set.add(change);

                prev = change;
                delta++;
            }
        }

        // System.out.println(set);

        return set.size();
    }
}