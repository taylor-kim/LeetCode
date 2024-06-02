class Solution {
    public void reverseString(char[] s) {
        mySol(s);
    }

    public void mySol(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char lc = s[left];
            s[left++] = s[right];
            s[right--] = lc;
        }
    }
}