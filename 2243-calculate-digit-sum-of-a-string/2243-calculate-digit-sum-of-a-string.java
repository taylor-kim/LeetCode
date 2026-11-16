class Solution {
    public String digitSum(String s, int k) {
        return mySol(s, k);
    }

    public String mySol(String s, int k) {
        StringBuilder next = new StringBuilder();

        while (s.length() > k) {
            for (int i = 0; i < s.length(); i += k) {
                String ss = s.substring(i, Math.min(i + k, s.length()));
                int sum = 0;

                for (int j = 0; j < ss.length(); j++) {
                    sum += (int)(ss.charAt(j) - '0');
                }

                next.append(sum);
            }

            s = next.toString();
            next.setLength(0);
        }

        return s;
    }
}