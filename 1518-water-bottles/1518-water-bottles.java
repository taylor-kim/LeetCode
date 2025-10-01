class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        return mySol(numBottles, numExchange);
    }

    public int mySol(int numBottles, int numExchange) {
        int ans = 0;

        while (numBottles >= numExchange) {
            int exchanged = numBottles / numExchange;
            int odd = numBottles % numExchange;

            numBottles = exchanged + odd;

            ans += exchanged * numExchange;
        }

        return ans + numBottles;
    }
}