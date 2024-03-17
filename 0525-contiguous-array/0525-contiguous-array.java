class Solution {
    public int findMaxLength(int[] nums) {
        return othersMap(nums);
    }

    public int othersMap(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }
        
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0, max = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // System.out.println(sumToIndex + String.format(", sum:%d", sum));

            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum));
            }
            else {
                sumToIndex.put(sum, i);
            }
        }
        
        return max;
    }

    public int mySol_TLE(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        int[] zero = new int[n + 1];
        int[] one = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            zero[i + 1] += zero[i] + (num == 0 ? 1 : 0);
            one[i + 1] += one[i] + (num == 1 ? 1 : 0);
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (zero[i] - zero[j] == one[i] - one[j]) {
                    ans = Math.max(ans, i - j);
                }
            }
        }

        return ans;
    }
}