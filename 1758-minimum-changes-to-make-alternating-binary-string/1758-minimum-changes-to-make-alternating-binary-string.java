class Solution {
    public int minOperations(String s) {
        return try_20260305(s);
    }

    public int try_20260305(String s) {
        int zeroOne = 0;
        int oneZero = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - '0';

            if (c != i % 2) {
                zeroOne++;
            } else {
                oneZero++;
            }
        }

        return Math.min(zeroOne, oneZero);
    }

    public int more_simple_code(String s) {
        int startZero = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i % 2 != s.charAt(i) - '0') {
                startZero++;
            }
        }

        return Math.min(startZero, s.length() - startZero);
    }

    public int official_check_only_one(String s) {
        int startZero = 0;

        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';

            if (i % 2 == 0) {
                startZero += num;
            } else {
                startZero += num == 0 ? 1 : 0;
            }
        }

        return Math.min(startZero, s.length() - startZero);
    }

    public int mySol(String s) {
        int startZero = 0;
        int startOne = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            int alt = num == 1 ? 0 : 1;

            if (i % 2 == 0) {
                startZero += num;
                startOne += alt;
            } else {
                startZero += alt;
                startOne += num;
            }
        }

        return Math.min(startZero, startOne);
    }

    public int mySol_fail(String s) {
        int ans = 0;

        boolean zero = s.charAt(0) == '0';

        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                ans += zero ? 0 : 1;
            } else {
                ans += zero ? 1 : 0;
            }

            zero = !zero;
        }

        return ans;
    }
}