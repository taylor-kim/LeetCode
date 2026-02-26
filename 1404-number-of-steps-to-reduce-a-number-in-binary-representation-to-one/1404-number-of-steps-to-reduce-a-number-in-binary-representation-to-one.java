class Solution {
    public int numSteps(String s) {
        return greedy(s);
    }

    public int greedy(String s) {
        int ans = 0;
        int carry = 0;

        for (int i = s.length() - 1; i > 0; i--) {
            int digit = (int)(s.charAt(i) - '0') + carry;

            if (digit % 2 == 0) {
                ans++;
            } else {
                ans += 2;
                carry = 1;
            }
        }

        return ans + carry;
    }

    public int try_20260226_bf(String s) {
        char[] arr = s.toCharArray();

        int ans = 0;

        int i = arr.length - 1;

        while (i > 0) {
            if (arr[i] == '0') {
                ans++;
                i--;
            } else {
                arr[i] = '0';
                ans++;
                int j = i - 1;

                while (j >= 0 && arr[j] == '1') {
                    arr[j] = '0';
                    j--;
                }

                if (j >= 0) {
                    arr[j] = '1';
                }

                ans += i - j;
                i = j;
            }
        }

        return ans;
    }

    public int official_greedy(String s) {
        int ans = 0;
        int carry = 0;

        for (int i = s.length() - 1; i > 0; i--) {
            int digit = (int)(s.charAt(i) - '0') + carry;

            if (digit % 2 == 1) {
                ans += 2;
                carry = 1;
            } else {
                ans++;
            }
        }

        return ans + carry;
    }

    public int official_simulation(String s) {
        StringBuilder sb = new StringBuilder(s);

        int ans = 0;

        while (sb.length() > 1) {
            if (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            } else {
                int index = sb.length() - 1;

                while (index >= 0 && sb.charAt(index) == '1') {
                    sb.setCharAt(index--, '0');
                }

                if (index < 0) {
                    sb.insert(0, '1');
                } else {
                    sb.setCharAt(index, '1');
                }
            }

            ans++;
        }

        return ans;
    }

    public int mySol(String s) {
        int ans = 0;

        while (!s.equals("1")) {
            // System.out.print(s + " => ");

            if (s.charAt(s.length() - 1) == '0') {
                s = s.substring(0, s.length() - 1);
            } else {
                int length = s.length();
                int index = length - 2;

                while (index >= 0 && s.charAt(index) == '1') {
                    index--;
                }

                s = index >= 0 ? (s.substring(0, index) + "1") : "1";
                s += "0".repeat(length - index - 1);
            }

            ans++;

            // System.out.println(s + ", " + ans);
        }

        return ans;
    }
}