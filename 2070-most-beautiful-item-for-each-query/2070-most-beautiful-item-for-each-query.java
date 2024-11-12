class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        return mySol3(items, queries);
    }

    public int[] mySol3(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        TreeMap<Integer, Integer> map = new TreeMap();
        List<Integer> list = new ArrayList();

        for (int[] item : items) {
            int key = item[0];
            if (!map.containsKey(key)) {
                map.put(key, list.size());
                list.add(0);
            }

            int index = map.get(key);

            int value = item[1];

            if (index > 0) {
                int prev = list.get(index - 1);
                list.set(index, Math.max(prev, value));
            } else {
                list.set(index, value);
            }
        }

        // System.out.println(map);
        // System.out.println(list);

        for (int i = 0; i < queries.length; i++) {
            Integer key = map.floorKey(queries[i]);
            queries[i] = key == null ? 0 : list.get(map.get(key));
        }

        return queries;
    }

    public int[] mySol2_mle(int[][] items, int[] queries) {
        int min = (int)1e9;
        int max = 0;
        
        for (int[] item : items) {
            min = Math.min(min, item[0]);
            max = Math.max(max, item[0]);
        }

        int[] bucket = new int[max - min + 1];

        for (int[] item : items) {
            int key = item[0] - min;
            int value = item[1];

            bucket[key] = Math.max(bucket[key], value);
        }

        for (int i = 1; i <= max - min; i++) {
            bucket[i] = Math.max(bucket[i], bucket[i - 1]);
        }

        for (int i = 0; i < queries.length; i++) {
            int key = Math.min(queries[i] - min, max - min);
            queries[i] = key < 0 ? 0 : bucket[key];
        }

        return queries;
    }

    public int[] mySol(int[][] items, int[] queries) {
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int i = 0; i < items.length; i++) {
            int key = items[i][0];
            int b = items[i][1];
            map.put(key, Math.max(map.getOrDefault(key, 0), b));
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