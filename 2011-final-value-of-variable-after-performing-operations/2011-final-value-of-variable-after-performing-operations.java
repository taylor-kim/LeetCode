class Solution {
    public int finalValueAfterOperations(String[] operations) {
        return mySol(operations);
    }

    public int mySol(String[] operations) {
        int ans = 0;

        for (String op : operations) {
            if (op.startsWith("++") || op.endsWith("++")) {
                ans++;
            } else {
                ans--;
            }
        }

        return ans;
    }
}