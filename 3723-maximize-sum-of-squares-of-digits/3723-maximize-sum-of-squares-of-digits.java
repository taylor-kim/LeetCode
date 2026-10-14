class Solution {
    public String maxSumOfSquares(int num, int sum) {
        return editorial(num, sum);
    }

    public String editorial(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        int nines = sum / 9;
        int r = sum % 9;

        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }

        if (r > 0) {
            ans.append((char)('0' + r));
        }

        int zeros = num - ans.length();

        for (int i = 0; i < zeros; i++) {
            ans.append('0');
        }

        return ans.toString();
    }

    public String improved_with_editorial(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        while (ans.length() < num) {
            int d = Math.min(9, sum);

            ans.append(d);

            sum -= d;
        }

        return ans.toString();
    }

    public String mySol2(int num, int sum) {
        StringBuilder ans = new StringBuilder();

        while (ans.length() < num) {
            int d = Math.min(9, sum);

            ans.append(d);

            sum -= d;
        }

        return sum == 0 ? ans.toString() : "";
    }

    public String mySol_tle(int num, int sum) {
        String[] ans = {"", "0"};

        topdown(num, sum, new StringBuilder(), 0, ans);

        return ans[0];
    }

    public void topdown(int num, int sum, StringBuilder sb, long score, String[] ans) {
        // System.out.println("num:%d, sum:%d, score:%d, n:%s".formatted(num, sum, score, sb.toString()));
        if (num == 0 && sum == 0) {
            long max = Long.parseLong(ans[1]);

            if (max <= score) {
                String n = sb.toString();
                ans[0] = ans[0].compareTo(n) < 0 ? n : ans[0];
                ans[1] = String.valueOf(score);
            }

            return;
        }

        if (num < 0 || sum < 0) return;

        for (int i = 0; i < 10; i++) {
            if (i == 0 && sb.length() == 0) continue;

            sb.append(i);

            topdown(num - 1, sum - i, sb, score + i * i, ans);

            sb.setLength(sb.length() - 1);
        }
    }
}