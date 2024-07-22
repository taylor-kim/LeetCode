class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        return mySol(names, heights);
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