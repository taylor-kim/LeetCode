class Solution {
    public int countTriples(int n) {
        return mySol2(n);
    }

    public int mySol2(int n) {
        int ans = 0;

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int square = a * a + b * b;

                double c = Math.sqrt(square);
                double odd = c - ((int)c);

                // System.out.println("a:%d, b:%d, sq:%d, c:%f, int c:%d, odd:%f".formatted(a, b, square, c, (int)c, odd));

                if (odd > 0) continue;

                if (c <= n) ans++;
            }
        }

        // for (int a = 1; a <= Math.sqrt(n); a++) {
        //     for (int b = 1; b <= Math.sqrt(n); b++) {
        //         System.out.println("%d + %d = %d".formatted(a * a, b * b, a * a + b * b));
        //         if (a * a + b * b <= n) ans++;
        //     }
        // }

        return ans;
    }
}