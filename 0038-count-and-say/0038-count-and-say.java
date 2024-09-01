class Solution {
    public String countAndSay(int n) {
        return mySol(n);
    }

    public String mySol(int n) {
        if (n == 1) {
            return "1";
        }

        String s = mySol(n - 1);

        StringBuilder sb = new StringBuilder();

        int count = 0;
        int before = -1;

        for (char ch : s.toCharArray()) {
            int num = (int)ch - '0';

            if (before == -1 || before == num) {
                count++;
                before = num;
            } else {
                sb.append(count).append(before);
                count = 1;
                before = num;
            }
        }

        sb.append(count).append(before);

        return sb.toString();
    }
}