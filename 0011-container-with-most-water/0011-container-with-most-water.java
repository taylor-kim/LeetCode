class Solution {
    public int maxArea(int[] height) {
        return mySol2(height);
    }

    public int mySol2(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;

        int ans = 0;

        while (left < right) {
            ans = Math.max(ans, (right - left) * Math.min(height[left], height[right]));

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }

    public int mySol_fail(int[] height) {
        int ans = 0;
        int heighest = 0;
        int left = 0;

        for (int right = 0; right < height.length; right++) {
            ans = Math.max(ans, (right - left) * Math.min(height[left], height[right]));

            if (heighest < height[right]) {
                heighest = height[right];
                left = right;
            }
        }

        return ans;
    }
}