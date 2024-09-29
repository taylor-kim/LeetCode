class Solution {
    public String removeDuplicateLetters(String s) {
        return tryAgain_20240929(s);
    }

    public String tryAgain_20240929(String s) {
        int[] last = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            last[c - 'a'] = i;
        }

        Set<Character> set = new HashSet();

        Stack<Integer> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            while (!stack.isEmpty() 
                && s.charAt(stack.peek()) >= s.charAt(i) 
                && last[s.charAt(stack.peek()) - 'a'] > i) {
                    set.remove(s.charAt(stack.peek()));
                    System.out.println(String.format("pop:%c", s.charAt(stack.pop())));
            }

            if (set.add(s.charAt(i))) {
                stack.push(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(s.charAt(stack.pop()));
        }

        return sb.reverse().toString();
    }
}