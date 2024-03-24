class Solution {
    public int findDuplicate(int[] nums) {
        return study_bit(nums);
    }

    public int study_bit(int[] nums) {
        int bitLimit = 0;
        int ans = 0;
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        while (max > 0) {
            max >>= 1;
            bitLimit++;
        }

        for (int bit = 0; bit < bitLimit; bit++) {
            int mask = 1 << bit;
            int countWithoutDup = 0;
            int countNum = 0;

            for (int i = 0; i < nums.length; i++) {
                if ((i & mask) != 0) {
                    countWithoutDup++;
                }

                if ((nums[i] & mask) != 0) {
                    countNum++;
                }
            }

            if (countNum > countWithoutDup) {
                ans |= mask;
            }
        }

        return ans;
    }

    public int try_binarysearch(int[] nums) {
        int lo = 1;
        int hi = nums.length;

        while (lo < hi) {
            int cand = lo + (hi - lo) / 2;

            int count = 0;

            for (int num : nums) {
                if (num <= cand) {
                    count++;
                }
            }

            if (count == cand) {
                lo = cand + 1;
            } else if (count > cand) {
                hi = cand;
            } else if (count < cand) {
                lo = cand + 1;
            }
        }

        return lo;
    }

    public int mySol(int[] nums) {
        int n = nums.length;
        int slow = nums[0];
        int fast = slow;

        // 1 -> 3 -> 2 -> 4 -> 2
        // 1 -> 3 -> 2 <-> 4

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) break;
        }

        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}