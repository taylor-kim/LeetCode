class Solution {
    public int minOperations(String[] logs) {
        return mySol(logs);
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