class Solution {
    public String decodeString(String s) {
        return try_rec(s);
    }

    public String try_rec(String s) {
        Stack stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isDigit = Character.isDigit(c);

            if (!stack.isEmpty()) {
                Object top = stack.peek();

                if (isDigit) {
                    int num = (int)(c - '0');
                    if (top instanceof Integer) {
                        stack.push((int)stack.pop() * 10 + num);
                    } else {
                        stack.push(num);
                    }
                } else if (c == '[') {
                    stack.push(c);
                } else if (c == ']') {
                    // System.out.println(String.format("first:%s", stack.peek()));
                    String word = (String)stack.pop();

                    // System.out.println(String.format("second:%s", stack.peek()));
                    stack.pop();

                    // System.out.println(String.format("third:%s", stack.peek()));
                    int num = (Integer)stack.pop();

                    if (!stack.isEmpty() && stack.peek() instanceof String) {
                        stack.push(stack.pop() + word.repeat(num));
                    } else {
                        stack.push(word.repeat(num));
                    }
                } else {
                    if (top instanceof String) {
                        stack.push((String)stack.pop() + c);
                    } else {
                        stack.push(String.valueOf(c));
                    }
                }
            } else {
                if (isDigit) {
                    stack.push((int)(c - '0'));
                } else if (c == '[' || c == ']') {
                    stack.push(c);
                } else {
                    stack.push(String.valueOf(c));
                }
            }
        }

        // System.out.println(stack);

        return (String)stack.pop();
    }

    public String topdown(String s, int index) {
        if (index >= s.length()) return "";

        int open = 0;

        char c = s.charAt(index);

        if (Character.isDigit(c)) {
            
        }

        return "";
    }
}