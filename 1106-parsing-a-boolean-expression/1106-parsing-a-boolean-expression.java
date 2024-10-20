class Solution {
    public boolean parseBoolExpr(String expression) {
        return mySol(expression);
    }

    public boolean mySol(String s) {
        Stack<Character> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (c == ',') {
                continue;
            } else if (c == ')') {
                boolean and = true;
                boolean or = false;

                while (stack.peek() != '(') {
                    boolean b = stack.pop() == 't';

                    and &= b;
                    or |= b;
                }

                stack.pop();

                char op = stack.pop();

                if (op == '&') {
                    stack.push(and ? 't' : 'f');
                } else if (op == '|') {
                    stack.push(or ? 't' : 'f');
                } else if (op == '!') {
                    stack.push(and ? 'f' : 't');
                }
            } else {
                stack.push(c);
            }
        }

        // System.out.println(stack);

        return stack.pop() == 't';
    }
}