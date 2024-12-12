class Solution {
    public long pickGifts(int[] gifts, int k) {
        return mySol(gifts, k);
    }

    public long mySol(int[] gifts, int k) {
        int max = 0;

        for (int gift : gifts) max = Math.max(max, gift);

        int[] count = new int[max + 1];

        for (int gift : gifts) count[gift]++;

        int number = max;

        while (number > 0 && k > 0) {
            while (k > 0 && count[number]-- > 0) {
                int root = (int)Math.sqrt(number);
                count[root]++;
                k--;
            }

            number--;
        }

        long ans = 0;

        for (int i = 1; i <= max; i++) {
            if (count[i] > 0) ans += i * count[i];
        }

        return ans;
    }
}