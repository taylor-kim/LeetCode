class Solution {
    public int minimizeMax(int[] nums, int p) {
        return byHint(nums, p);
    }

    public int byHint(int[] nums, int p) {
        int n = nums.length;
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[n - 1] - nums[0];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (foundPairs_official(nums, mid, p)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean foundPairs_official(int[] nums, int maxDiff, int p) {
        int i = 0;
        while (i < nums.length - 1 && p > 0) {
            if (nums[i + 1] - nums[i] <= maxDiff) {
                p--;
                i += 2;
            } else {
                i++;
            }
        }

        return p == 0;
    }

    private boolean foundPairs_fail(int[] nums, int maxDiff, int p) {
        if (p == 0) return true;

        if (p == 1) {
            for (int i = 0; i < nums.length - 1; i++) {
                int diff = nums[i + 1] - nums[i];

                if (diff <= maxDiff) return true;
            }

            return false;
        }

        int minimizeMax = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 3; i += 4, p -= 2) {
            int diff1 = nums[i + 1] - nums[i];
            int diff2 = nums[i + 3] - nums[i + 2];

            minimizeMax = Math.min(minimizeMax, Math.max(diff1, diff2));

            diff1 = nums[i + 3] - nums[i];
            diff2 = nums[i + 2] - nums[i + 1];

            minimizeMax = Math.min(minimizeMax, Math.max(diff1, diff2));
        }

        return minimizeMax <= maxDiff;
    }

    public int mySol_after_fail(int[] nums, int p) {
        int n = nums.length;

        Arrays.sort(nums);

        int[] diffArray = new int[n / 2];

        for (int i = 0, j = 0; i < n - 1; i += 2, j++) {
            diffArray[j] = nums[i + 1] -  nums[i];
        }

        Arrays.sort(diffArray);

        return diffArray[p - 1];
    }

    public int mySol_fail(int[] nums, int p) {
        /**
        1,2,3,7,10
        2,1,1,1,1
        */
        Map<Integer, Integer> counter = new HashMap();

        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(nums);

        List<Integer> list = new ArrayList(counter.keySet());

        Collections.sort(list, (a, b) -> {
            int c1 = counter.get(a);
            int c2 = counter.get(b);

            return c1 != c2 ? c2 - c1 : (a - b);
        });

        System.out.println(Arrays.toString(nums));
        System.out.println(list);

        int ans = 0;

        List<Integer> oddList = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);

            p -= counter.get(num) / 2;

            if (p <= 0) return ans;

            if (counter.get(num) % 2 == 1) {
                oddList.add(num);
            }
        }

        Collections.sort(oddList);

        int[] diffArr = new int[oddList.size() - 1];

        for (int i = 0; i < oddList.size() - 1; i++) {
            int diff = oddList.get(i + 1) - oddList.get(i);

            diffArr[i] = diff;
        }

        Arrays.sort(diffArr);

        return diffArr[p - 1];
    }
}