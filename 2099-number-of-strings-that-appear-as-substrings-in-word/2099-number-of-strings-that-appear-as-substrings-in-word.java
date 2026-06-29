class Solution {
    public int numOfStrings(String[] patterns, String word) {
        return mySol(patterns, word);
    }

    public int mySol(String[] patterns, String word) {
        int ans = 0;

        for (String p : patterns) {
            if (word.contains(p)) ans++;
        }

        return ans;
    }
}