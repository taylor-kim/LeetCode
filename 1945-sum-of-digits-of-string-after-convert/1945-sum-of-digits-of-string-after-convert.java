class Solution {
    public int getLucky(String s, int k) {
        return mySol(s, k);
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