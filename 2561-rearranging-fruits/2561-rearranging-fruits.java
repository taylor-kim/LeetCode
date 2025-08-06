class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        return official(basket1, basket2);
    }

    public long official(int[] basket1, int[] basket2) {
        int n = basket1.length;

        Map<Integer, Integer> freq = new HashMap();

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            freq.put(basket1[i], freq.getOrDefault(basket1[i], 0) + 1);
            freq.put(basket2[i], freq.getOrDefault(basket2[i], 0) - 1);

            min = Math.min(min, Math.min(basket1[i], basket2[i]));
        }

        List<Integer> list = new ArrayList();

        for (int cost : freq.keySet()) {
            int count = freq.get(cost);

            if (count % 2 == 1) return -1;

            for (int i = 0; i < Math.abs(count) / 2; i++) {
                list.add(cost);
            }
        }

        Collections.sort(list);

        long ans = 0;

        for (int i = 0; i < list.size() / 2; i++) {
            ans += Math.min(2l * min, list.get(i));
        }

        return ans;
    }

    public long mySol(int[] basket1, int[] basket2) {
        int n = basket1.length;
        int min = Integer.MAX_VALUE;

        Map<Integer, Integer> map1 = new HashMap();
        Map<Integer, Integer> map2 = new HashMap();

        for (int i = 0; i < n; i++) {
            map1.put(basket1[i], map1.getOrDefault(basket1[i], 0) + 1);
            map2.put(basket2[i], map2.getOrDefault(basket2[i], 0) + 1);

            min = Math.min(min, Math.min(basket1[i], basket2[i]));
        }

        for (int key : map1.keySet()) {
            int smaller = Math.min(map1.get(key), map2.getOrDefault(key, 0));

            map1.put(key, map1.get(key) - smaller);
            map2.put(key, map2.getOrDefault(key, 0) - smaller);
        }

        List<int[]> list1 = new ArrayList();
        List<int[]> list2 = new ArrayList();

        for (int key : map1.keySet()) {
            int count = map1.get(key);

            if (count % 2 == 1) return -1;

            if (count != 0) {
                list1.add(new int[] {key, map1.get(key)});
            }
        }

        for (int key : map2.keySet()) {
            int count = map2.get(key);

            if (count % 2 == 1) return -1;

            if (count != 0) {
                list2.add(new int[] {key, map2.get(key)});
            }
        }

        int left1 = 0;
        int left2 = list2.size() - 1;

        long ans = 0;

        // Collections.sort(list1, (a, b) -> {
        //     return a[0] - b[0];
        // });
        // Collections.sort(list2, (a, b) -> {
        //     return a[0] - b[0];
        // });

        while (left1 < list1.size() && left2 >= 0) {
            int[] data1 = list1.get(left1);
            int cost1 = data1[0];
            int count1 = data1[1];

            int[] data2 = list2.get(left2);
            int cost2 = data2[0];
            int count2 = data2[1];

            // System.out.println(String.format("d1:%s, d2:%s", Arrays.toString(data1), Arrays.toString(data2)));
            
            int small = Math.min(count1, count2);

            if (cost1 != cost2) {
                long opt1 = (long)min * small;
                long opt2 = (long)Math.min(cost1, cost2) * small / 2;

                ans += Math.min(opt1, opt2);
            }

            data1[1] -= small;
            data2[1] -= small;

            if (data1[1] == 0) {
                left1++;
            }

            if (data2[1] == 0) {
                left2--;
            }
        }

        return ans;
    }

    private void print(List<int[]> list) {
        StringBuilder sb = new StringBuilder();

        for (int[] data : list) {
            sb.append(data[0]).append(", ");
        }

        System.out.println(sb.toString());
    }
}