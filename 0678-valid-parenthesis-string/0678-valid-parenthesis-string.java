class Solution {
    public boolean checkValidString(String s) {
        return mySol(s);
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
            if (opens.peek() > jokers.peek()) {
                return false;
            } else {
                opens.pop();
                jokers.pop();
            }
        }

        return opens.isEmpty();
    }
}