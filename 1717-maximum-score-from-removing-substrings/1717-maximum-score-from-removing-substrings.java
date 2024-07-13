class Solution {
    public int maximumGain(String s, int x, int y) {
        return official_stack(s, x, y);
    }

    public int official_stack(String s, int x, int y) {
        int ans = 0;
        String high = x > y ? "ab" : "ba";
        String low = x > y ? "ba" : "ab";

        String afterHigh = removeString(s, high);

        ans += (s.length() - afterHigh.length()) / 2 * Math.max(x, y);

        String afterLow = removeString(afterHigh, low);

        ans += (afterHigh.length() - afterLow.length()) / 2 * Math.min(x, y);

        return ans;
    }

    private String removeString(String s, String match) {
        Stack<Character> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (match.charAt(1) == c && !stack.isEmpty() && stack.peek() == match.charAt(0)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public int try_stack_fail(String s, int x, int y) {
        Stack<Character> stack = new Stack();

        Map<Character, Character> greedy = new HashMap();
        Map<Character, Character> second = new HashMap();

        if (x > y) {
            greedy.put('a', 'b');
            second.put('b', 'a');
        } else {
            greedy.put('b', 'a');
            second.put('a', 'b');
        }

        int ans = 0;

        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'b') {
                if (!stack.isEmpty() && greedy.getOrDefault(stack.peek(), '0') == c) {
                    ans += x > y ? x : y;
                    // System.out.println(String.format("greed : %c%c, %d", stack.peek(), c, ans));
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else if (!stack.isEmpty()) {
                while (stack.size() > 0) {
                    char pop = stack.pop();
                    if (!stack.isEmpty() && second.getOrDefault(stack.peek(), '0') == pop) {
                        ans += x > y ? y : x;
                        // System.out.println(String.format("second : %c%c, %d", stack.peek(), pop, ans));
                        stack.pop();
                    }
                }
            }
        }

        while (stack.size() > 0) {
            char pop = stack.pop();
            if (!stack.isEmpty() && second.getOrDefault(stack.peek(), '0') == pop) {
                ans += x > y ? y : x;
                // System.out.println(String.format("second : %c%c, %d", stack.peek(), pop, ans));
                stack.pop();
            }
        }

        return ans;
    }

    public int bruteForce(String s, int x, int y) {
        if (s.length() < 2) return 0;

        int max = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == 'a' && s.charAt(i + 1) == 'b') {
                max = Math.max(max, x + bruteForce(s.substring(0, i) + s.substring(i + 2), x, y));
            }

            if (s.charAt(i) == 'b' && s.charAt(i + 1) == 'a') {
                max = Math.max(max, y + bruteForce(s.substring(0, i) + s.substring(i + 2), x, y));
            }
        }

        return max;
    }
}