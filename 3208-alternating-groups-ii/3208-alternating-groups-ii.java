class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        return mySol2(colors, k);
    }

    public int mySol2(int[] colors, int k) {
        int n = colors.length;

        int left = 0;
        int ans = 0;

        for (int right = 1; left < n && right < 2 * n; right++) {
            int prev = (right - 1) % n;
            int current = right % n;

            if (colors[prev] == colors[current]) {
                left = right;
            } else {
                if (right - left + 1 == k) {
                    left++;
                    ans++;
                }
            }
        }

        return ans;
    }

    public int mySol_fail(int[] colors, int k) {
        int n = colors.length;
        Map<Integer, Integer> map = new HashMap();

        // 0,1,0,1,1,0
        map.put(colors[0], 0);

        int ans = 0;

        Set<String> set = new HashSet();

        for (int i = 1; i < 2 * n; i++) {
            int prev = (i - 1) % n;
            int current = i % n;

            if (colors[prev] == colors[current]) {
                map.clear();
                map.put(colors[current], i);
            } else {
                if (!map.containsKey(colors[current])) {
                    map.put(colors[current], i);
                }

                int left = k % 2 == 0 ? 1 - colors[current] : colors[current];

                if (i - map.getOrDefault(left, 0) + 1 >= k) {
                    if (!set.add(left + "_" + i)) {
                        break;
                    }
                    System.out.println(String.format("i:%d, left:%d, map:%s", i, left, map));
                    ans++;
                }
            }
        }

        System.out.println(set);

        return set.size();
    }
}