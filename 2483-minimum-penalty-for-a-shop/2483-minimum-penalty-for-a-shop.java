class Solution {
    public int bestClosingTime(String customers) {
        return fail(customers);
    }

    public int others(String s) {
        int max = 0;
        int score = 0;
        int ans = -1;

        for (int i = 0; i < s.length(); i++) {
            score += s.charAt(i) == 'Y' ? 1 : -1;

            if (score > max) {
                max = score;
                ans = i;
            }
        }

        return ans + 1;
    }

    public int others2(String s) {
        int n = s.length();
        int[] ltr = new int[n + 1];
        int[] rtl = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ltr[i] = ltr[i - 1] + (s.charAt(i - 1) == 'N' ? 1 : 0);
            rtl[n - i] = rtl[n - i + 1] + (s.charAt(n - i) == 'Y' ? 1 : 0);
        }

        int min = Integer.MAX_VALUE;
        int ans = 0;

        for (int i = 0; i <= n; i++) {
            if (ltr[i] + rtl[i] < min) {
                min = ltr[i] + rtl[i];
                ans = i;
            }
        }

        return ans;
    }

    public int fail(String s) {
        int n = s.length();
        int[] ltr = new int[n + 1];
        int[] rtl = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ltr[i] = ltr[i - 1] + (s.charAt(i - 1) == 'N' ? 1 : 0);
            rtl[n - i] = rtl[n - i + 1] + (s.charAt(n - i) == 'Y' ? 1 : 0);
        }

        // System.out.println(Arrays.toString(ltr));
        // System.out.println(Arrays.toString(rtl));

        int min = Integer.MAX_VALUE;
        int ans = 0;

        for (int i = 0; i <= n; i++) {
            if (ltr[i] + rtl[i] < min) {
                min = ltr[i] + rtl[i];
                ans = i;
            }
        }

        return ans;
    }
}