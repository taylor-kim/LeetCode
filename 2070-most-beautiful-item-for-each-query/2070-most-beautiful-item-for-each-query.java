class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        return mySol(items, queries);
    }

    public int[] mySol(int[][] items, int[] queries) {
        TreeMap<Integer, Integer> map = new TreeMap();

        List<Integer> dp = new ArrayList();
        dp.add(0);

        for (int i = 0; i < items.length; i++) {
            int key = items[i][0];
            int b = items[i][1];
            map.put(key, Math.max(map.getOrDefault(key, 0), b));

            // Integer lower = map.lowerKey(key);

            // if (lower != null && map.get(lower) > b) {
            //     map.put(key, map.get(lower));
            // }
        }

        Integer key = map.firstKey();

        while (map.higherKey(key) != null) {
            int nextKey = map.higherKey(key);

            map.put(nextKey, Math.max(map.get(key), map.get(nextKey)));

            key = nextKey;
        }

        for (int i = 0; i < queries.length; i++) {
            key = map.floorKey(queries[i]);
            queries[i] = key == null ? 0 : map.get(key);
        }

        return queries;
    }
}