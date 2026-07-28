class Solution {
    public String smallestPalindrome(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        StringBuilder sb = new StringBuilder();

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String odd = "";

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i] / 2; j++) {
                sb.append((char)(i + 'a'));
            }

            if (freq[i] % 2 == 1) {
                odd = String.valueOf((char)(i + 'a'));
            }
        }

        return sb.toString() + odd + sb.reverse().toString();
    }
}