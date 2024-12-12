class Solution {
    public long pickGifts(int[] gifts, int k) {
        return official_heap(gifts, k);
    }

    public long official_heap(int[] gifts, int k) {
        Queue<Integer> pq = new PriorityQueue();

        for (int num : gifts) pq.add(-num);

        while (k-- > 0) {
            pq.add(-(int)Math.sqrt(-pq.poll()));
        }

        long ans = 0;

        while (!pq.isEmpty()) ans -= pq.poll();

        return ans;
    }

    public long mySol_mle_improved(int[] gifts, int k) {
        int max = 0;

        for (int gift : gifts) max = Math.max(max, gift);

        TreeMap<Integer, Integer> counter = new TreeMap();

        for (int gift : gifts) counter.put(gift, counter.getOrDefault(gift, 0) + 1);

        while (k > 0) {
            int number = counter.lastKey();

            counter.put(number, counter.get(number) - 1);

            int root = (int)Math.sqrt(number);
            counter.put(root, counter.getOrDefault(root, 0) + 1);

            k--;

            if (counter.get(number) == 0) {
                counter.remove(number);
            }
        }

        long ans = 0;

        for (Integer key : counter.keySet()) {
            ans += counter.get(key) * key;
        }

        return ans;
    }

    public long mySol2(int[] gifts, int k) {
        List<Integer> list = new ArrayList();
        
        for (int num : gifts) list.add(num);

        Collections.sort(list);

        while (k-- > 0) {
            int root = (int)Math.sqrt(list.remove(list.size() - 1));
            int index = Collections.binarySearch(list, root);

            if (index < 0) index = -(index + 1);

            list.add(index, root);
        }

        long ans = 0;

        for (int num : list) ans += num;

        return ans;
    }

    public long mySol_mle(int[] gifts, int k) {
        int max = 0;

        for (int gift : gifts) max = Math.max(max, gift);

        int[] count = new int[max + 1];

        for (int gift : gifts) count[gift]++;

        int number = max;

        while (number > 0 && k > 0) {
            while (k > 0 && count[number]-- > 0) {
                int root = (int)Math.sqrt(number);
                count[root]++;
                k--;
            }

            number--;
        }

        long ans = 0;

        for (int i = 1; i <= max; i++) {
            if (count[i] > 0) ans += i * count[i];
        }

        return ans;
    }
}