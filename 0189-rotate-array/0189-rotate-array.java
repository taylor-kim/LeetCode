class Solution {
    public void rotate(int[] nums, int k) {
        mySol(nums, k);
    }

    public void mySol(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int count = 0;
        int startIndex = 0;
        int index = 0;
        int value = nums[index];

        while (count < n) {
            count++;

            int nextIndex = (index + k) % n;
            int popped = nums[nextIndex];
            nums[nextIndex] = value;

            if (nextIndex == startIndex) {
                startIndex = (startIndex + 1) % n;
                index = startIndex;
                value = nums[index];
            } else {
                index = nextIndex;
                value = popped;
            }
        }
    }
}