class Solution {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        return mySol(nums, a, b);
    }

    public int mySol(int[] nums, int a, int b) {
        int ans = 0;
        int mod = (int)1e9 + 7;
        int n = nums.length;
        boolean[] removed = new boolean[n];

        int ltA = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < a) {
                ans = (ans + (i - ltA)) % mod;
                
                if (ltA != i) {
                    removed[i] = true;
                }
                ltA++;
            }

            // System.out.println("i:%d, ans:%d".formatted(i, ans));
        }

        // [9,7,3,5]
        // [3,9,7,5] ans : 2, ltA = 1
        // [0,0,1,0]

        // [9,7,3,5]
        // gtB = 3
        // []

        // System.out.println("");

        int gtB = n - 1;
        int removeCount = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (removed[i]) {
                removeCount++;
                continue;
            }

            while (gtB >= 0 && removed[gtB]) {
                gtB--;
                removeCount--;
            }

            if (nums[i] > b) {
                ans = (ans + (gtB - i - removeCount)) % mod;
                gtB--;
            }

            // System.out.println("i:%d, ans:%d".formatted(i, ans));
        }

        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];

        nums[i] = nums[j];
        nums[j] = temp;
    }
}