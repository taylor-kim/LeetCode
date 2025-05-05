class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        return mySol(dominoes);
    }

    public int mySol(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap();

        int ans = 0;

        for (int[] domino : dominoes) {
            int key1 = getKey(domino[0], domino[1]);

            ans += map.getOrDefault(key1, 0);

            map.put(key1, map.getOrDefault(key1, 0) + 1);
        }

        return ans;
    }

    private int getKey(int a, int b) {
        if (a < b) {
            a += b;
            b = a - b;
            a -= b;
        }
        return a * 10 + b;
    }
}