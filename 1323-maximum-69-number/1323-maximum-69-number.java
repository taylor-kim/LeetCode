class Solution {
    public int maximum69Number (int num) {
        return mySol(num);
    }

    public int mySol(int num) {
        char[] digits = String.valueOf(num).toCharArray();

        int ans = 0;
        boolean isChanged = false;

        for (int i = 0; i < digits.length; i++) {
            if (!isChanged && digits[i] == '6') {
                ans = ans * 10 + 9;
                isChanged = true;
            } else {
                ans = ans * 10 + (digits[i] - '0');
            }
        }

        return ans;
    }
}