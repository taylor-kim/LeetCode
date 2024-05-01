class Solution {
    public String reversePrefix(String word, char ch) {
        return mySol_stack(word, ch);
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

    public String mySol_stack(String word, char ch) {
        Stack<Character> stack = new Stack();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));

            if (sb.length() == 0 && stack.peek() == ch) {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }

                sb.append(word.substring(i + 1, word.length()));

                return sb.toString();
            }
        }

        return word;
    }
}