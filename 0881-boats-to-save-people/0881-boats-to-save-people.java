class Solution {
    public int numRescueBoats(int[] people, int limit) {
        return mySol(people, limit);
    }

    public int mySol2(int[] people, int limit) {
        int n = people.length;

        int max = 0;

        for (int v : people) {
            max = Math.max(max, v);
        }

        int[] bucket = new int[max + 1];

        for (int v : people) {
            bucket[v]++;
        }

        int w = max;
        int carry = 0;
        int ans = 0;

        while (w > 0 && carry < n) {
            if (bucket[w] > 0) {
                bucket[w]--;
                carry++;
                int complement = Math.min(limit - w, w);

                while (complement > 0 && bucket[complement] == 0) {
                    complement--;
                }

                if (complement > 0) {
                    bucket[complement]--;
                    carry++;
                }

                ans++;
            } else {
                w--;
            }
        }

        return ans;
    }

    public int mySol(int[] people, int limit) {
        Map<Integer, Integer> map = new HashMap();

        int max = 0;

        for (int i = 0; i < people.length; i++) {
            int w = people[i];
            map.put(w, map.getOrDefault(w, 0) + 1);
            max = Math.max(max, w);
        }

        int w = max;

        int ans = 0;

        while (map.size() > 0) {
            if (!map.containsKey(w)) {
                w--;
            } else {
                reduce(map, w);

                int complement = Math.min(limit - w, w);

                while (complement > 0 && !map.containsKey(complement)) {
                    complement--;
                }

                reduce(map, complement);

                ans++;
            }
        }

        return ans;
    }

    private void reduce(Map<Integer, Integer> map, int key) {
        if (key > 0 && map.containsKey(key)) {
            map.put(key, map.get(key) - 1);

            if (map.get(key) == 0) {
                map.remove(key);
            }
        }
    }
}