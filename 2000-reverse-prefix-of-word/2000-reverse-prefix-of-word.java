class Solution {
    public String reversePrefix(String word, char ch) {
        return mySol_stack(word, ch);
    }

    public String mySol_stack(String word, char ch) {
        Stack<Character> stack = new Stack();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (sb.length() == 0 && !stack.isEmpty() && c == ch) {
                sb.append(c);
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

                sb.append(word.substring(i + 1, word.length()));

                return sb.toString();
            }

            stack.push(c);
        }

        return word;
    }
}