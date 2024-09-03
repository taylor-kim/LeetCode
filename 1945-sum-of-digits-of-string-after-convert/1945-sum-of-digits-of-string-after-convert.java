class Solution {
    public int getLucky(String s, int k) {
        return official2(s, k);
    }

    public int official2(String s, int k) {
        int ans = 0;

        for (char c : s.toCharArray()) {
            int num = c - 'a' + 1;

            while (num > 0) {
                ans += num % 10;
                num /= 10;
            }
        }

        while (--k > 0) {
            int sum = 0;

            while (ans > 0) {
                sum += ans % 10;
                ans /= 10;
            }

            ans = sum;
        }

        return ans;
    }

    public int mySol(String s, int k) {
        String c = convert(s);

        int ans = 0;

        while (k-- > 0) {
            ans = reduce(c);
            c = String.valueOf(ans);
        }

        return ans;
    }

    private String convert(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }

        return sb.toString();
    }

    private int reduce(String s) {
        int ret = 0;

        for (char c : s.toCharArray()) {
            ret += c - '0';
        }

        return ret;
    }
}