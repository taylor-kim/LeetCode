class Solution {
    public int minSwaps(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        Stack<Integer> stack = new Stack();
        int open = 0;
        int close = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!stack.isEmpty() && s.charAt(stack.peek()) == '[' && c == ']') {
                stack.pop();
                open--;
            } else {
                stack.push(i);

                if (c == '[') {
                    open++;
                } else {
                    close++;
                }
            }
        }

        // System.out.println(String.format("open:%d, close:%d", open, close));

        int ans = stack.size() / 2;

        // return ans > 1 ? ans - 1 : ans;

        return (stack.size() / 2 + 1) / 2;
    }
}