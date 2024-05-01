class Solution {
    public String reversePrefix(String word, char ch) {
        return official_two_pointer_swapping(word, ch);
    }

    public String official_two_pointer_swapping(String word, char ch) {
        int left = 0;
        char[] arr = word.toCharArray();
        
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == ch) {
                while (left <= right) {
                    char temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                    left++;
                    right--;
                }

                return new String(arr);
            }
        }

        return word;
    }

    public String official_find_index_and_fill_result(String word, char ch) {
        int chIndex = word.indexOf(ch);

        if (chIndex == -1) return word;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (i <= chIndex) {
                sb.append(word.charAt(chIndex - i));
            } else {
                sb.append(word.charAt(i));
            }
        }

        return sb.toString();
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