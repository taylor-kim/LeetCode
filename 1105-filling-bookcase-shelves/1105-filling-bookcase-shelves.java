class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return mySol_topdown(books, shelfWidth);
    }

    public int mySol_topdown(int[][] books, int shelfWidth) {
        return mySol_topdown(books, shelfWidth, 0, 0, new Integer[books.length][books.length]);
    }

    public int mySol_topdown(int[][] books, int shelfWidth, int index, int level, Integer[][] memo) {
            if (index >= books.length) return 0;

            if (memo[index][level] != null) {
                return memo[index][level];
            }

            int ans = Integer.MAX_VALUE;

            int curW = 0;
            int curH = 0;

            for (int i = index; i < books.length; i++) {
                curW += books[i][0];

                if (curW > shelfWidth) {
                    break;
                }

                curH = Math.max(curH, books[i][1]);

                ans = Math.min(ans, curH + mySol_topdown(books, shelfWidth, i + 1, level + 1, memo));
            }

            return memo[index][level] = ans;
    }
}