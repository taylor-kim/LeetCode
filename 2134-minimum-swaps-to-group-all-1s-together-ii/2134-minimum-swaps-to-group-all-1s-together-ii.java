class Solution {
    public int minSwaps(int[] nums) {
        return mySol_by_hint2_opt(nums);
    }

    public int mySol_by_hint2_opt(int[] nums) {
        int n = nums.length;
        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) k++;
        }

        int countZero = 0;

        for (int i = 0; i < k; i++) if (nums[i] == 0) countZero++;

        int ans = countZero;

        int left = 0;

        for (int right = k; right < nums.length + k - 1; right++) {
            if (nums[right % n] == 0) countZero++;

            while (right - left + 1 > k) {
                if (nums[left++] == 0) countZero--;
            }

            ans = Math.min(ans, countZero);
        }

        return ans;
    }

    public int mySol_by_hint2(int[] nums) {
        int n = nums.length;
        int[] circle = new int[n * 2];
        int k = 0;

        for (int i = 0; i < circle.length; i++) {
            circle[i] = nums[i % n];
            if (i < n && nums[i] == 1) k++;
        }

        int countZero = 0;

        for (int i = 0; i < k; i++) if (circle[i] == 0) countZero++;

        int ans = countZero;

        int left = 0;

        for (int right = k; right < circle.length; right++) {
            if (circle[right] == 0) countZero++;

            while (right - left + 1 > k) {
                if (circle[left++] == 0) countZero--;
            }

            ans = Math.min(ans, countZero);
        }

        return ans;
    }

    public int mySol_by_topics_fail(int[] nums) {
        int n = nums.length;
        int[] circle = new int[n * 2];
        int k = 0;

        for (int i = 0; i < circle.length; i++) {
            circle[i] = nums[i % n];
            if (circle[i] == 1) k++;
        }

        int left = 0;
        
        for (int right = 0; right < circle.length; right++) {
            while (left <= right && circle[left] == 0) {
                left++;
            }
        }

        return 0;
    }

    public int mySol_fail(int[] nums) {
        int n = nums.length;
        int count = 0;
        int left = -1;
        int right = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
                if (left == -1) left = i;
                right = i;
            }
        }

        int length = right - left + 1;
        int length2 = (n - right - 1) + left;

        System.out.println(String.format("count:%d, length:%d, length2:%d", count, length, length2));

        return Math.min(length - count, length2);
    }
}