class Solution {
    public boolean checkValidString(String s) {
        return mySol(s);
    }

    public boolean mySol(String s) {
        Stack<Integer> stack = new Stack();
        Stack<Integer> stack2 = new Stack();

        int star = 0;
        int close = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!stack.isEmpty() && c == ')') {
                stack.pop();
                continue;
            }

            if (!stack2.isEmpty() && c == ')') {
                stack2.pop();
                continue;
            }

            if (c == '*') stack2.push(i);
            else if (c == '(') stack.push(i);
            else close++;

            if (stack.size() + stack2.size() < close) return false;
        }

        if (close > 0) return false;

        if (stack.size() > stack2.size()) return false;

        while (!stack.isEmpty()) {
            if (stack.pop() > stack2.pop()) return false;
        }

        return true;
    }
}