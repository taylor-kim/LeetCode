class Solution {
    public String smallestSubsequence(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        int n = s.length();

        TreeMap<Character, Integer> map = new TreeMap();

        for (int i = 0; i < n; i++) {
            map.put(s.charAt(i), i);
        }

        Stack<Character> stack = new Stack();
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (set.contains(c)) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && map.get(stack.peek()) > i) {
                set.remove(stack.pop());
            }

            set.add(c);
            stack.push(c);

            if (stack.size() == map.size()) break;
        }

        // System.out.println(stack);

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}