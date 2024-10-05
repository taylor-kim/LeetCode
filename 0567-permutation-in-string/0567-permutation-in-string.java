class Solution {
    public boolean checkInclusion(String s1, String s2) {
        return official_optimised(s1, s2);
    }

    public boolean official_optimised(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
            freq2[s2.charAt(i) - 'a']++;
        }

        int count = 0;

        for (int i = 0; i < freq1.length; i++) {
            if (freq1[i] == freq2[i]) count++;
        }

        if (count == 26) return true;

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int left = s2.charAt(i) - 'a';
            int right = s2.charAt(i + s1.length()) - 'a';

            freq2[right]++;

            if (freq1[right] == freq2[right]) {
                count++;
            } else if (freq1[right] + 1 == freq2[right]) {
                count--;
            }

            freq2[left]--;

            if (freq1[left] == freq2[left]) {
                count++;
            } else if (freq1[left] - 1 == freq2[left]) {
                count--;
            }

            if (count == 26) return true;
        }

        return false;
    }

    public boolean mySol(String s1, String s2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char c : s1.toCharArray()) {
            freq1[c - 'a']++;
        }

        int k = s1.length();

        int left = 0;
        
        for (int right = 0; right < s2.length(); right++) {
            freq2[s2.charAt(right) - 'a']++;

            if (right - left + 1 > k) {
                freq2[s2.charAt(left++) - 'a']--;
            }

            if (right - left + 1 == k && equals(freq1, freq2)) return true;
        }

        return false;
    }

    private boolean equals(int[] freq1, int[] freq2) {
        for (int i = 0; i < freq1.length; i++) {
            if (freq1[i] != freq2[i]) return false;
        }

        return true;
    }
}