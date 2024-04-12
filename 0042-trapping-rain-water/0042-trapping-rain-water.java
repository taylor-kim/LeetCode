class Solution {
    public int trap(int[] heights) {
        return trap_my_solution(heights);
    }

    private int doWithDp(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int ans = 0;

        int[] leftDp = new int[heights.length];
        int[] rightDp = new int[heights.length];

        leftDp[0] = heights[0];
        rightDp[heights.length - 1] = heights[heights.length - 1];

        for (int i = 1; i < heights.length; i++) {
            leftDp[i] = Math.max(heights[i], leftDp[i - 1]);
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            rightDp[i] = Math.max(heights[i], rightDp[i + 1]);
        }

        for (int i = 0; i < heights.length; i++) {
            ans += Math.min(leftDp[i], rightDp[i]) - heights[i];
        }

        return ans;
    }
    
    private int doWithStack(int[] heights) {
        int ans = 0;

        Stack<Integer> stack = new Stack();

        for (int current = 0; current < heights.length; current++) {
            while (!stack.isEmpty() && heights[current] > heights[stack.peek()]) {
                int mid = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                int left = stack.peek();

                int distance = current - left - 1;
                int height = Math.min(heights[current], heights[left]) - heights[mid];

                ans += distance * height;
            }

            stack.push(current);
        }

        return ans;
    }
    
    private int trap_my_solution(int[] heights) {
        int left = 0;
        int right = heights.length - 1;

        int latestMaxHeight = 0;

        int result = 0;

        while (left < right) {
            int leftHeight = heights[left];
            int rightHeight = heights[right];

            if (leftHeight == 0) {
                left++;
                continue;
            }

            if (rightHeight == 0) {
                right--;
                continue;
            }

            int width = right - left - 1;

            int height = Math.min(leftHeight, rightHeight);
            result += width * height;

            if (latestMaxHeight > 0) {
                if (latestMaxHeight < height) {
                    result -= (right - left) * latestMaxHeight;
                } else {
                    result -= (right - left) * height;
                }
            }

            latestMaxHeight = Math.max(latestMaxHeight, height);

            if (leftHeight < rightHeight) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }
}