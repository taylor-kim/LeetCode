class Solution {
    public long sumAndMultiply(int n) {
        return mySol(n);
    }

    public long mySol(int n) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;

        while (n > 0) {
            int d = n % 10;

            if (d > 0) {
                sb.append(d);
                sum += d;
            }
            n /= 10;
        }

        int x = sb.length() > 0 ? Integer.parseInt(sb.reverse().toString()) : 0;

        return (long)x * sum;
    }
}