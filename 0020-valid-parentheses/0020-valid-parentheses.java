class Solution {
    public boolean isValid(String s) {
        return mySol(s);
    }

    public boolean mySol(String s) {
        Stack<Character> stack = new Stack();

        Map<Character, Character> pairs = Map.of(
            ')', '(', ']', '[', '}', '{'
        );

        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}