class Solution {
    public int minimumRecolors(String blocks, int k) {
        return official_sw(blocks, k);
    }

    public int official_sw(String blocks, int k) {
        int ans = k;

        int left = 0;

        char[] arr = blocks.toCharArray();

        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            count += arr[right] == 'W' ? 0 : 1;

            if (right - left + 1 == k) {
                ans = Math.min(ans, k - count);

                count -= arr[left++] == 'W' ? 0 : 1;
            }
        }

        return ans;
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