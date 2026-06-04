class Solution {
    public int totalWaviness(int num1, int num2) {
        return mySol(num1, num2);
    }

    public int mySol(int num1, int num2) {
        int ans = 0;

        for (int i = num1; i <= num2; i++) {
            ans += count(i);
        }

        return ans;
    }

    private int count(int num) {
        int ans = 0;

        if (num < 100) return ans;

        int origin = num;

        int prev = num % 10;
        num /= 10;
        int inclination = 0;

        while (num > 0) {
            int digit = num % 10;
            num /= 10;

            if (digit == prev) {
                inclination = 0;
                continue;
            } else if (digit < prev) {
                if (inclination == 0) {
                    inclination = 1;
                } else if (inclination < 0) {
                    inclination = 1;
                    ans++;
                }
            } else {
                if (inclination == 0) {
                    inclination = -1;
                } else if (inclination > 0) {
                    inclination = -1;
                    ans++;
                }
            }

            prev = digit;
        }

        // if (ans > 0) {
        //     System.out.println("num:%d, wave:%d".formatted(origin, ans));
        // }

        return ans;
    }
}