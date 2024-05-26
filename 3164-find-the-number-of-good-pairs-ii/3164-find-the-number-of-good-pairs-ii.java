class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        return mySol_2(nums1, nums2, k);
    }

    public long mySol_2(int[] nums1, int[] nums2, int k) {
        long ans = 0;

        int start = 0;

        Map<Integer, Integer> map = new TreeMap();

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] % k == 0 && nums1[i] / k > 0) {
                map.put(nums1[i] / k, map.getOrDefault(nums1[i] / k, 0) + 1);
            }
        }

        for (int num : map.keySet()) {
            for (int j = 0; j < nums2.length; j++) {
                if (num % nums2[j] == 0) {
                    ans += map.get(num);
                }
            }
        }

        return ans;
    }

    public long mySol_tle(int[] nums1, int[] nums2, int k) {
        long ans = 0;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int start = 0;

        while (start < nums1.length) {
            if (nums1[start] >= k) break;
            start++;
        }

        List<Integer> target = new ArrayList();

        for (int i = start; i < nums1.length; i++) {
            if (nums1[i] % k == 0 && nums1[i] / k > 0) {
                target.add(nums1[i] / k);
            }
        }

        for (int num : target) {
            for (int j = 0; j < nums2.length; j++) {
                if (num % nums2[j] == 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}