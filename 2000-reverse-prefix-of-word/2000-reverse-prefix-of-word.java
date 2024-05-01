class Solution {
    public String reversePrefix(String word, char ch) {
        return do_simple(word, ch);
    }

    public String do_simple(String word, char ch) {
        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (i < word.length()) {
            if (word.charAt(i++) == ch) {
                sb.append(word.substring(0, i));
                sb.reverse();
                break;
            }
        }

        sb.append(word.substring(i, word.length()));

        return sb.length() == 0 ? word : sb.toString();
    }

    public String mySol_stack_fail(String word, char ch) {
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