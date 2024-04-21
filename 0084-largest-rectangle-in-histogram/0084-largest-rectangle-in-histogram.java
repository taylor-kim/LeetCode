class Solution {
    public int largestRectangleArea(int[] heights) {
        return mySol(heights);
    }

    public int mySol(int[] heights) {
        Stack<Integer> stack = new Stack();
        int n = heights.length;

        int ans = 0;

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[stack.peek()] > heights[i])) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;

                ans = Math.max(ans, h * w);
            }

            stack.push(i);
        }

        return ans;
    }
}