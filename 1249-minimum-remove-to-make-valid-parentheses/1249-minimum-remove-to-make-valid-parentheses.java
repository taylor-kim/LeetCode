class Solution {
    public String minRemoveToMakeValid(String s) {
        return others(s);
    }

    public String others(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (!stack.isEmpty()) stack.pop();
                else sb.setCharAt(i, '*');
            }
        }

        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }

        return sb.toString().replaceAll("\\*", "");
    }

    public String mySol2_fail(String s) {
        return mySol(s, 0, new Stack());
    }

    public String mySol(String s, int index, Stack<Integer> stack) {
        if (index >= s.length()) return "";

        StringBuilder ans = new StringBuilder();

        while (index < s.length() && (s.charAt(index) != '(' && s.charAt(index) != ')')) {
            ans.append(s.charAt(index++));
        }

        if (index >= s.length()) return ans.toString();

        if (s.charAt(index) == '(') {
            stack.push(index);
            String sub = mySol(s, index + 1, stack);

            String p = "";

            if (stack.isEmpty() || stack.peek() != index) {
                p = "(";
            } else if (!stack.isEmpty()) {
                stack.pop();
            }

            return ans.toString() + p + sub;
            
        } else {
            String p = "";
            if (!stack.isEmpty()) {
                stack.pop();
                p = ")";
            }

            return ans.toString() + p + mySol(s, index + 1, stack);
        }
    }

    public String mySol_fail(String s) {
        Stack<Integer> stack = new Stack();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (!stack.isEmpty() && c == ')') {
                sb.append(s.substring(stack.pop(), i + 1));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}