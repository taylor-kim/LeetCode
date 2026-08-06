class Solution {
    public int smallestNumber(int n, int t) {
        return mySol(n, t);
    }

    public int mySol(int n, int t) {
        int prod = 1;
        int num = n;

        while (num > 0) {
            prod *= num % 10;
            num /= 10;
        }

        if (prod % t == 0) return n;
        
        return mySol(n + 1, t);
    }
}