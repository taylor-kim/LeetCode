class Solution {
    public int minLength(String s) {
        return mySol_stack(s);
    }

    public int mySol_stack(String s) {
        Stack<Character> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty()
                 && ((stack.peek() == 'A' && c == 'B')
                 || (stack.peek() == 'C' && c == 'D'))) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public int mySol(String s) {
        if (s.indexOf("AB") >= 0 || s.indexOf("CD") >= 0 ) {
            s = s.replaceAll("AB", "").replaceAll("CD", "");

            return mySol(s);
        }

        return s.length();
    }
}