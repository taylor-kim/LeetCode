class Solution {
    public int countPalindromicSubsequence(String s) {
        return editorial_improve_mySol(s);
    }

    public int editorial_improve_mySol(String s) {
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (first[c - 'a'] == -1) {
                first[c - 'a'] = i;
            }

            last[c - 'a'] = i;
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            int left = first[i];
            int right = last[i];

            Set<Integer> between = new HashSet();

            for (int mid = left + 1; mid < right; mid++) {
                between.add(s.charAt(mid) - 'a');
            }

            ans += between.size();
        }

        return ans;
    }

    public int mySol(String s) {
        int ans = 0;

        for (int i = 0; i < 26; i++) {
            char c = (char)(i + 'a');

            ans += count(s, c);
        }

        return ans;
    }

    private int count(String s, char base) {
        int left = 0;
        int right = s.length() - 1;

        while (left < s.length() && s.charAt(left) != base) left++;

        while (right >= 0 && s.charAt(right) != base) right--;

        int[] freq = new int[26];

        for (int i = left + 1; i < right; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int uniq = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) uniq++;
        }

        return uniq;
    }
}