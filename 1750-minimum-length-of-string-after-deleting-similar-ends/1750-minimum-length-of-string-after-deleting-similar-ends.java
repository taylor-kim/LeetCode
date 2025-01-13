class Solution {
    public int minimumLength(String s) {
        return try_20250113(s);
    }

    public int try_20250113(String s) {
        int n = s.length();
        int left = 0;
        int right = n - 1;

        while (left < right && s.charAt(left) == s.charAt(right)) {
            while (left + 1 < n && s.charAt(left) == s.charAt(left + 1)) {
                left++;
            }

            while (right - 1 >= 0 && s.charAt(right - 1) == s.charAt(right)) {
                right--;
            }

            left++;
            right--;
        }

        return Math.max(0, right - left + 1);
    }
}