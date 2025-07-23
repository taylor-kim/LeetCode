class Solution {
    public int maximumGain(String s, int x, int y) {
        return tryAgain_20250723(s, x, y);
    }

    public int tryAgain_20250723(String s, int x, int y) {
        // abab
        // bbbaba

        char first = x > y ? 'a' : 'b';
        char second = x > y ? 'b' : 'a';

        Stack<Character> stack = new Stack();

        int ans = 0;
        int hi = Math.max(x, y);
        int lo = Math.min(x, y);

        for (char c : s.toCharArray()) {
            if (c != 'a' && c != 'b') {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == first && c == second) {
                stack.pop();
                ans += hi;
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        for (char c : sb.reverse().toString().toCharArray()) {
            if (c != 'a' && c != 'b') {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == second && c == first) {
                stack.pop();
                ans += lo;
            } else {
                stack.push(c);
            }
        }

        return ans;
    }













    public int tryAgain_20240812(String s, int x, int y) {
        int ans = 0;

        String first = "ab";
        String second = "ba";

        if (x < y) {
            int temp = x;
            x = y;
            y = temp;

            first = "ba";
            second = "ab";
        }

        Stack<Character> stack = new Stack();

        ans += getMaximumScore(s, first, x, stack);

        StringBuilder remain = new StringBuilder();

        while (!stack.isEmpty()) {
            remain.append(stack.pop());
        }

        s = remain.reverse().toString();

        // System.out.println(String.format("ans:%d, s:%s", ans, s));

        ans += getMaximumScore(s, second, y, stack);

        return ans;
    }

    public int getMaximumScore(String s, String target, int score, Stack<Character> stack) {
        char a = target.charAt(0);
        char b = target.charAt(1);

        int ans = 0;

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && c == b && stack.peek() == a) {
                stack.pop();
                ans += score;
            } else {
                stack.push(c);
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