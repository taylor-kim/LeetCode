class Solution {
    public boolean parseBoolExpr(String expression) {
        return official_recur(expression);
    }

    int index = 0;

    public boolean official_recur(String s) {
        char c = s.charAt(index++);

        if (c == 't') return true;
        if (c == 'f') return false;

        if (c == '!') {
            index++; // skip '('
            boolean sub = !official_recur(s);
            index++; // skip ')'
            return sub;
        }

        List<Boolean> values = new ArrayList();
        index++; // skip '('
        while (s.charAt(index) != ')') {
            if (s.charAt(index) != ',') {
                values.add(official_recur(s));
            } else {
                index++; // skip ','
            }
        }
        index++; // skip ')'

        if (c == '&') {
            for (Boolean b : values) {
                if (!b) return false;
            }
            return true;
        }

        if (c == '|') {
            for (Boolean b : values) {
                if (b) return true;
            }
            return false;
        }

        throw new RuntimeException("Shouldn't be reached.");
    }

    public boolean try_recur_fail(String s) {
        return try_recur(s, 0);
    }

    public boolean try_recur(String s, int index) {
        char op = s.charAt(index++);
        index++; // open

        char next = s.charAt(index);

        boolean and = true;
        boolean or = false;

        if (next == '&' || next == '|' || next == '!') {
            boolean sub = try_recur(s, index);

            if (op == '&') {
                and &= sub;
            } else if (op == '|') {
                or |= sub;
            } else {
                and = !sub;
            }
        }

        while (s.charAt(index) != ')') {
            char c = s.charAt(index++);

            if (c == ',') {
                continue;
            }

            boolean b = c == 't';

            and &= b;
            or |= b;
        }

        if (op == '&') {
            return and;
        } else if (op == '|') {
            return or;
        } else {
            return !and;
        }

        // return false;
    }

    public boolean mySol_more_efficient(String s) {
        Stack<Character> stack = new Stack();
        Set<Character> opSet = new HashSet(Arrays.asList('&', '|', '!'));

        for (char c : s.toCharArray()) {
            if (c == ')') {
                boolean and = true;
                boolean or = false;

                while (!opSet.contains(stack.peek())) {
                    boolean b = stack.pop() == 't';

                    and &= b;
                    or |= b;
                }

                char op = stack.pop();

                if (op == '&') {
                    stack.push(and ? 't' : 'f');
                } else if (op == '|') {
                    stack.push(or ? 't' : 'f');
                } else if (op == '!') {
                    stack.push(and ? 'f' : 't');
                }
            } else if (c != ',' && c != '(') {
                stack.push(c);
            }
        }

        // System.out.println(stack);

        return stack.pop() == 't';
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