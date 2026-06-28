class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        return mySol(arr);
    }

    public int mySol(int[] arr) {
        Arrays.sort(arr);

        int ans = 1;

        for (int i = 1; i < arr.length; i++) {
            if (ans < arr[i]) {
                ans++;
            }
        }

        return ans;
    }
}