class Solution {
    public String reverseParentheses(String s) {
        return official_wormhole_teleportation(s);
    }

    public String official_wormhole_teleportation(String s) {
        int n = s.length();
        int[] pairs = new int[n];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int open = stack.pop();

                pairs[i] = open;
                pairs[open] = i;
            }
        }

        int direction = 1;

        StringBuilder ans = new StringBuilder();

        for (int index = 0; index < n; index += direction) {
            char c = s.charAt(index);
            if (c == '(' || c == ')') {
                index = pairs[index];
                direction = -direction;
            } else {
                ans.append(c);
            }
        }

        return ans.toString();
    }

    public String official_stack(String s) {
        Stack<Integer> stack = new Stack();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(sb.length());
            } else if (c == ')') {
                reverse(sb, stack.pop(), sb.length() - 1);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
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