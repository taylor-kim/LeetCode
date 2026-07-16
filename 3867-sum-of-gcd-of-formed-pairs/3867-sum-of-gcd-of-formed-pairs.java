class Solution {
    public long gcdSum(int[] nums) {
        return mySol(nums);
    }

    public long mySol(int[] nums) {
        int n = nums.length;
        int max = 0;
        int[] prefixGcd = new int[n];

        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);

            prefixGcd[i] = gcd(nums[i], max);
        }

        Arrays.sort(prefixGcd);

        int left = 0;
        int right = n - 1;

        long sum = 0;

        while (left < right) {
            sum += gcd(prefixGcd[left++], prefixGcd[right--]);
        }

        return sum;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;

        return gcd(b, a % b);
    }
}