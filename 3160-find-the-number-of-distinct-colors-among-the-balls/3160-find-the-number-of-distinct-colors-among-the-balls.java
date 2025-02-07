class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        return mySol(limit, queries);
    }

    public int[] mySol(int limit, int[][] queries) {
        int[] ans = new int[queries.length];

        Map<Integer, Set<Integer>> colorToBalls = new HashMap();
        Map<Integer, Integer> ballToColor = new HashMap();

        int count = 0;

        for (int i = 0; i < queries.length; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            if (colorToBalls.computeIfAbsent(color, k -> new HashSet()).add(ball)) {
                if (ballToColor.containsKey(ball)) {
                    int prevColor = ballToColor.get(ball);

                    colorToBalls.get(prevColor).remove(ball);

                    if (colorToBalls.get(prevColor).size() == 0) {
                        colorToBalls.remove(prevColor);
                    }
                }

                ballToColor.put(ball, color);
            }

            ans[i] = colorToBalls.size();
        }

        return ans;
    }
}