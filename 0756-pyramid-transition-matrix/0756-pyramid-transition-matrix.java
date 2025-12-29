class Solution {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        return mySol(bottom, allowed);
    }

    public boolean mySol(String bottom, List<String> allowed) {
        Map<String, Boolean> memo = new HashMap<>();
        return dfs(bottom, allowed, memo);
    }

    private boolean dfs(String cur, List<String> allowed, Map<String, Boolean> memo) {
        if (cur.length() == 1) {
            return true;
        }

        if (memo.containsKey(cur)) {
            return memo.get(cur);
        }

        List<String> nextRows = new ArrayList<>();
        buildNextRows(cur, 0, new StringBuilder(), allowed, nextRows);

        for (String next : nextRows) {
            if (dfs(next, allowed, memo)) {
                memo.put(cur, true);
                return true;
            }
        }

        memo.put(cur, false);
        return false;
    }

    private void buildNextRows(
        String cur,
        int idx,
        StringBuilder sb,
        List<String> allowed,
        List<String> nextRows
    ) {
        if (idx == cur.length() - 1) {
            nextRows.add(sb.toString());
            return;
        }

        String prefix = cur.substring(idx, idx + 2);

        for (String a : allowed) {
            if (a.startsWith(prefix)) {
                sb.append(a.charAt(2));
                buildNextRows(cur, idx + 1, sb, allowed, nextRows);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
