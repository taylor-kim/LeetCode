class Solution {
    public String countAndSay(int n) {
        return try_20240901_topdown(n);
    }

    public String try_20240901_topdown(int n) {
        if (n == 1) return "1";

        String s = try_20240901_topdown(n - 1);

        char prev = '0';
        int count = 0;

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (prev == c) {
                count++;
            } else {
                if (prev != '0') {
                    sb.append(count).append(prev);
                }

                prev = c;
                count = 1;
            }
        }

        sb.append(count).append(prev);

        return sb.toString();
    }

    public String try_20240901(int n) {
        StringBuilder ans = new StringBuilder();
        ans.append("1");

        while (--n > 0) {
            StringBuilder next = new StringBuilder();

            char prev = '0';
            int count = 1;

            for (char c : ans.toString().toCharArray()) {
                if (prev == c) {
                    count++;
                } else {
                    if (prev != '0') {
                        next.append(count).append(prev);
                    }
                    prev = c;
                    count = 1;
                }
            }

            next.append(count).append(prev);

            ans = next;
        }

        return ans.toString();
    }
}