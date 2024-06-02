class Solution {
    public int numSteps(String s) {
        return official_simulation(s);
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