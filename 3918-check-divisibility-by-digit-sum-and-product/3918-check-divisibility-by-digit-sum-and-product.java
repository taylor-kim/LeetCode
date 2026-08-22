class Solution {
    public boolean checkDivisibility(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        int s = 0;
        int p = 1;

        int num = n;

        while (num > 0) {
            s += num % 10;
            p *= num % 10;

            num /= 10;
        }

        return n % (s + p) == 0;
    }
}