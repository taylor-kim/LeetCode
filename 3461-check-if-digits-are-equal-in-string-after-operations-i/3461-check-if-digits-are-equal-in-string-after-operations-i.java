class Solution {
    public boolean hasSameDigits(String s) {
        return mySol(s);
    }

    public boolean mySol(String s) {
        while (s.length() > 2) {
            StringBuilder next = new StringBuilder();

            int prev = s.charAt(0) - '0';

            for (int i = 1; i < s.length(); i++) {
                int current = s.charAt(i) - '0';

                next.append((prev + current) % 10);

                prev = current;
            }

            s = next.toString();
        }

        return s.charAt(0) == s.charAt(1);
    }
}