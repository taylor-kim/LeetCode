class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        return editorial(nums);
    }

    public int[] editorial(List<Integer> nums) {
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

    public int[] mySol2(List<Integer> nums) {
        int n = nums.size();

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            
            for (int cand = 0; cand < num; cand++) {
                if ((cand | (cand + 1)) == num) {
                    ans[i] = cand;
                    break;
                }
            }
        }

        return ans;
    }

    public int[] mySol_fail(List<Integer> nums) {
        int n = nums.size();

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            int found = 0;

            for (int bit = 0; bit < 32; bit++) {
                int mask = 1 << bit;
                if ((num & mask) != 0) {
                    found |= mask;
                } else {
                    break;
                }
            }

            if (found == 0) {
                ans[i] = -1;
            } else {
                ans[i] = found;
            }
        }

        return ans;
    }
}