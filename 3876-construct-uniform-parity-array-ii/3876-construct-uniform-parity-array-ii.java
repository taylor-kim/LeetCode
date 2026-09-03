class Solution {
    public boolean uniformArray(int[] nums1) {
        return mySol2(nums1);
    }

    public boolean mySol2(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        
        for (int num : nums) min = Math.min(min, num);

        for (int i = 0; i < n; i++) {
            if (nums[i] == min) continue;

            if (nums[i] % 2 == min % 2) continue;

            if (min % 2 == 0) return false;
        }

        return true;
    }

    public boolean mySol(int[] nums1) {
        int n = nums1.length;

        Arrays.sort(nums1);

        int base = nums1[0] % 2 == 0 ? 2 : 1;

        int pSum = base;

        // System.out.println(base);

        for (int i = 1; i < n; i++) {
            int bit = nums1[i] % 2 == 0 ? 2 : 1;

            // System.out.println("i:%d, base:%d, bit:%d, pSum:%d".formatted(i, base, bit, pSum));

            if ((base & bit) != 0) continue;

            if (base == 2) return false;

            // if (pSum == 3) return true;

            // int xor = pSum ^ bit;

            // if ((base & xor) == 0) return false;

            // pSum |= bit;
        }

        return true;
    }
}