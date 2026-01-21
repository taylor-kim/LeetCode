class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        return mySol2(nums);
    }

    public int[] mySol2(List<Integer> nums) {
        int n = nums.size();

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            int cand = -1;
            int x = 1;

            while ((num & x) != 0) {
                cand = num - x;
                x <<= 1;
            }

            ans[i] = cand;
        }

        return ans;
    }

    public int[] mySol_tle(List<Integer> nums) {
        int n = nums.size();

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);

            for (int x = 1; x < num; x++) {
                if ((x | (x + 1)) == num) {
                    ans[i] = x;
                    break;
                }
            }
        }

        return ans;
    }
}