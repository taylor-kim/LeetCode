class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        boolean removed = false;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (removed) return false;

                if (isPalindrom(s, left + 1, right)) {
                    left++;
                } else if (isPalindrom(s, left, right - 1)) {
                    right--;
                } else {
                    return false;
                }

                removed = true;
            }

            left++;
            right--;
        }

        return true;
    }

    private boolean isPalindrom(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}