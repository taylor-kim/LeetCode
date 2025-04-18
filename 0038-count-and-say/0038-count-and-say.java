class Solution {
    public String countAndSay(int n) {
        return mySol(n);
    }

    public String mySol(int n) {
        return topdown(n);
    }

    public String topdown(int n) {
        if (n == 1) return "1";

        String s = topdown(n - 1);

        return encode(s);
    }

    private String encode(String s) {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        char prev = 'x';

        for (char c : s.toCharArray()) {
            if (prev == c) {
                count++;
            } else {
                if (prev != 'x') {
                    sb.append(count).append(prev);
                }
                count = 1;
                prev = c;
            }
        }

        sb.append(count).append(prev);

        return sb.toString();
    }
}