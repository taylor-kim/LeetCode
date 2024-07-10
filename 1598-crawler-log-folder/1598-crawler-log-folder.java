class Solution {
    public int minOperations(String[] logs) {
        return official_stack(logs);
    }

    public int official_stack(String[] logs) {
        Stack<String> stack = new Stack();

        for (String log : logs) {
            if (log.equals("../")) {
                if (stack.size() > 0) stack.pop();
            } else if (log.charAt(0) != '.') stack.push(log);
        }

        return stack.size();
    }

    public int mySol(String[] logs) {
        int ans = 0;

        for (String log : logs) {
            if (log.equals("../")) {
                ans = Math.max(ans - 1, 0);
            } else if (log.charAt(0) != '.') {
                ans++;
            }
        }

        return ans;
    }
}