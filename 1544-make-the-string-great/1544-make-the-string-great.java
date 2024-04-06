class Solution {
    public String makeGood(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean removed = false;

            int diff = 'a' - 'A';

            if (!stack.isEmpty() && (stack.peek() + diff == (int)c || stack.peek() - diff == (int)c)) {
                stack.pop();
                removed = true;
            }

            if (!removed) {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}