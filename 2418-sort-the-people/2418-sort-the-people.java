class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        return official_tree_map(names, heights);
    }

    public String[] official_tree_map(String[] names, int[] heights) {
        Map<Integer, String> map = new TreeMap();

        for (int i = 0; i < names.length; i++) {
            map.put(heights[i], names[i]);
        }

        String[] ans = new String[names.length];

        int index = names.length - 1;

        for (int h : map.keySet()) {
            ans[index--] = map.get(h);
        }

        return ans;
    }

    public String[] official_map(String[] names, int[] heights) {
        Map<Integer, String> map = new HashMap();

        for (int i = 0; i < names.length; i++) {
            map.put(heights[i], names[i]);
        }

        Arrays.sort(heights);

        String[] ans = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            ans[names.length - i - 1] = map.get(heights[i]);
        }

        return ans;
    }

    public String[] mySol(String[] names, int[] heights) {
        int[][] pairs = new int[names.length][2];

        for (int i = 0; i < names.length; i++) {
            pairs[i][0] = i;
            pairs[i][1] = heights[i];
        }

        Arrays.sort(pairs, (a, b) -> {
            return b[1] - a[1];
        });

        String[] result = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            result[i] = names[pairs[i][0]];
        }

        return result;
    }
}