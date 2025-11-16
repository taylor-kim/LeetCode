class Solution {
    public int numSub(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        long ans = 0;
        int mod = (int)1e9 + 7;
        
        int left = 0;
        int ones = 0;

        for (int right = 0; right < s.length(); right++) {
            int num = s.charAt(right);

            if (num == '0') {
                long count = 1l * ones * (1 + ones) / 2;
                ans = (ans + count) % mod;
                ones = 0;
            } else {
                ones++;
            }
        }

        long count = 1l * ones * (1 + ones) / 2;
        ans = (ans + count) % mod;

        return (int)ans;
    }
}