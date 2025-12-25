class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        return mySol(happiness, k);
    }

    public long mySol(int[] happiness, int k) {
        long ans = 0;

        Arrays.sort(happiness);

        int dec = 0;

        for (int i = happiness.length - 1; i >= 0 && k-- > 0; i--) {
            int h = happiness[i] - dec++;

            if (h <= 0) break;

            ans += h;
        }

        return ans;
    }
}