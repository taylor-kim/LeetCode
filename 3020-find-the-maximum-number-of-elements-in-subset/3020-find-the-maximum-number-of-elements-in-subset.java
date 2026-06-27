class Solution {
    public int maximumLength(int[] nums) {
        return official_without_sort(nums);
    }

    public int official_without_sort(int[] nums) {
        Map<Integer, Integer> map = new HashMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;

        if (map.containsKey(1)) {
            int count = map.remove(1);

            ans = (count & 1) == 1 ? count : count - 1;
        }

        for (int x : map.keySet()) {
            int length = 0;

            while (map.containsKey(x) && map.get(x) >= 1) {
                int count = map.get(x);

                length++;

                if (count == 1) break;

                x = x * x;
            }

            ans = Math.max(ans, (length - 1) * 2 + 1);
        }

        return ans;
    }

    public int mySol(int[] nums) {
        int ans = 1;

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        if (map.containsKey(1)) {
            int count = map.remove(1);
            int pair = (count - 1) / 2;
            ans = (pair) * 2 + 1;
        }

        while (!map.isEmpty()) {
            int length = 0;
            int x = map.firstKey();

            while (map.containsKey(x) && map.get(x) >= 1) {
                int count = map.remove(x);

                length++;

                if (count == 1) break;

                x = x * x;
            }

            ans = Math.max(ans, (length - 1) * 2 + 1);
        }

        return ans;
    }
}