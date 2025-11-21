class Solution {
    public int countPalindromicSubsequence(String s) {
        return try_20251121(s);
    }

    public int try_20251121(String s) {
        List<Integer>[] indices = new List[26];

        for (int i = 0; i < 26; i++) indices[i] = new ArrayList();

        char[] arr = s.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
            indices[arr[i] - 'a'].add(i);
        }

        int ans = 0;

        for (int i = 0; i < indices.length; i++) {
            List<Integer> list = indices[i];

            if (list.size() >= 2) {
                ans += countUniqChar(arr, list.get(0) + 1, list.get(list.size() - 1) - 1);
            }
        }

        return ans;
    }

    private int countUniqChar(char[] arr, int from, int to) {
        Set<Character> set = new HashSet();

        for (int i = from; i <= to; i++) {
            set.add(arr[i]);
        }

        return set.size();
    }

    public int official_two(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (first[c - 'a'] == -1) {
                first[c - 'a'] = i;
            } else {
                last[c - 'a'] = i;
            }
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            Set<Character> counter = new HashSet();
            for (int k = first[i] + 1; k < last[i]; k++) {
                counter.add(s.charAt(k));
            }
            ans += counter.size();
        }

        return ans;
    }

    public int official_improved(String s) {
        Set<Character> letters = new HashSet();
        Map<Character, int[]> indices = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letters.add(c);

            indices.computeIfAbsent(c, k -> new int[] {-1, -1});
            
            int[] pair = indices.get(c);

            if (pair[0] == -1) {
                pair[0] = i;
            } else {
                pair[1] = i;
            }
        }

        int ans = 0;

        for (char c : letters) {
            int[] pair = indices.get(c);
            int i = pair[0];
            int j = pair[1];

            Set<Character> between = new HashSet();

            for (int k = i + 1; k < j; k++) {
                between.add(s.charAt(k));
            }

            ans += between.size();
        }

        return ans;
    }

    public int official_one(String s) {
        Set<Character> letters = new HashSet();

        for (char c : s.toCharArray()) {
            letters.add(c);
        }

        int ans = 0;

        for (char c : letters) {
            int i = -1;
            int j = 0;

            for (int k = 0; k < s.length(); k++) {
                if (c == s.charAt(k)) {
                    if (i == -1) {
                        i = k;
                    }

                    j = k;
                }
            }

            Set<Character> between = new HashSet();

            for (int k = i + 1; k < j; k++) {
                between.add(s.charAt(k));
            }

            ans += between.size();
        }

        return ans;
    }

    public int mySol_tle(String s) {
        int n = s.length();
        int[][] dp = new int[26][26];

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    count(s, i + 1, j - 1, s.charAt(i), dp);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                ans += dp[i][j];
            }
        }

        return ans;
    }

    private void count(String s, int from, int to, char one, int[][] dp) {
        for (int i = from; i <= to; i++) {
            dp[one - 'a'][s.charAt(i) - 'a'] = 1;
        }
    }
}