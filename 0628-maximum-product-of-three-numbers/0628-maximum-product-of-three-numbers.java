class Solution {
    public int maximumProduct(int[] nums) {
        return others_partial_sort(nums);
    }

    public int others_partial_sort(int[] nums) {
        int n = nums.length;

        // find two minimums
        for (int i = 0; i < 2; i++) {
            int indexOfMin = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[indexOfMin] > nums[j]) {
                    indexOfMin = j;
                }
            }

            swap(nums, indexOfMin, i);
        }

        // System.out.println(Arrays.toString(nums));

        for (int i = n - 1; i >= n - 3; i--) {
            int indexOfMax = i;
            for (int j = 2; j < i; j++) {
                if (nums[indexOfMax] < nums[j]) {
                    indexOfMax = j;
                }
            }

            swap(nums, indexOfMax, i);
        }

        // System.out.println(Arrays.toString(nums));

        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[n - 1] * nums[0] * nums[1]);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;

        nums[i] += nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    public int others_n(int[] nums) {
        int a = -1001, b = a, c = b;
        int x = 1001, y = x;

        for (int num : nums) {
            c = Math.max(c, Math.min(b, num));
            b = Math.max(b, Math.min(a, num));
            a = Math.max(a, num);

            y = Math.min(y, Math.max(x, num));
            x = Math.min(x, num);
        }

        return Math.max(a * b * c, a * x * y);
    }

    public int mySol2(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        //3 positives
        int p3 = nums[n - 1] * nums[n - 2] * nums[n - 3];

        //1 pos, 2 neg
        int p2n1 = nums[n - 1] * nums[0] * nums[1];

        return Math.max(p3, p2n1);
    }

    public int mySol_fail(int[] nums) {
        int[] positives = {-1000, -1000, -1000};
        int[] negatives = {-1000, -1000, -1000};

        for (int num : nums) {
            if (num > 0) {
                if (positives[0] < num) {
                    positives[2] = positives[1];
                    positives[1] = positives[0];
                    positives[0] = num;
                } else if (positives[1] < num) {
                    positives[2] = positives[1];
                    positives[1] = num;
                } else if (positives[2] < num) {
                    positives[2] = num;
                }
            } else {
                if (negatives[0] < num) {
                    negatives[2] = negatives[1];
                    negatives[1] = negatives[0];
                    negatives[0] = num;
                } else if (negatives[1] < num) {
                    negatives[2] = negatives[1];
                    negatives[1] = num;
                } else if (negatives[2] < num) {
                    negatives[2] = num;
                }
            }
        }

        System.out.println(Arrays.toString(positives));
        System.out.println(Arrays.toString(negatives));

        return Math.max(
            positives[0] * positives[1] * positives[2],
            negatives[0] * negatives[1] * negatives[2]
        );
    }
}