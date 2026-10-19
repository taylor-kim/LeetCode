class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        return editorialGreedy(nums1, nums2, k1, k2);
    }

    public long mySol_improved_fail(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }

        int adj = k1 + k2;

        if (sum <= adj) return 0;

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : diff) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // println(map);

        while (adj > 0) {
            int high = map.lastKey();
            int counter = map.get(high);

            Integer lowerKey = map.lowerKey(high);

            if (lowerKey != null && adj >= (high - lowerKey) * counter) {
                map.put(lowerKey, map.get(lowerKey) + counter);
                map.remove(high);

                adj -= (high - lowerKey) * counter;
            } else {
                int q = adj / counter;
                int r = adj % counter;
                int lower = high - q;
                map.put(lower, map.getOrDefault(lower, 0) + counter - r);
                map.put(lower - 1, map.getOrDefault(lower - 1, 0) + r);

                long reduced = q * counter + r;

                if (reduced > 0) {
                    map.put(high, (int)reduced);
                } else {
                    map.remove(high);
                }

                break;
            }
        }

        // println(map);

        long ans = 0;

        List<Integer> desc = map.keySet().stream().collect(Collectors.toList());
        Collections.reverse(desc);

        for (int num : desc) {
            // println(num);
            if (num <= 0) break;
            
            long sq = 1l * num * num;
            ans += sq * map.get(num);
        }

        return ans;
    }

    public long editorialBS(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = k1 + k2;
        int maxDiff = 0;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        int lo = 0;
        int hi = maxDiff + 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long sum = 0;
            for (int d : diff) {
                sum += d > mid ? d - mid : 0;
            }

            if (sum <= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        for (int d : diff) {
            if (d  > lo) {
                k -= d - lo;
            }
        }

        Arrays.sort(diff);
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            long d = Math.min(diff[i], lo);
            if (k > 0 && d > 0) {
                k--;
                d--;
            }

            ans += d * d;
        }

        return ans;
    }

    public long editorialGreedy(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n + 1];
        long sum = 0;
        long k = (long)k1 + k2;

        for (int i = 0; i < n; i++) {
            diff[i + 1] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i + 1];
        }

        if (sum <= k) return 0;

        Arrays.sort(diff);

        for (int i = n - 1; i >= 0; i--) {
            int count = n - i;

            long cost = (long)(diff[i + 1] - diff[i]) * count;

            if (cost > k) {
                long q = k / count;
                long r = k % count;
                long reduced = diff[i + 1] - q;
                long ans = reduced * reduced * (count - r) + (reduced - 1) * (reduced - 1) * r;

                for (int j = i; j > 0; j--) {
                    ans += (long)diff[j] * diff[j];
                }

                return ans;
            }

            k -= cost;
        }

        return 0;
    }

    public long mySol(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }

        int adj = k1 + k2;

        if (sum <= adj) return 0;

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int num : diff) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // println(map);

        while (adj > 0) {
            int high = map.lastKey();
            int counter = map.get(high);

            int mod = Math.min(adj, counter);
            adj -= mod;

            map.put(high, counter - mod);

            if (map.get(high) == 0) {
                map.remove(high);
            }

            int lower = high - 1;

            map.put(lower, map.getOrDefault(lower, 0) + mod);
        }

        println(map);

        long ans = 0;

        List<Integer> desc = map.keySet().stream().collect(Collectors.toList());
        Collections.reverse(desc);

        for (int num : desc) {
            // println(num);
            if (num <= 0) break;
            
            long sq = 1l * num * num;
            ans += sq * map.get(num);
        }

        return ans;
    }

    private void println(Object o) {
        System.out.println(o);
    }
}