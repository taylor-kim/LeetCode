class Solution {
    public String answerString(String word, int numFriends) {
        return official_simple(word, numFriends);
    }

    public String official_simple(String word, int numFriends) {
        if (numFriends == 1) return word;

        int n = word.length();

        String ans = "";

        for (int i = 0; i < n; i++) {
            String s = word.substring(i, Math.min(i + n - numFriends + 1, n));

            if (ans.compareTo(s) <= 0) ans = s;
        }

        return ans;
    }

    public String mySol2_fail(String word, int numFriends) {
        String a = greedy(word, numFriends, true);
        String b = reverse(greedy(reverse(word), numFriends, false));

        return max(a, b);
    }

    public String greedy(String word, int numFriends, boolean lt) {
        int n = word.length();

        int start = 0;
        int end = 0;

        for (end = 0; start < n - numFriends + 1 && end < n; end++) {
            if (lt && word.charAt(start) < word.charAt(end)) {
                start = end;
            } else if (!lt && word.charAt(start) > word.charAt(end)) {
                start = end;
            }
        }

        int maxLength = n - numFriends + 1;

        return word.substring(start, Math.min(maxLength, end));
    }

    public String mySol_tle(String word, int numFriends) {
        return dfs(word, 0, numFriends, new String[word.length()][numFriends + 1]);
    }

    public String dfs(String word, int index, int numFriends, String[][] memo) {
        if (numFriends == 0 || index >= word.length()) return "";

        if (memo[index][numFriends] != null) return memo[index][numFriends];

        String ans = "";

        for (int i = index; i < word.length() - numFriends + 1; i++) {
            String mine = word.substring(index, i + 1);

            String sub = dfs(word, i + 1, numFriends - 1, memo);

            String max = max(mine, sub);

            ans = max(ans, max);
        }

        return memo[index][numFriends] = ans;
    }

    private String max(String a, String b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}