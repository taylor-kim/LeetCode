class Solution {
    public int scoreOfParentheses(String s) {
        return stackWithGemini(s);
    }

    public int stackWithGemini(String s) {
        int n = s.length();

        Stack<Integer> stack = new Stack();
        stack.push(0);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(0);
            } else {
                int score = stack.pop();
                int prev = stack.pop();

                stack.push(prev + Math.max(score * 2, 1));
            }
        }

        return stack.pop();
    }

    public int topdown(String s) {
        int n = s.length();
        int[] pair = new int[n];

        Stack<Integer> stack = new Stack();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        return topdown(s, 0, n - 1, pair);
    }

    public int topdown(String s, int lo, int hi, int[] pair) {
        if (lo >= hi) return 0;

        if (s.charAt(lo + 1) == ')') {
            return 1 + topdown(s, lo + 2, hi, pair);
        } else {
            int sub = topdown(s, lo + 1, pair[lo] - 1, pair);

            return 2 * sub + topdown(s, pair[lo] + 1, hi, pair);
        }
    }

    public int mySol_hold(String s) {
        int ans = 0;
        Stack<Character> ops = new Stack();
        Stack<Integer> nums = new Stack();

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (s.charAt(i + 1) == '(') {
                    ops.push('*');
                } else {
                    ops.push('+');
                }
            } else {
                char op = ops.pop();

                if (op == '*') {
                    nums.push(nums.pop() * 2);
                } else {
                    nums.push(1);
                    if (i + 1 < n && s.charAt(i + 1) == '(') {
                        ops.push('+');
                    }
                }
            }

            System.out.println(ops + ", " + nums);
        }

        // System.out.println(ops);
        // System.out.println(nums);

        return ans;
    }
}