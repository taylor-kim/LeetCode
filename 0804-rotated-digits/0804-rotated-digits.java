class Solution {
    public int rotatedDigits(int n) {
        return mySol2(n);
    }

    public int mySol2(int n) {
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if (check(i)) {
                // System.out.println(i);
                ans++;
            }
        }

        return ans;
    }

    Set<Integer> invalid = Set.of(3, 4, 7);
    Set<Integer> valid = Set.of(2, 5, 6, 9);
    Map<Integer, Integer> mirrors = Map.of(
        0, 0,
        1, 1,
        2, 5,
        5, 2,
        6, 9,
        8, 8,
        9, 6
    );

    private boolean check(int n) {
        if (n < 2) return false;

        int pos = 1;
        int mirror = 0;
        boolean hasDiff = false;

        while (n > 0) {
            int d = n % 10;

            if (invalid.contains(d)) return false;

            int rot = mirrors.get(d);
            mirror += rot * pos;

            if (valid.contains(d)) hasDiff = true;
            
            n /= 10;
            pos *= 10;
        }

        return hasDiff && n != mirror;
    }

    public int mySol_fail(int n) {
        if (n < 2) return 0;

        int ans = 1;

        Map<Integer, Integer> counter = Map.of(
            9, 4,
            8, 3,
            7, 3,
            6, 3,
            5, 2,
            4, 2,
            3, 2,
            2, 1,
            1, 0,
            0, 0
        );

        while (n > 0) {
            int d = n % 10;
            int count = counter.get(d);

            ans = ans * count;

            n /= 10;
        }

        return ans;
    }
}