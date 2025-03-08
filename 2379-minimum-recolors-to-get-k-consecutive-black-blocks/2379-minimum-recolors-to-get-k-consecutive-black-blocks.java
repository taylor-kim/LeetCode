class Solution {
    public int minimumRecolors(String blocks, int k) {
        return mySol(blocks, k);
    }

    public int mySol(String blocks, int k) {
        int ans = k;

        int left = 0;

        char[] arr = blocks.toCharArray();

        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            count += arr[right] == 'W' ? 0 : 1;

            if (right - left + 1 > k) {
                count -= arr[left++] == 'W' ? 0 : 1;
            }

            if (right - left + 1 == k) {
                ans = Math.min(ans, k - count);
            }
        }

        return ans;
    }
}