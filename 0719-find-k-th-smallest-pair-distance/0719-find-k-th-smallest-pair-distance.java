class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        return bruteForce2(nums, k);
    }

    public int bruteForce2(int[] nums, int k) {
        int max = Integer.MIN_VALUE;

        for (int num : nums) max = Math.max(max, num);

        int[] bucket = new int[max + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[j] - nums[i]);
                bucket[diff]++;
            }
        }

        int diff = 0;

        while (diff <= max) {
            k -= bucket[diff];

            if (k <= 0) return diff;

            diff++;
        }

        return -1;
    }

    public int mySol4(int[] nums, int k) {
        int n = nums.length;

        int max = k;
        
        Arrays.sort(nums);

        int diff = 1;

        int[] freq = new int[nums[n - 1] + 1];

        for (int num : nums) freq[num]++;

        System.out.println(Arrays.toString(nums));

        while (k > 0) {
            Map<Integer, Integer> map = new TreeMap();
            
            for (int i = 0; i + diff < n; i++) {
                int num = nums[i + diff] - nums[i];
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            if (n < 100 && max < 100) {
                System.out.println(map);
            }

            for (int key : map.keySet()) {
                k -= map.get(key);

                if (n < 100 && max < 100) {
                    System.out.println(key);
                }

                if (k <= 0) return key;
            }

            if (n < 100 && max < 100) {
                System.out.println("\n\n");
            }

            diff++;
        }

        return -1;
    }

    public int mySol3(int[] nums, int k) {
        int n = nums.length;
        
        Arrays.sort(nums);

        int diff = 1;
        int left = 0;
        int right = n - 1;
        int ans = -1;

        System.out.println(Arrays.toString(nums));

        while (k > 0) {
            while (left <= right - diff) {
                int a = nums[left + diff] - nums[left];
                int b = nums[right] - nums[right - diff];
                if (a < b) {
                    left++;
                    ans = a;
                } else {
                    right--;
                    ans = b;
                }
                k--;

                if (n < 100 && k < 100) {
                    System.out.println(ans);
                }

                if (k == 0) return ans;
            }
            left = 0;
            right = n - 1;
            diff++;
        }

        return ans;
    }

    public int mySol2(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        int count = findxTh(n, k);

        int calc = (count * count - count) / 2;

        if (calc == k) {
            return nums[count - 1] - nums[count - 2];
        }

        int left = -1;
        int right = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i + count - 1 < n; i++) {
            if (nums[i + count - 1] - nums[i] < min) {
                min = nums[i + count - 1] - nums[i];
                left = i;
                right = i + count - 1;
            }
        }

        return 0;
    }

    public int bruteForce(int[] nums, int k) {
        Map<Integer, Integer> counters = new TreeMap();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[j] - nums[i]);
                counters.put(diff, counters.getOrDefault(diff, 0) + 1);
            }
        }

        for (int key : counters.keySet()) {
            k -= counters.get(key);

            if (k <= 0) return key;
        }

        return 0;
    }

    public int mySol(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

        int right = findxTh(n, k);

        System.out.println(String.format("right:%d, calc:%d, k:%d", right, (right * right - right) / 2, k));

        int calc = (right * right - right) / 2;

        if (calc == k) {
            return nums[right - 1] - nums[right - 2];
        }

        // int left = right - (k - calc);

        // System.out.println(String.format("left:%d, right:%d", left, right));

        // return nums[right] - nums[left];

        right++;

        List<Integer> list = new ArrayList();

        int diff = 1;

        while (list.size() < right) {
            for (int i = 0; i < nums.length - diff && list.size() < right; i++) {
                list.add(nums[i + diff] - nums[i]);
            }
            diff++;
        }

        // for (int i = 1; i < right; i++) {
        //     list.add(nums[i] - nums[i - 1]);
        // }

        Collections.sort(list);

        System.out.println(list + ", size:" + list.size());

        return list.get((list.size() % k) - 1);
    }

    private int findxTh(int n, int k) {
        int left = 1;
        int right = n + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int count = (mid * mid - mid) / 2;

            System.out.println(String.format("left:%d, mid:%d, right:%d, val:%d", left, mid, right, count));

            if (count <= k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
    }
}