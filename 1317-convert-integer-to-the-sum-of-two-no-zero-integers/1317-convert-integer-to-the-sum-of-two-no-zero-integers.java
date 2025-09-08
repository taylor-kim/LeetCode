public class Solution {
    public int[] getNoZeroIntegers(int n) {
        return mySol(n);
    }

    public int[] mySol(int n) {
        return topdown(0, n);
    }

    private int[] topdown(int a, int b) {
        if (b <= 0) return null;

        if (isOk(a) && isOk(b)) {
            return new int[] {a, b};
        }

        return topdown(a + 1, b - 1);
    }

    private boolean isOk(int n) {
        if (n <= 0) return false;

        while (n > 0) {
            int digit = n % 10;

            if (digit == 0) return false;

            n /= 10; 
        }

        return true;
    }
}