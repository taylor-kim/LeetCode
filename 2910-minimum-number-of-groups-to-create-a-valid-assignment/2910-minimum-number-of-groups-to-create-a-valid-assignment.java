class Solution {
    public int minGroupsForValidAssignment(int[] balls) {
        return mySol(balls);
    }

    public int mySol(int[] balls) {
        Map<Integer, Integer> freq = new HashMap();

        for (int num : balls) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int min = Integer.MAX_VALUE;

        for (int count : freq.values()) {
            min = Math.min(min, count);
        }

        int limit = min + 1;

        int ans = 0;

        while (min > 0) {
            for (int count : freq.values()) {
                if (min <= count && count <= limit) {
                    ans++;
                    continue;
                }

                int box = count / limit;
                int odd = count % limit;

                if (odd == 0) {
                    ans += box;
                } else if (box >= min - odd) {
                    ans += box + 1;
                } else {
                    ans = 0;
                    break;
                }
            }

            if (ans != 0) return ans;

            min--;
            limit--;
        }

        return -1;
    }
}