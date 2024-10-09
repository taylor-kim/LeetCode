class Solution {
    public int minAddToMakeValid(String s) {
        return mySol(s);
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