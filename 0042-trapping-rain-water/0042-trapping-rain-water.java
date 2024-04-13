class Solution {
    public int trap(int[] height) {
        return sol_stack(height);
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
}