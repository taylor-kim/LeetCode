class Solution {
    public int minMoves(int[] nums) {
        return discussion(nums);
    }
    

    public int discussion(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        while (map.size() > 1) {
            int max = map.lastKey();
            int min = map.firstKey();
            int diff = max - min;

            map.put(max, map.get(max) - 1);
            if (map.get(max) == 0) map.remove(max);

            map.put(max - diff, map.getOrDefault(max - diff, 0) + 1);

            ans += diff;
        }

        return ans;
    }

    public int mySol_tle(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        while (map.size() > 1) {
            TreeMap<Integer, Integer> nextMap = new TreeMap();

            int max = map.lastKey();

            map.put(max, map.getOrDefault(max, 0) - 1);
            if (map.get(max) == 0) {
                map.remove(max);
            }

            int min = map.firstKey();

            int diff = max - min;

            ans += diff;

            for (int num : map.keySet()) {
                int inc = num + diff;
                nextMap.put(inc, nextMap.getOrDefault(inc, 0) + map.get(num));
            }
            nextMap.put(max, nextMap.getOrDefault(max, 0) + 1);

            map = nextMap;
        }

        return ans;
    }
}