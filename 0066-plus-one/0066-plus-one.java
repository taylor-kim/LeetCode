class Solution {
    public int[] plusOne(int[] digits) {
        return improve(digits);
    }

    public int[] improve(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += 1;

            int carry = digits[i] / 10;
            digits[i] %= 10;

            if (carry == 0) return digits;
        }

        int[] ans = new int[digits.length + 1];
        ans[0] = 1;

        return ans;
    }

    public int[] mySol(int[] digits) {
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += carry;

            carry = digits[i] / 10;
            digits[i] %= 10;
        }

        if (carry == 0) {
            return digits;
        } else {
            int[] ans = new int[digits.length + 1];

            for (int i = 0; i < digits.length; i++) {
                ans[i] = digits[i];
            }

            ans[0] = 1;

            return ans;
        }
    }
}