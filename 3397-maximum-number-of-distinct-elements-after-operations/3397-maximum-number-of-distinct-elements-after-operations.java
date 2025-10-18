class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        return official(nums, k);
    }

    public int official(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 0;
        int prev = Integer.MIN_VALUE;

        for (int num : nums) {
            int current = Math.min(Math.max(prev + 1, num - k), num + k);

            if (prev < current) {
                ans++;
            }

            prev = current;
        }

        return ans;
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

            // while (count-- > 0 && delta <= k) {
            //     int change = num + delta;

            //     // System.out.println(change);

            //     // set.add(change);
            //     ans++;

            //     prev = change;
            //     delta++;
            // }

            int changes = Math.min(k - delta + 1, count);

            ans += changes;

            prev = num + delta + changes - 1;
        }

        // System.out.println(set);

        return ans;
    }
}