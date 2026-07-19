class Solution {
    public String smallestSubsequence(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        int n = s.length();

        Map<Character, Integer> map = new HashMap();

        for (int i = 0; i < n; i++) {
            map.put(s.charAt(i), i);
        }

        Stack<Character> stack = new Stack();
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (set.contains(c)) {
                System.out.println(i);
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && map.get(stack.peek()) > i) {
                set.remove(stack.pop());
            }

            set.add(c);
            stack.push(c);
        }

        // System.out.println(stack);

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}