class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        return mySol_fail_and_fix_with_gpt(bottom, allowed);
    }

    public boolean mySolWithGodtin(String bottom, List<String> allowed) {
        List<StringBuilder> pyramid = new ArrayList();

        for (int i = 0; i < bottom.length(); i++) {
            pyramid.add(new StringBuilder());
        }

        pyramid.get(0).append(bottom);

        Map<String, List<Character>> map = new HashMap();

        for (String allow : allowed) {
            map.computeIfAbsent(allow.substring(0, 2), k -> new ArrayList()).add(allow.charAt(2));
        }

        return topdown(1, 0, map, pyramid);
    }

    public boolean topdown(int row, int col, Map<String, List<Character>> map, List<StringBuilder> pyramid) {
        if (row == pyramid.size()) return true;

        if (col == pyramid.size() - row) {
            return topdown(row + 1, 0, map, pyramid);
        }

        StringBuilder bottom = pyramid.get(row - 1);

        String prefix = bottom.substring(col, col + 2);

        boolean ans = false;

        for (char next : map.getOrDefault(prefix, new ArrayList<>())) {
            pyramid.get(row).append(next);

            if (topdown(row, col + 1, map, pyramid)) {
                return true;
            }

            pyramid.get(row).deleteCharAt(col);
        }

        return false;
    }

    public boolean mySol_fail_and_fix_with_gpt(String bottom, List<String> allowed) {
        return topdown(bottom, new HashSet(allowed), new HashMap());
    }

    public boolean topdown(String bottom, Set<String> allowed, Map<String, Boolean> memo) {
        if (bottom.length() == 1) return true;

        if (memo.containsKey(bottom)) {
            return memo.get(bottom);
        }

        List<String> nextRows = new ArrayList();

        buildNextRows(bottom, 0, new StringBuilder(), nextRows, allowed);

        for (String nextRow : nextRows) {
            if (topdown(nextRow, allowed, memo)) {
                memo.put(bottom, true);
                return true;
            }
        }

        memo.put(bottom, false);

        return false;
    }

    void buildNextRows(String bottom, int col, StringBuilder nextRow, List<String> nextRows, Set<String> allowed) {
        if (col == bottom.length() - 1) {
            nextRows.add(nextRow.toString());
            return;
        }

        String prefix = bottom.substring(col, col + 2);

        for (int i = 0; i < 6; i++) {
            char next = (char)(i + 'A');

            if (allowed.contains(prefix + next)) {
                nextRow.append(next);

                buildNextRows(bottom, col + 1, nextRow, nextRows, allowed);

                nextRow.deleteCharAt(col);
            }
        }
    }
}
