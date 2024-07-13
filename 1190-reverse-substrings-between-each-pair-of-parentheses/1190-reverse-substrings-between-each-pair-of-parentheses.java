class Solution {
    public String reverseParentheses(String s) {
        return try_third(s);
    }

    public String try_third(String s) {
        StringBuilder ans = new StringBuilder();
        Stack<StringBuilder> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                String reverse = stack.pop().reverse().toString();

                if (stack.size() == 0) {
                    ans.append(reverse);
                } else {
                    stack.peek().append(reverse);
                }
            } else {
                if (stack.size() > 0) {
                    stack.peek().append(c);
                } else {
                    ans.append(c);
                }
            }
        }

        return ans.toString();
    }
}