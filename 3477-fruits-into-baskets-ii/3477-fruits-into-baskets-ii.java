class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        return mySol(fruits, baskets);
    }

    public int mySol(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            boolean ok = false;

            for (int j = 0; j < n; j++) {
                if (baskets[j] > 0 && fruits[i] <= baskets[j]) {
                    baskets[j] = -baskets[j];
                    ok = true;
                    break;
                }
            }

            if (!ok) {
                ans++;
            }
        }

        return ans;
    }
}