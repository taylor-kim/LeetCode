class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        return mySol_fail(bottom, allowed);
    }

    public boolean mySol2WithTopics(String bottom, List<String> allowed) {
        return false;
    }

    public boolean mySol_fail(String bottom, List<String> allowed) {
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
}
