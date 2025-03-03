class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        return official_two_pointers(nums, pivot);
    }

    public int[] official_two_pointers(int[] nums, int pivot) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int[] ans = new int[n];

        for (int i = 0, j = n - 1; i < n; i++, j--) {
            if (nums[i] < pivot) {
                ans[left++] = nums[i];
            }

            if (nums[j] > pivot) {
                ans[right--] = nums[j];
            }
        }

        while (left <= right) {
            ans[left++] = pivot;
        }

        return ans;
    }

    public int[] official_twopasses_with_fixed_array(int[] nums, int pivot) {
        int n = nums.length;
        int less = 0;
        int equal = 0;

        for (int num : nums) {
            if (num < pivot) {
                less++;
            } else if (num == pivot) {
                equal++;
            }
        }

        int li = 0;
        int ei = less;
        int gi = less + equal;

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (num < pivot) {
                ans[li++] = num;
            } else if (num == pivot) {
                ans[ei++] = num;
            } else {
                ans[gi++] = num;
            }
        }

        return ans;
    }

    public int[] mySol2(int[] nums, int pivot) {
        int n = nums.length;
        List<Integer> lows = new ArrayList();
        List<Integer> highs = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                lows.add(nums[i]);
            } else if (nums[i] > pivot) {
                highs.add(nums[i]);
            }
        }

        for (int i = 0; i < lows.size(); i++) {
            nums[i] = lows.get(i);
        }

        for (int i = lows.size(); i < n - highs.size(); i++) {
            nums[i] = pivot;
        }
        
        for (int i = n - highs.size(); i < n; i++) {
            nums[i] = highs.get(i - (n - highs.size()));
        }

        return nums;
    }
    
    public int[] mySol_fail(int[] nums, int pivot) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;

        for (int i = 0; i < n && i <= hi; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, lo++);
            } else if (nums[i] > pivot) {
                swap(nums, i--, hi--);
            }
            // System.out.println(Arrays.toString(nums));
        }

        // System.out.println(String.format("lo:%d, hi:%d", lo, hi));
        lo = hi + 1;
        hi = n - 1;

        while (lo < hi) {
            swap(nums, lo++, hi--);
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;

        nums[i] += nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] -= nums[j];
    }
}