class Solution {
    public String largestGoodInteger(String num) {
        return try_20250814(num);
    }

    public String try_20250814(String num) {
        int prev = -1;
        int count = 0;

        int ans = -1;

        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';

            if (digit == prev) {
                count++;
            } else {
                prev = digit;
                count = 1;
            }

            if (count >= 3) {
                ans = Math.max(ans, digit);
            }
        }

        return ans >= 0 ? String.format("%d%d%d", ans, ans, ans) : "";
    }

    public String official_2(String num) {
        char max = (char)('0' - 1);

        for (int i = 0; i < num.length() - 2; i++) {
            char cur = num.charAt(i);

            if (cur == num.charAt(i + 1)
                && cur == num.charAt(i + 2)
                && max < cur) {
                    max = cur;
                }
        }

        return max >= '0' ? "" + max + max + max : "";
    }

    public String official_1(String num) {
        List<String> list = Arrays.asList(
            "999", "888", "777", "666", "555", "444", "333", "222", "111", "000"
        );

        for (String target : list) {
            // if (num.indexOf(target) != -1) {
            //     return target;
            // }
            if (contains(target, num)) {
                return target;
            }
        }

        return "";
    }

    private boolean contains(String target, String base) {
        for (int i = 0; i < base.length() - 2; i++) {
            if (base.charAt(i) == target.charAt(0)
                && base.charAt(i + 1) == target.charAt(1)
                && base.charAt(i + 2) == target.charAt(2)) {
                    return true;
                }
        }
        return false;
    }

    public String simple(String num) {
        String ans = "";

        for (int i = 0; i < num.length() - 2; i++) {
            String sub = num.substring(i, i + 3);

            if (sub.charAt(0) == sub.charAt(1) 
                && sub.charAt(1) == sub.charAt(2)
                && ans.compareTo(sub) < 0) {
                ans = sub;
            }
        }

        return ans;
    }

    public String mySol(String num) {
        String ans = "";

        int left = 0;
        String match = num.substring(0, 1);

        for (int right = 1; right < num.length(); right++) {
            char cur = num.charAt(right);

            if (match.charAt(match.length() - 1) == cur) {
                match += cur;
            } else {
                left = right;
                match = "" + cur;
            }

            if (match.length() == 3) {
                if (ans.compareTo(match) < 0) {
                    ans = match;
                }
                left++;
            }
        }

        return ans;
    }
}