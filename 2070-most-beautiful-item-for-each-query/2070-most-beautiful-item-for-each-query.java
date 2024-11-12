class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        return try_binarySearch(items, queries);
    }

    public int[] try_binarySearch(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        List<int[]> list = new ArrayList();
        int[] prev = items[0];
        list.add(prev);

        for (int i = 1; i < items.length; i++) {
            int[] item = items[i];

            if (prev[0] != item[0]) {
                item[1] = Math.max(prev[1], item[1]);
                list.add(item);
                prev = item;
            } else {
                prev[1] = Math.max(prev[1], item[1]);
            }
        }

        // for (int[] each : list) {
        //     System.out.print(Arrays.toString(each));
        // }

        // System.out.println("");

        for (int i = 0; i < queries.length; i++) {
            int[] q = {queries[i], 0};
            int index = Collections.binarySearch(list, q, (a, b) -> {
                return a[0] - b[0];
            });

            if (index < 0) {
                index = -(index + 2);
            }

            queries[i] = index < 0 ? 0 : list.get(index)[1];
        }

        return queries;
    }

    private int search(int[][] items, int target) {
        int lo = 1;
        int hi = (int)1e9;

        int ans = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int[] item = items[mid];

            // if ()
        }

        return ans;
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