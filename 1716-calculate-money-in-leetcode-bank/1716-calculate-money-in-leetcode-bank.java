class Solution {
    public int totalMoney(int n) {
        return better(n);
    }

    public int better(int n) {
        int k = n / 7;
        int odd = n % 7;

        int weeks = k * (56 + 7 * (k - 1)) / 2;
        int odds = odd * (2 * (k + 1) + (odd - 1)) / 2;

        return weeks + odds;
    }

    public int official_math(int n) {
        int k = n / 7;
        int f = 28;
        int l = 28 + (k - 1) * 7;

        int weekSum = k * (f + l) / 2;
        int oddDaysSum = 0;
        int monday = k + 1;

        for (int day = 0; day < n % 7; day++) {
            oddDaysSum += monday + day;
        }

        return weekSum + oddDaysSum;
    }

    public int official_loop(int n) {
        int ans = 0;
        int monday = 1;

        while (n > 0) {
            for (int day = 0; day < Math.min(n, 7); day++) {
                ans += monday + day;
            }
            n -= 7;
            monday++;
        }

        return ans;
    }

    public int mySol(int n) {
        int weeks = n / 7;
        int oddDays = n % 7;

        // 1 : 1 ~ 7
        // 2 : 2 ~ 8
        // 3 : 3 ~ 9
        // n : n ~ n + 6

        // 7(nth_week + nth_week + 6) / 2

        // 7(1 + 7) / 2
        // 7(2 + 8) / 2
        // 7(3 + 9) / 2

        // 7(8 + 10 + 12 + ... (n + n + 6))/2

        // 7(n(8 + 8 + (n - 1) * 2)/2)/2

        int ans = 7 * (weeks * (16 + ((weeks - 1) * 2)) / 2) / 2;

        if (oddDays > 0) {
            int start = weeks + 1;

            ans += oddDays * (start + start + oddDays - 1) / 2;
        }

        return ans;
    }
}