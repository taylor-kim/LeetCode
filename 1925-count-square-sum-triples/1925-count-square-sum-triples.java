class Solution {
    public int countTriples(int n) {
        return editorial(n);
    }

    public int editorial(int n) {
        int res = 0;
        // enumerate a and b
        for (int a = 1; a <= n; ++a) {
            for (int b = 1; b <= n; ++b) {
                // determine if it meets the requirements
                int c = (int) Math.sqrt(a * a + b * b + 1.0);
                if (c <= n && c * c == a * a + b * b) {
                    ++res;
                }
            }
        }
        return res;
    }

    public int mySol_fail(int n) {
        int ans = 0;

        for (int a = 1; a * a <= n; a++) {
            for (int b = 1; b * b <= n; b++) {
                if (a * a + b * b <= n) ans++;
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