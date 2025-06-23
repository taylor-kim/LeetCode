class Solution {
    public int minimumDeletions(String word, int k) {
        return official(word, k);
    }

    public int official(String word, int k) {
        Map<Integer, Integer> freq = new HashMap();

        for (char c : word.toCharArray()) {
            freq.put(c - 'a', freq.getOrDefault(c - 'a', 0) + 1);
        }

        int ans = Integer.MAX_VALUE;
        
        for (int mid : freq.values()) {
            int removed = 0;

            for (int other : freq.values()) {
                if (mid > other) {
                    removed += other;
                } else if (other > mid + k) {
                    removed += other - (mid + k);
                }
            }

            ans = Math.min(ans, removed);
        }

        return ans;
    }

    public int mySol2_fail(String word, int k) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c -  'a']++;
        }

        Map<Integer, Integer> map = new TreeMap();

        for (int count : freq) {
            if (count == 0) continue;
            map.put(count, map.getOrDefault(count, 0) + 1);
        }

        System.out.println(map);

        List<int[]> list = new ArrayList();

        for (int key : map.keySet()) {
            list.add(new int[] {key, map.get(key)});
        }

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        int mid = list.size() / 2;
        int ans = getMin(list, mid, k);

        if (list.size() % 2 == 0) {
            System.out.println("even!!!");
            ans = Math.min(ans, getMin(list, mid - 1, k));
        }

        return ans;
    }

    private int getMin(List<int[]> list, int mid, int k) {
        System.out.println(String.format("mid:%d", mid));
        // int left = Math.max(mid - 1, 0);
        // int right = Math.min(mid + 1, list.size() - 1);
        int left = 0;
        int right = list.size() - 1;

        int[] md = list.get(mid);

        int ans = 0;

        while (left < mid || mid < right) {
            int removeLeft = 0;
            int removeRight = 0;

            if (left < mid) {
                int[] ld = list.get(left);
                removeLeft = Math.max(md[0] - ld[0] - k, 0) * ld[1];
            }
            if (mid < right) {
                int[] rd = list.get(right);
                removeRight = Math.max(rd[0] - md[0] - k, 0) * rd[1];
            }

            System.out.println(String.format("removeLeft:%d, removeRight:%d", removeLeft, removeRight));

            ans += removeLeft + removeRight;

            left++;
            right--;
        }

        System.out.println(String.format("ans:%d\n", ans));

        return ans;
    }

    public int mySol_fail(String word, int k) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c -  'a']++;
        }

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int count : freq) {
            if (count == 0) continue;
            map.put(count, map.getOrDefault(count, 0) + 1);
        }

        int right = map.lastKey();
        int left = map.firstKey();

        int ans = 0;

        while (right - left > k) {
            int removeRight = (right - left - k) * map.get(right);
            int removeLeft = left * map.get(left);

            if (removeLeft < removeRight) {
                ans += removeLeft;
                left = map.higherKey(left);
            } else {
                ans += removeRight;
                right = map.lowerKey(right);
            }
        }

        return ans;
    }
}