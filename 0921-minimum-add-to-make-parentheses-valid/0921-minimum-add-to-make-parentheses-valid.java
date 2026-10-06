class Solution {
    public int minAddToMakeValid(String s) {
        return try_20261006(s);
    }

    public int try_20261006(String s) {
        int open = 0;
        int close = 0;

        int ans = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                open--;
            }

            if (open < 0) {
                ans++;
                open = 0;
            }
        }

        return ans + Math.abs(open);
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