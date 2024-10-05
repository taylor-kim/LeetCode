class Solution {
    public boolean checkInclusion(String s1, String s2) {
        return mySol(s1, s2);
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