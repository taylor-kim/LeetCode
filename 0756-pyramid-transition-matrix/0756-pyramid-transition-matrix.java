class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        return mySol(bottom, allowed);
    }

    public boolean mySol(String bottom, List<String> allowed) {
        List<StringBuilder> pyramid = new ArrayList();

        for (int i = 0; i < bottom.length(); i++) {
            pyramid.add(new StringBuilder());
        }

        pyramid.get(0).append(bottom);

        return topdown(1, 0, 0, allowed, pyramid, new Boolean[bottom.length()][bottom.length()][6]);
    }

    public boolean topdown(int row, int col, int digit, List<String> allowed, List<StringBuilder> pyramid, Boolean[][][] memo) {
        if (row == pyramid.size()) return true;

        if (digit == 6) return false;

        if (col == pyramid.size() - row) {
            return topdown(row + 1, 0, 0, allowed, pyramid, memo);
        }

        if (memo[row][col][digit] != null) {
            return memo[row][col][digit];
        }

        StringBuilder bottom = pyramid.get(row - 1);

        String prefix = bottom.substring(col, col + 2);

        boolean ans = false;

        char next = (char)(digit + 'A');

        if (allowed.contains(prefix + next)) {
            pyramid.get(row).append(next);

            ans = topdown(row, col + 1, 0, allowed, pyramid, memo);

            pyramid.get(row).deleteCharAt(col);
        }

        ans |= topdown(row, col, digit + 1, allowed, pyramid, memo);

        return memo[row][col][digit] = ans;
    }
}
