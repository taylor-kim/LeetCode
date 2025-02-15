class Solution {
    public int punishmentNumber(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if (partition(String.valueOf(i * i), 0, i)) {
                ans += i * i;
            }
        }

        return ans;
    }

    private boolean partition(String s, int index, int target) {
        if (target < 0) return false;

        if (index >= s.length()) return target == 0;

        int number = 0;

        for (int i = index; i < s.length(); i++) {
            number = number * 10 + (s.charAt(i) - '0');

            if (partition(s, i + 1, target - number)) {
                return true;
            }
        }

        return false;
    }
}