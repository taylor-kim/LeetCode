class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        return mySol(s, dictionary);
    }

    public int mySol(String s, String[] dictionary) {
        Set<String> dict = new HashSet();

        for (String word : dictionary) {
            dict.add(word);
        }

        return mySol(s, dict, 0, new Integer[s.length()]);
    }

    public int mySol(String s, Set<String> dict, int index, Integer[] memo) {
        if (index >= s.length()) return 0;

        if (memo[index] != null) {
            return memo[index];
        }

        StringBuilder sb = new StringBuilder();

        int ans = Integer.MAX_VALUE;

        for (int i = index; i < s.length(); i++) {
            sb.append(s.charAt(i));

            int length = dict.contains(sb.toString()) ? 0 : sb.length();

            ans = Math.min(ans, length + mySol(s, dict, i + 1, memo));
        }

        return memo[index] = ans;
    }
}