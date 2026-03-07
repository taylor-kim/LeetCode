class Solution {
    public int minFlips(String s) {
        return mySol3_hint(s);
    }

    public int mySol3_hint(String s) {
        int k = s.length();
        String ss = s + s;

        int left = 0;
        int zeroOne = 0;
        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < ss.length(); right++) {
            int digit = ss.charAt(right) - '0';

            if (right % 2 == 0) {
                zeroOne += digit;
            } else {
                zeroOne += digit == 0 ? 1 : 0;
            }

            if (right - left + 1 == k) {                
                ans = Math.min(ans, Math.min(zeroOne, k - zeroOne));

                int removeDigit = ss.charAt(left) - '0';

                if (left % 2 == 0) {
                    zeroOne -= removeDigit;
                } else {
                    zeroOne -= removeDigit == 0 ? 1 : 0;
                }

                left++;
            }
        }

        return ans;
    }

    public int mySol2_fail(String s) {
        int n = s.length();
        int index = 0;

        char last = s.charAt(n - 1);

        while (index < n && s.charAt(index) != last) {
            last = s.charAt(index++);
        }

        if (index != 0) {
            s = s.substring(index, n);
        }

        System.out.println(s + ", " + index);

        return mySol_wrong(s);
    }

    public int mySol_wrong(String s) {
        int n = s.length();
        int zeroOne = 0;

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            if (i % 2 == 0) {
                zeroOne += digit;
            } else {
                zeroOne += digit == 0 ? 1 : 0;
            }
        }

        return Math.min(zeroOne, n - zeroOne);
    }
}