class Solution {
    public int findTheLongestSubstring(String s) {
        return official_bit_prefixSum(s);
    }

    public int official_bit_prefixSum(String s) {
        int[] bits = new int[26];
        bits['a' - 'a'] = 1;
        bits['e' - 'a'] = 2;
        bits['i' - 'a'] = 4;
        bits['o' - 'a'] = 8;
        bits['u' - 'a'] = 16;

        int xor = 0;

        int[] map = new int[32];
        Arrays.fill(map, -1);

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            xor ^= bits[s.charAt(i) - 'a'];

            if (xor != 0 && map[xor] == -1) {
                map[xor] = i;
            }

            ans = Math.max(ans, i - map[xor]);
        }

        return ans;
    }

    public int mySol_second(String s) {
        int[] vCounts = new int[s.length() + 1];

        for (int i = 0; i < s.length(); i++) {
            vCounts[i + 1] = vCounts[i];

            char c = s.charAt(i);

            if (isVowel(c)) {
                vCounts[i + 1] ^= 1 << bit(c);
            }
        }

        // System.out.println(Arrays.toString(vCounts));

        return getLength(0, s.length() - 1, vCounts);
    }

    private int getLength(int left, int right, int[] vCounts) {
        if (left == right) return (vCounts[right + 1] ^ vCounts[left]) == 0 ? 1 : 0;

        if ((vCounts[right + 1] ^ vCounts[left]) == 0) {
            return right - left + 1;
        } else {
            return Math.max(getLength(left + 1, right, vCounts), getLength(left, right - 1, vCounts));
        }
    }

    public int mySol_tle(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            ans = Math.max(ans, topdown(i, i, 0, s));
        }

        return ans;
    }

    private int topdown(int start, int current, int vCount, String s) {
        if (current >= s.length()) return vCount == 0 ? current - start : 0;

        char c = s.charAt(current);

        if (isVowel(c)) {
            vCount ^= 1 << bit(c);
        }

        int ans = topdown(start, current + 1, vCount, s);

        if (vCount == 0) {
            ans = Math.max(current - start + 1, ans);
        }

        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private int bit(char c) {
        return c == 'a' ? 0 :
                c == 'e' ? 1 :
                c == 'i' ? 2 :
                c == 'o' ? 3 : 4;
    }
}