class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return mySol_topdown(books, shelfWidth);
    }

    public int mySol_topdown(int[][] books, int shelfWidth) {
        return mySol_topdown(books, shelfWidth, 0, new Integer[books.length]);
    }

    public int mySol_topdown(int[][] books, int shelfWidth, int index, Integer[] memo) {
        if (index >= books.length) return 0;

        if (memo[index] != null) return memo[index];

        int sumWidth = 0;
        int maxH = 0;

        int ans = Integer.MAX_VALUE;

        for (int i = index; i < books.length; i++) {
            sumWidth += books[i][0];

            if (sumWidth > shelfWidth) break;

            maxH = Math.max(maxH, books[i][1]);

            ans = Math.min(ans, maxH + mySol_topdown(books, shelfWidth, i + 1, memo));
        }

        return memo[index] = ans;
    }
}