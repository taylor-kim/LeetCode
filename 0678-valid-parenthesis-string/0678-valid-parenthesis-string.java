class Solution {
    public boolean checkValidString(String s) {
        return official_two_pointers(s);
    }

    public boolean official_two_pointers(String s) {
        int n = s.length();
        int open = 0;
        int close = 0;

        for (int i = 0; i < n; i++) {
            open += s.charAt(i) == ')' ? -1 : 1;

            int j = n - i - 1;
            close += s.charAt(j) == '(' ? -1 : 1;

            if (open < 0 || close < 0) return false;
        }

        return true;
    }

    public boolean mySol(String s) {
        Stack<Integer> jokers = new Stack();
        Stack<Integer> opens = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                opens.push(i);
            } else if (c == ')') {
                if (!opens.isEmpty()) {
                    opens.pop();
                } else if (!jokers.isEmpty()) {
                    jokers.pop();
                } else {
                    return false;
                }
            } else {
                jokers.push(i);
            }
        }

        // System.out.println("opens:%s, jokers:%s".formatted(opens, jokers));

        while (!opens.isEmpty() && !jokers.isEmpty()) {
            if (opens.pop() > jokers.pop()) {
                return false;
            }
        }

        return opens.isEmpty();
    }
}