class Solution {
    public boolean canBeValid(String s, String locked) {
        return others_onepass(s, locked);
    }

    // https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/submissions/1506022118/
    public boolean others_onepass(String s, String locked) {
        int n = s.length();

        if ((n & 1) == 1) return false;

        int l = 0;
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(' || locked.charAt(i) == '0') {
                r++;
            } else {
                r--;
            }

            if (s.charAt(i) == ')' || locked.charAt(i) == '0') {
                l--;
            } else {
                l++;
            }

            if (r < 0) return false;

            l = Math.max(l, 0);
        }

        return l == 0;
    }

    public boolean try_space_opt(String s, String locked) {
        int n = s.length();

        if (n % 2 == 1) return false;

        int open = 0;
        int joker = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (locked.charAt(i) == '0') {
                joker++;
            } else if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else if (joker > 0) {
                    joker--;
                } else {
                    return false;
                }
            }
        }

        open = 0;
        int close = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            if (locked.charAt(i) == '0') {
                close++;
            } else if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }

            if (open > close) return false;
        }

        return true;
    }

    public boolean official_space_opt(String s, String locked) {
        int n = s.length();

        if (n % 2 == 1) return false;

        int open = 0;
        int joker = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (locked.charAt(i) == '0') {
                joker++;
            } else if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else if (joker > 0) {
                    joker--;
                } else {
                    return false;
                }
            }
        }

        int balance = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            if (locked.charAt(i) == '0') {
                balance--;
                joker--;
            } else if (s.charAt(i) == '(') {
                balance++;
                open--;
            } else {
                balance--;
            }

            if (balance > 0) {
                return false;
            }

            if (joker == 0 && open == 0) {
                break;
            }
        }

        if (open > 0) {
            return false;
        }

        return true;
    }

    public boolean official_stack(String s, String locked) {
        int n = s.length();

        if (n % 2 == 1) return false;

        Stack<Integer> open = new Stack();
        Stack<Integer> joker = new Stack();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (locked.charAt(i) == '0') {
                joker.push(i);
            } else if (c == '(') {
                open.push(i);
            } else {
                if (!open.isEmpty()) {
                    open.pop();
                } else if (!joker.isEmpty()) {
                    joker.pop();
                } else {
                    return false;
                }
            }
        }

        while (!open.isEmpty() && !joker.isEmpty()
                 && open.peek() < joker.peek()) {
            open.pop();
            joker.pop();
        }

        return open.isEmpty();
    }

    public boolean mySol(String s, String locked) {
        int n = s.length();

        if (n % 2 == 1) return false;

        StringBuilder sb = new StringBuilder("-".repeat(n));

        Stack<Integer> open = new Stack();
        Stack<Integer> joker = new Stack();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (locked.charAt(i) == '0') {
                joker.push(i);
            } else if (c == '(') {
                open.push(i);
            } else {
                if (!open.isEmpty()) {
                    sb.setCharAt(open.pop(), '(');
                } else if (!joker.isEmpty()) {
                    sb.setCharAt(joker.pop(), '(');
                } else {
                    return false;
                }

                sb.setCharAt(i, ')');
            }
        }

        System.out.println(String.format("open:%s, joker:%s", open, joker));

        if (open.size() > joker.size()) {
            return false;
        }

        while (!open.isEmpty()) {
            if (open.peek() > joker.peek()) {
                return false;
            }
            sb.setCharAt(open.pop(), '(');
            sb.setCharAt(joker.pop(), ')');
        }

        System.out.println(String.format("open:%s, joker:%s", open, joker));

        while (!joker.isEmpty()) {
            sb.setCharAt(joker.pop(), ')');

            if (!joker.isEmpty()) {
                sb.setCharAt(joker.pop(), '(');
            }
        }

        System.out.println(String.format("answer : %s", sb.toString()));

        return joker.size() % 2 == 0;
    }
}