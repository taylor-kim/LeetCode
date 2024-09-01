class Solution {
    public String nearestPalindromic(String n) {
        return official_binarySearch(n);
    }

    public String official_binarySearch(String n) {
        long num = Long.parseLong(n);

        long prev = prevPalin(num);
        long next = nextPalin(num);

        if (Math.abs(prev - num) <= Math.abs(next - num)) {
            return Long.toString(prev);
        } else {
            return Long.toString(next);
        }
    }

    private long prevPalin(long num) {
        long left = 0;
        long right = num;

        long ans = 0;

        while (left < right) {
            long mid = (right - left) / 2 + left;
            long palin = toPalin(mid);

            if (palin < num) {
                ans = palin;
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return ans;
    }

    private long nextPalin(long num) {
        long left = num + 1;
        long right = (long) 1e18;

        long ans = 0;
        
        while (left < right) {
            long mid = (right - left) / 2 + left;
            long palin = toPalin(mid);

            if (palin > num) {
                ans = palin;
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private long toPalin(long num) {
        String s = Long.toString(num);
        int n = s.length();
        int left = (n - 1) / 2;
        int right = n / 2;

        char[] arr = s.toCharArray();

        while (left >= 0) {
            arr[right++] = arr[left--];
        }

        return Long.parseLong(new String(arr));
    }

    public String official_shit(String n) {
        int l = n.length();
        int mid = l / 2 - ((l + 1) % 2);
        long firstHalf = Long.parseLong(n.substring(0, mid + 1));

        List<Long> cands = new ArrayList();

        cands.add(halfToPalindrome(firstHalf, l % 2 == 0));
        cands.add(halfToPalindrome(firstHalf + 1, l % 2 == 0));
        cands.add(halfToPalindrome(firstHalf - 1, l % 2 == 0));
        cands.add((long)Math.pow(10, l - 1) - 1);
        cands.add((long)Math.pow(10, l) + 1);

        long minDiff = Long.MAX_VALUE;
        long ans = 0;
        long origin = Long.parseLong(n);

        for (long cand : cands) {
            if (cand == origin) continue;

            long diff = Math.abs(cand - origin);

            if (diff < minDiff) {
                minDiff = diff;
                ans = cand;
            } else if (diff == minDiff) {
                ans = Math.min(cand, ans);
            }
        }

        return String.valueOf(ans);
    }

    private long halfToPalindrome(long firstHalf, boolean even) {
        long p = firstHalf;

        if (!even) firstHalf /= 10;

        while (firstHalf > 0) {
            p = p * 10 + (firstHalf % 10);
            firstHalf /= 10;
        }

        return p;
    }

    public String mySol_fail(String n) {
        if (isPalindrome(n)) {
            return minimize(n);
        }

        if (n.charAt(0) == '1') {
            boolean allZero = true;
            for (int i = 1; i < n.length() && allZero; i++) {
                if (n.charAt(i) != '0') {
                    allZero = false;
                }
            }

            if (allZero) {
                return String.valueOf(Integer.parseInt(n) - 1);
            }
        }

        StringBuilder ans = new StringBuilder();

        int l = n.length();

        if (l == 1) {
            return String.valueOf(Integer.parseInt(n) - 1);
        } else if (l == -2) {
            int number = Integer.parseInt(n);
            int lower = 9;
            int upper = number / 10 * 10 + number / 10;

            if (number - lower <= upper - number) {
                ans.append(lower);
            } else {
                ans.append(upper);
            }
        } else {
            int mid = l / 2;

            int left = mid - 1;
            int right = mid + 1;

            if (l % 2 == 0) {
                int midLeft = mid - 1;
                ans.append(n.charAt(midLeft) - '0');
                ans.append(n.charAt(midLeft) - '0');
                left--;
            } else {
                ans.append(n.charAt(mid) - '0');
            }

            while (left >= 0) {
                int lv = n.charAt(left--) - '0';
                int rv = n.charAt(right++) - '0';

                ans.insert(0, lv);
                ans.append(lv);
            }
        }

        return ans.toString();
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }

    private String minimize(String s) {
        if (s.length() == 1) {
            return String.valueOf(Integer.parseInt(s) - 1);
        }

        if (isOnlyOne(s)) {
            return String.valueOf(Integer.parseInt(s) - 2);
        }

        if (isOnlyNine(s)) {
            return String.valueOf(Integer.parseInt(s) + 2);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s);

        int l = s.length();
        int mid = l / 2;

        char alt = (char)(s.charAt(mid) - 1);

        if (s.charAt(mid) == '0') {
            alt = '1';
        }

        sb.setCharAt(mid, alt);

        if (l % 2 == 0) {
            sb.setCharAt(mid - 1, alt);
        }

        return sb.toString();
    }

    private boolean isOnlyOne(String s) {
        for (char c : s.toCharArray()) {
            if (c != '1') return false;
        }

        return true;
    }

    private boolean isOnlyNine(String s) {
        for (char c : s.toCharArray()) {
            if (c != '9') return false;
        }

        return true;
    }
}