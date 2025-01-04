class Solution {
    public int numWays(String[] words, String target) {
        return mySol2_with_assist(words, target);
    }

    public int try_bottomup(String[] words, String target) {
        List<int[]> chars = new ArrayList();

        for (int k = 0; k < words[0].length(); k++) {
            int[] freq = new int[26];

            for (String word : words) {
                freq[word.charAt(k) - 'a']++;
            }

            chars.add(freq);
        }

        // int[][] dp = new int[][];

        return 1;
    }

    public int mySol2_with_assist(String[] words, String target) {
        List<int[]> chars = new ArrayList();

        for (int k = 0; k < words[0].length(); k++) {
            int[] freq = new int[26];

            for (String word : words) {
                freq[word.charAt(k) - 'a']++;
            }

            chars.add(freq);
        }

        return (int)topdown2(chars, target, 0, 0, new Long[words[0].length()][target.length()]);
    }

    public long topdown2(List<int[]> chars, String target, int k, int index, Long[][] memo) {
        if (index >= target.length()) return 1;
        if (chars.size() - k < target.length() - index) return 0;

        if (memo[k][index] != null) {
            return memo[k][index];
        }

        int mod = (int)1e9 + 7;

        long ans = 0;

        int count = chars.get(k)[target.charAt(index) - 'a'];

        if (count > 0) {
            long result = topdown2(chars, target, k + 1, index + 1, memo);

            if (result > 0) {
                
                ans = (ans + ((count * result) % mod)) % mod;
            }
        }

        ans = (ans + topdown2(chars, target, k + 1, index, memo)) % mod;

        return memo[k][index] = ans;
    }

    public int mySol(String[] words, String target) {
        List<List<Character>> chars = new ArrayList();

        for (int k = 0; k < words[0].length(); k++) {
            List<Character> list = new ArrayList();

            for (String word : words) {
                list.add(word.charAt(k));
            }

            Collections.sort(list);
            chars.add(list);
        }

        return (int)topdown(chars, target, 0, 0, new Long[words[0].length() + 1][target.length()]);
    }

    public long topdown(List<List<Character>> chars, String target, int k, int index, Long[][] memo) {
        if (index >= target.length()) return 1;
        if (k >= chars.size()) return 0;

        if (memo[k][index] != null) {
            return memo[k][index];
        }

        int mod = (int)1e9 + 7;

        long ans = 0;

        int[] indices = findCandidates(chars.get(k), target.charAt(index));

        int count = indices[1] - indices[0];

        // int count = chars.get(k)[target.charAt(index) - 'a'];

        // for (int i = 0; i < count; i++) {
        //     ans = (ans + topdown(chars, target, k + 1, index + 1, memo)) % mod;
        // }

        if (count > 0) {
            long result = topdown(chars, target, k + 1, index + 1, memo);

            if (result > 0) {
                
                ans = (ans + ((count * result) % mod)) % mod;
            }
        }

        // for (String word : words) {
        //     if (word.charAt(k) == target.charAt(index)) {
        //         ans += topdown(words, target, k + 1, index + 1);
        //     }
        // }

        ans = (ans + topdown(chars, target, k + 1, index, memo)) % mod;

        return memo[k][index] = ans;
    }

    private int[] findCandidates(List<Character> list, char target) {
        int left = leftmost(list, target);
        int right = leftmost(list, (char)(target + 1));

        // System.out.println(String.format("left:%d, right:%d, target:%c", left, right, target));

        return new int[] {left, right};
    }

    private int leftmost(List<Character> list, char target) {
        // System.out.println(String.format("target:%c", target));
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    public int topdown(String word, String target, int k, int index) {
        if (index >= target.length()) return 1;
        if (k >= word.length()) return 0;

        int ans = 0;

        if (word.charAt(k) == target.charAt(index)) {
            // ans = topdown()
        }

        return ans;
    }
}