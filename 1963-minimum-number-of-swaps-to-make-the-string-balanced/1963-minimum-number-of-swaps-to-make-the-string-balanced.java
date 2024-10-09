class Solution {
    public int minSwaps(String s) {
        return official_stack(s);
    }

    public int official_stack(String s) {
        Stack<Character> stack = new Stack();
        int unmatched = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    unmatched++;
                } else {
                    stack.pop();
                }
            }
        }

        return (unmatched + 1) / 2;
    }
}