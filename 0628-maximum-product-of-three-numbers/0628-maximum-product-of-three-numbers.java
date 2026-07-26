class Solution {
    public int maximumProduct(int[] nums) {
        return mySol2(nums);
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