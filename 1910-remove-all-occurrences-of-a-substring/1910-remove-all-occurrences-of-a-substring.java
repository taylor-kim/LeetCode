class Solution {
    public String removeOccurrences(String s, String part) {
        return try_stack2_after_read_editorial(s, part);
    }

    public String try_stack2_after_read_editorial(String s, String part) {
        Stack<Character> stack = new Stack();
        Stack<Character> popped = new Stack();

        for (char c : s.toCharArray()) {
            stack.push(c);
            
            if (stack.size() >= part.length()) {

                boolean matched = true;
                
                for (int i = part.length() - 1; i >= 0; i--) {
                    popped.push(stack.pop());
                    if (popped.peek() != part.charAt(i)) {
                        matched = false;
                        break;
                    }
                }

                if (matched) {
                    popped.clear();
                } else {
                    while (!popped.isEmpty()) {
                        stack.push(popped.pop());
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public String try_stack_fail(String s, String part) {
        Stack<int[]> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stack.isEmpty()) {
                if (c == part.charAt(0)) {
                    stack.push(new int[] {i, 1});
                } else {
                    stack.push(new int[] {i, 0});
                }
            } else {
                int[] prev = stack.peek();

                if (prev[1] == 0 || c != part.charAt(prev[1])) {
                    if (c == part.charAt(0)) {
                        stack.push(new int[] {i, 1});
                    } else {
                        stack.push(new int[] {i, 0});
                    }
                } else if (++prev[1] == part.length()) {
                    System.out.println("matched");
                    stack.pop();
                }
            }

            if (!stack.isEmpty() && stack.peek()[1] == part.length()) {
                System.out.println("matched");
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            int[] data = stack.pop();

            if (data[1] == 0) {
                sb.append(s.charAt(data[0]));
            } else {
                for (int i = data[1] - 1; i >= 0; i--) {
                    sb.append(part.charAt(i));
                }
            }

            System.out.println(sb.toString());
        }

        return sb.reverse().toString();
    }

    public String mySol(String s, String part) {
        int index = -1;

        while ((index = s.indexOf(part)) >= 0) {
            s = s.substring(0, index) + s.substring(index + part.length());

            // System.out.println(String.format("index:%d, s:%s", index, s));
        }

        return s;
    }
}