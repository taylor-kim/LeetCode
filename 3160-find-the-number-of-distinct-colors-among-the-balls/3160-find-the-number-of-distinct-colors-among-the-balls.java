class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        return official(limit, queries);
    }

    public int[] official(int limit, int[][] queries) {
        int[] ans = new int[queries.length];

        Map<Integer, Integer> colorToBalls = new HashMap();
        Map<Integer, Integer> ballToColor = new HashMap();

        int count = 0;

        for (int i = 0; i < queries.length; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            if (!ballToColor.containsKey(ball)) {
                colorToBalls.put(color, colorToBalls.getOrDefault(color, 0) + 1);
            } else if (ballToColor.get(ball) != color) {
                int prevColor = ballToColor.get(ball);

                colorToBalls.put(prevColor, colorToBalls.get(prevColor) - 1);

                if (colorToBalls.get(prevColor) == 0) {
                    colorToBalls.remove(prevColor);
                }

                colorToBalls.put(color, colorToBalls.getOrDefault(color, 0) + 1);
            }

            ballToColor.put(ball, color);

            ans[i] = colorToBalls.size();
        }

        return ans;
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