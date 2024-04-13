class Solution {
    public int trap(int[] height) {
        return sol_dp(height);
    }

    public int sol_stack(int[] height) {
        Stack<Integer> stack = new Stack();

        int ans = 0;

        for (int cur = 0; cur < height.length; cur++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[cur]) {
                int mid = stack.pop();

                if (stack.isEmpty()) break;

                int left = stack.peek();

                int distance = cur - left - 1;
                int h = Math.min(height[left], height[cur]) - height[mid];

                ans += distance * h;
            }

            stack.push(cur);
        }

        return ans;
    }

    public int sol_dp(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];

        int lMax = height[0];
        int rMax = height[n - 1];

        for (int i = 1; i < n; i++) {
            lMax = Math.max(lMax, height[i]);
            rMax = Math.max(rMax, height[n - i - 1]);

            left[i] = lMax - height[i];
            right[n - i - 1] = rMax - height[n - i - 1];
        }

        int ans = 0;

        for (int i = 1; i < n - 1; i++) {
            ans += Math.min(left[i], right[i]);
        }

        return ans;
    }
}