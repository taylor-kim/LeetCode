class Solution {
    public boolean canBeValid(String s, String locked) {
        return mySol(s, locked);
    }

    public boolean mySol(String s, String locked) {
        int n = s.length();

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

        // System.out.println(String.format("open:%s, joker:%s", open, joker));

        if (open.size() > joker.size()) {
            return false;
        }

        while (!open.isEmpty()) {
            if (open.peek() > joker.peek()) {
                return false;
            }
            open.pop();
            joker.pop();
        }

        // System.out.println(String.format("open:%s, joker:%s", open, joker));

        return joker.size() % 2 == 0;
    }
}