class Solution {
    public String removeDuplicateLetters(String s) {
        return tryAgain_20240929_faster(s);
    }

    public String tryAgain_20240929_faster(String s) {
        int[] last = new int[26];
        boolean[] seen = new boolean[26];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';

            if (seen[val]) continue;

            while (!stack.isEmpty() && stack.peek() > val && last[stack.peek()] > i) {
                seen[stack.pop()] = false;
            }

            stack.push(val);
            seen[val] = true;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append((char)(stack.pop() + 'a'));
        }

        return sb.reverse().toString();
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
                && last[s.charAt(stack.peek()) - 'a'] > i
                && !set.contains(s.charAt(i))) {
                    // System.out.println(String.format("pop:%c", s.charAt(stack.peek())));
                    set.remove(s.charAt(stack.pop()));
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