class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return mySol_topdown(books, shelfWidth);
    }

    public int mySol_topdown(int[][] books, int shelfWidth) {
        return mySol_topdown(books, shelfWidth, 0, shelfWidth, 0, new Integer[books.length][shelfWidth + 1]);
    }

    public int mySol_topdown(int[][] books, int shelfWidth
            , int index, int width, int maxH, Integer[][] memo) {
            if (index == books.length - 1) {
                if (width >= books[index][0]) return Math.max(maxH, books[index][1]);
                return maxH + books[index][1];
            }

            if (memo[index][width] != null) return memo[index][width];

            int curW = books[index][0];
            int curH = books[index][1];

            int only = maxH + mySol_topdown(books, shelfWidth, index + 1, shelfWidth - curW, curH, memo);

            int newWidth = width - curW;
            
            if (newWidth >= 0) {
                int newMaxH = Math.max(maxH, curH);

                int include = mySol_topdown(books, shelfWidth, index + 1, newWidth, newMaxH, memo);

                return memo[index][width] = Math.min(only, include);
            }

            return memo[index][width] = only;
    }
}