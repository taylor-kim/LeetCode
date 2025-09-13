class Solution {
    public int maxFreqSum(String s) {
        return mySol2(s);
    }

    public int mySol2(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int vMax = 0;
        int cMax = 0;

        for (int i = 0; i < freq.length; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 14 || i == 20) {
                vMax = Math.max(vMax, freq[i]);
            } else {
                cMax = Math.max(cMax, freq[i]);
            }
        }

        return vMax + cMax;
    }

    public int mySol(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int vMax = 0;
        int cMax = 0;

        for (int i = 0; i < freq.length; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 14 || i == 20) {
                vMax = Math.max(vMax, freq[i]);
            } else {
                cMax = Math.max(cMax, freq[i]);
            }
        }

        return vMax + cMax;
    }
}