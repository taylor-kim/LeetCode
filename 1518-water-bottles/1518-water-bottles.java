class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        
        while (numBottles >= numExchange) {
            int odd = numBottles % numExchange;
             numBottles /= numExchange;
            ans += numBottles;
            
            numBottles += odd;
            
}
         return ans;
    }
}