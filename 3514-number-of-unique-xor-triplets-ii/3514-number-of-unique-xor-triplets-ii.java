class Solution {
    public int uniqueXorTriplets(int[] nums) {
        return after_editorial(nums);
    }

    public int after_editorial(int[] nums) {
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        int msb = 0;
        
        for (int i = 31; i >= 0; i--) {
            if ((max & (1 << i)) != 0) {
                msb = i;
                break;
            }
        }

        int xorMax = (int)Math.pow(2, msb + 1) - 1;

        boolean[] xorTwo = new boolean[xorMax + 1];
        boolean[] xorThree = new boolean[xorMax + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int xor = nums[i] ^ nums[j];
                xorTwo[xor] = true;
            }
        }

        for (int i = 0; i < xorMax; i++) {
            if (xorTwo[i]) {
                for (int num : nums) {
                    xorThree[i ^ num] = true;
                }
            }
        }

        int ans = 0;

        for (boolean b : xorThree) {
            if (b) ans++;
        }

        return ans;
    }

    public int mySol_tle(int[] nums) {
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        int msb = 0;
        
        for (int i = 31; i >= 0; i--) {
            if ((max & (1 << i)) != 0) {
                msb = i;
                break;
            }
        }

        int xorMax = (int)Math.pow(2, msb + 1) - 1;

        boolean[] visit = new boolean[xorMax + 1];

        topdown(nums, 0, 3, 0, visit);

        int ans = 0;

        for (boolean b : visit) {
            if (b) ans++;
        }

        return ans;
    }

    private void topdown(int[] nums, int index, int count, int xor, boolean[] visit) {
        if (count == 0) {
            visit[xor] = true;
            return;
        }
        if (index >= nums.length) return;

        topdown(nums, index + 1, count, xor, visit);
        topdown(nums, index, count - 1, xor ^ nums[index], visit);
        topdown(nums, index + 1, count - 1, xor ^ nums[index], visit);
    }
}