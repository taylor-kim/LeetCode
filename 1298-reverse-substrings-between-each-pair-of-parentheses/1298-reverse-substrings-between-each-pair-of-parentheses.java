class Solution {
    public String reverseParentheses(String s) {
        return practice_wormhole(s);
    }

    public String practice_wormhole(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0, dir = 1; i < n; i += dir) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pair[i];
                dir = -dir;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public String bf_20260927(String s) {
        int n = s.length();

        int open = 0;
        Stack<Integer> stack = new Stack();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(ans.length());
            } else if (c == ')') {
                reverse(ans, stack.pop(), ans.length() - 1);
            } else {
                ans.append(c);
            }
        }

        return ans.toString();
    }

    public String try_20260927_fail(String s) {
        int n = s.length();

        int level = 0;
        int count = 0;
        Stack<Data> stack = new Stack();
        stack.push(new Data(level, 0));
        Queue<Data> queue = new LinkedList();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(new Data(++level, i + 1));
                ans.append(c);
            } else if (c == ')') {
                level--;
                Data d = stack.pop();
                d.end = i - 1;
                queue.add(d);
                ans.append(c);
            } else {
                stack.peek().sb.append(c);
                count++;
                ans.append("_");
            }
        }

        if (s.charAt(n - 1) != ')') {
            stack.peek().end = n - 1;
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        while (!queue.isEmpty()) {
            Data d = queue.poll();

            String part = d.getResult();

            if (part.length() == 0) continue;

            int start = d.start;
            int end = d.end;
            int index = 0;

            println("start:%d, end:%d".formatted(start, end));

            for (int i = start; i <= end; i++) {
                if (ans.charAt(i) != '_') continue;

                println("wtf, i:%d".formatted(i));

                ans.setCharAt(i, part.charAt(index++));

                println(ans.toString());
            }
        }

        return ans.toString().replaceAll("\\(|\\)", "");
    }

    private void println(Object o) {
        // System.out.println(o);
    }

    class Data {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        int start = 0;
        int end = 0;

        public Data(int level, int start) {
            this.level = level;
            this.start = start;
        }

        public String getResult() {
            if (level % 2 == 1) {
                return sb.reverse().toString();
            }

            return sb.toString();
        }
    }

    public String official_wormhole_teleportation(String s) {
        int n = s.length();
        int[] pairs = new int[n];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int open = stack.pop();

                pairs[i] = open;
                pairs[open] = i;
            }
        }

        int direction = 1;

        StringBuilder ans = new StringBuilder();

        for (int index = 0; index < n; index += direction) {
            char c = s.charAt(index);
            if (c == '(' || c == ')') {
                index = pairs[index];
                direction = -direction;
            } else {
                ans.append(c);
            }
        }

        return ans.toString();
    }

    public String official_stack(String s) {
        Stack<Integer> stack = new Stack();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(sb.length());
            } else if (c == ')') {
                reverse(sb, stack.pop(), sb.length() - 1);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, temp);
        }
    }

    public String try_third(String s) {
        StringBuilder ans = new StringBuilder();
        Stack<StringBuilder> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                String reverse = stack.pop().reverse().toString();

                if (stack.size() == 0) {
                    ans.append(reverse);
                } else {
                    stack.peek().append(reverse);
                }
            } else {
                if (stack.size() > 0) {
                    stack.peek().append(c);
                } else {
                    ans.append(c);
                }
            }
        }

        return ans.toString();
    }
}