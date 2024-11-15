class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        return mySol_bf(arr);
    }

    public int mySol_bf(int[] arr) {
        int ans = 0;

        int left = 0;
        int right = left + ans;

        while (!isNonDecreasing(arr, left, right)) {
            left++;
            right = left + ans;

            if (right > arr.length) {
                ans++;
                left = 0;
                right = left + ans;
            }
        }

        // return right - left + 1;

        return ans;
    }

    private boolean isNonDecreasing(int[] arr, int left, int right) {
        int prev = -1;

        for (int i = 0; i < arr.length; i++) {
            if (left <= i && i < right) continue;

            if (prev > arr[i]) return false;

            prev = arr[i];
        }

        // System.out.println(String.format("left:%d, right:%d", left, right));

        return true;
    }
}