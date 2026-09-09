class Solution {
    public long countCommas(long n) {
        return editorial(n);
    }

    public long editorial(long n) {
        long ans = 0;
        long p = 1000;

        while (p <= n) {
            ans += n - p + 1;
            p *= 1000;
        }

        return ans;
    }

    public long mySol_fail(long n) {
        //aa,bbb,ccc
        //21,234,567
        //34,567
        //3,001,123
        int[] counter = new int[64];

        long num = n;
        int index = 0;

        while (num > 0) {
            int d = 0;
            int pos = 1;
            for (int i = 0; i < 3 && num > 0; i++) {
                d += pos * (num % 10);
                num /= 10;
                pos *= 10;
            }

            counter[index++] = d;
        }

        if (index == 1) return 0;

        num = n;
        long ans = 0;

        //3,001,123

        for (int i = index - 1; i > 0; i--) {
            long high = counter[i] * ((long)Math.pow(1000, i));
            long others = num - high;

            // System.out.println("num:%d, high:%d, others:%d".formatted(num, high, others));

            ans += (counter[i] - 1) * ((long)Math.pow(1000, i));
            ans += others + 1;

            num = others;
        }

        return ans;
    }
}