class Solution {
    public int maxRotateFunction(int[] nums) {
        return mySol3(nums);
    }

    public int mySol3(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int i = 0; i < n; i++) {
            total += nums[i];
        }

        int ans = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += i * nums[i];
        }

        ans = sum;

        for (int i = 0; i < n - 1; i++) {
            int deduct = total - nums[i];
            sum = sum - deduct + (nums[i] * (n - 1));

            ans = Math.max(ans, sum);
        }

        return ans;
    }

    public int mySol2(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }

        int a = 0;
        int counter = 0;

        for (int i = minIndex; i < minIndex + n; i++) {
            a += (minIndex % n) * nums[minIndex % n];
            System.out.println(++counter);
        }

        int b = 0;
        counter = 0;

        for (int i = maxIndex; i > maxIndex - n; i--) {
            int index = (i + n) % n;
            b += index * nums[index];
            System.out.println(++counter);
        }

        System.out.println("a:%d, b:%d".formatted(a, b));

        return Math.max(a, b);
    }

    /**
    0 * a + 1 * b + 2 * c + 3 * d
    0 * b + 1 * c + 2 * d + 3 * a
    0 * c + 1 * d + 2 * a + 3 * b
    0 * d + 1 * a + 2 * b + 3 * c
     */

    public int mySol_fail(int[] nums) {
        int n = nums.length;
        int ans = 0;

        int left = 0;
        int sum = 0;
        
        for (int right = 0; right < n + n - 1; right++) {
            System.out.println("left:%d, right:%d".formatted(left, right));
            sum += (right % n) * nums[right % n];

            if (right - left + 1 == n) {
                ans = Math.max(ans, sum);
                sum -= (left % n) * nums[left % n];
                left++;
            }
        }

        return ans;
    }
}