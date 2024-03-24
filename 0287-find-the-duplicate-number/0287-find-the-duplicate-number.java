class Solution {
    public int findDuplicate(int[] nums) {
        return try_bit(nums);
    }

    public int try_bit(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int bitLimit = 0;

        while (max > 0) {
            max >>= 1;
            bitLimit++;
        }

        int ans = 0;

        for (int bit = 0; bit < bitLimit; bit++) {
            int mask = 1 << bit;
            int countOfDistinct = 0;
            int countOfNums = 0;

            for (int i = 0; i <= nums.length - 1; i++) {
                if ((i & mask) > 0) {
                    countOfDistinct++;
                }

                if ((nums[i] & mask) > 0) {
                    countOfNums++;
                }
            }

            if (countOfNums > countOfDistinct) {
                ans |= mask;
            }
        }

        return ans;
    }

    public int officialBit(int[] nums) {
        int n = nums.length - 1;
        int ans = 0;

        int bitLimit = getPositionOfSignificant(getMax(nums));

        for (int bit = 0; bit < bitLimit; bit++) {
            int mask = 1 << bit;
            int countOfDistinct = 0;
            int countOfNums = 0;

            for (int i = 0; i <= n; i++) {
                if ((i & mask) > 0) {
                    countOfDistinct++;
                }

                if ((nums[i] & mask) > 0) {
                    countOfNums++;
                }
            }

            if (countOfNums > countOfDistinct) {
                ans |= mask;
            }
        }

        return ans;
    }

    private int getPositionOfSignificant(int num) {
        int pos = 0;

        while (num > 0) {
            num >>= 1;
            pos++;
        }

        return pos;
    }

    private int getMax(int[] nums) {
        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        return max;
    }

    // https://www.youtube.com/watch?v=wjYnzkAhcNk
    public int studyAgain_slow_fast(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public int mySol(int[] nums) {
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);

            if (nums[num] < 0) {
                ans = num;
                break;
            } else {
                nums[num] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        return ans;
    }

    public int officialTortoiseAndHare(int[] nums) {
        int tor = nums[0];
        int hare = nums[0];

        do {
            tor = nums[tor];
            hare = nums[nums[hare]];
        } while (tor != hare);

        tor = nums[0];

        while (tor != hare) {
            tor = nums[tor];
            hare = nums[hare];
        }

        return tor;
    }

    public int officialBinarySearch(int[] nums) {
        int lo = 1;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int cand = lo + (hi - lo) / 2;

            int count = 0;

            for (int num : nums) {
                if (num <= cand) {
                    count++;
                }
            }

            if (count > cand) {
                hi = cand - 1;
            } else {
                lo = cand + 1;
            }
        }

        return lo;
    }
}