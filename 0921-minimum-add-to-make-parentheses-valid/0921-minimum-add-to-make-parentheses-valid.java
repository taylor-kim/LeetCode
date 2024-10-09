class Solution {
    public int minAddToMakeValid(String s) {
        return official_nostack(s);
    }

    public int official_nostack(String s) {
        int open = 0;
        int unmatched = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    unmatched++;
                }
            }
        }

        return open + unmatched;
    }

    public int mySol2(String s) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == '(' && c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public int mySol(String s) {
        Stack<Character> stack = new Stack();

        int unmatched = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    unmatched++;
                } else {
                    stack.pop();
                }
            }
        }

        return unmatched + stack.size();
    }
}