class Solution {
    public int lengthOfLastWord(String s) {
        return mySol(s);
    }

    public int mySol(String s) {
        int n = s.length();
        int start = 0;

        for (int end = n - 1; end >= 0; end--) {
            if (s.charAt(end) == ' ') continue;

            start = end;

            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }

            return end - start;
        }

        return 0;
    }
}