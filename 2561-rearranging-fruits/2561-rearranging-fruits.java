class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        return mySol_fail(basket1, basket2);
    }

    public long mySol_fail(int[] basket1, int[] basket2) {
        Arrays.sort(basket1);
        Arrays.sort(basket2);

        int n = basket1.length;

        Map<Integer, Integer> map1 = new HashMap();
        Map<Integer, Integer> map2 = new HashMap();

        for (int i = 0; i < n; i++) {
            map1.put(basket1[i], map1.getOrDefault(basket1[i], 0) + 1);
            map2.put(basket2[i], map2.getOrDefault(basket2[i], 0) + 1);
        }

        for (int key : map1.keySet()) {
            int min = Math.min(map1.get(key), map2.getOrDefault(key, 0));

            map1.put(key, map1.get(key) - min);
            map2.put(key, map2.getOrDefault(key, 0) - min);
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

        int min = Math.min(basket1[0], basket2[0]);

        int left1 = 0;
        int left2 = list2.size() - 1;

        long ans = 0;

        Collections.sort(list1, (a, b) -> {
            return a[0] - b[0];
        });
        Collections.sort(list2, (a, b) -> {
            return a[0] - b[0];
        });

        while (left1 < list1.size() && left2 >= 0) {
            int[] data1 = list1.get(left1);
            int cost1 = data1[0];
            int count1 = data1[1];

            int[] data2 = list2.get(left2);
            int cost2 = data2[0];
            int count2 = data2[1];
            
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
}