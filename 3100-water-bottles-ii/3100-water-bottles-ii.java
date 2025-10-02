class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        return mySol(numBottles, numExchange);
    }

    public int mySol(int numBottles, int numExchange) {
        int ans = 0;

        while (numBottles >= numExchange) {
            ans += numExchange;

            numBottles -= numExchange++;

            numBottles++;
        }

        return ans + numBottles;
    }
}