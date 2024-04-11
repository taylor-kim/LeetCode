class Solution {
    public String removeKdigits(String num, int k) {
        return mySol(num, k);
    }

    public String mySol(String num, int k) {
        Stack<Character> stack = new Stack();

        if (num.length() <= k) return "0";

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                k--;
                stack.pop();
            }

            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }

    public String mySol_topdown_fail(String num, int k) {
        return mySol_topdown(num, 0, k);
    }

    public String mySol_topdown(String num, int index,int k) {
        if (index >= num.length()) return "";

        if (k == 0) return num.substring(index);

        String include = mySol_topdown(num, index + 1, k);
        String exclude = mySol_topdown(num.substring(0, index) + num.substring(index + 1), index, k - 1);

        int a = Integer.parseInt(include);
        int b = Integer.parseInt(exclude);

        return a < b ? include : exclude;
    }
}