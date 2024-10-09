class Solution {
    public int minSwaps(String s) {
        return official_opt(s);
    }

    public int official_opt(String s) {
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                }
            }
        }

        return (open + 1) / 2;
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

        int unmatched = stack.size() / 2;

        return unmatched / 2 + (unmatched % 2);
    }
}