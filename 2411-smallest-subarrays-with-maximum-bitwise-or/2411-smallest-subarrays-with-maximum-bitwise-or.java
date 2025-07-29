class Solution {
    public int[] smallestSubarrays(int[] nums) {
        return mySol3(nums);
    }

    public int[] mySol3(int[] nums) {
        int n = nums.length;
        int[] remainBits = new int[32];
        int[] currentBits = new int[32];

        int totalOr = 0;

        for (int num : nums) {
            calcBits(remainBits, num, 1);
            totalOr |= num;
        }

        int[] ans = new int[n];
        int left = 0;

        for (int right = 0; right < n; right++) {
            int num = nums[right];

            calcBits(currentBits, num, 1);

            while (left <= right && getNumber(currentBits) == getNumber(remainBits)) {
                ans[left] = right - left + 1;
                calcBits(currentBits, nums[left], -1);
                calcBits(remainBits, nums[left], -1);

                left++;
            }
        }

        // while (left < n) {
        //     System.out.println(String.format("left:%d", left++));
        // }

        return ans;
    }

    public int[] mySol2_tle(int[] nums) {
        int n = nums.length;
        int[] bits = new int[32];

        int totalOr = 0;

        for (int num : nums) {
            calcBits(bits, num, 1);
            totalOr |= num;
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int max = getNumber(bits);

            int j = find(nums, i, max);

            // System.out.println(String.format("i:%d, max:%d, j:%d", i, max, j));

            ans[i] = j - i + 1;

            calcBits(bits, nums[i], -1);
        }

        return ans;
    }

    private int find(int[] nums, int start, int target) {
        int lo = start;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int xor = 0;

            for (int i = start; i <= mid; i++) {
                xor |= nums[i];
            }

            if (xor < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    private void calcBits(int[] bits, int num, int delta) {
        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;

            if ((num & bit) != 0) {
                bits[i] += delta;
            }
        }
    }

    public int[] mySol_fail(int[] nums) {
        int n = nums.length;
        int[] bits = new int[32];

        int totalOr = 0;

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                int bit = 1 << i;

                if ((num & bit) != 0) {
                    bits[i]++;
                }
            }
            totalOr |= num;
        }

        System.out.println(Arrays.toString(bits));
        System.out.println(totalOr);

        int[] max = new int[n];

        for (int i = 0; i < n; i++) {
            max[i] = getNumber(bits);

            int num = nums[i];

            for (int j = 0; j < 32; j++) {
                int bit = 1 << j;

                if ((num & bit) != 0) {
                    bits[j]--;
                }
            }
        }

        System.out.println(Arrays.toString(max));

        int[] ans = new int[n];

        return ans;
    }

    private int getNumber(int[] bits) {
        int num = 0;
        
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0) {
                num |= (1 << i);
            }
        }

        return num;
    }
}