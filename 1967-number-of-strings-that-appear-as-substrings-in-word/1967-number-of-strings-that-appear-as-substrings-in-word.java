class Solution {
    public int numOfStrings(String[] patterns, String word) {
        return mySol2(patterns, word);
    }

    public int mySol2(String[] patterns, String word) {
        int ans = 0;

        for (String s : patterns) {
            int[] p = getP(s);
            if (search(word, s, p)) ans++;
        }

        return ans;
    }

    private int[] getP(String s) {
        int n = s.length();

        int count = 0;
        int index = 1;

        int[] p = new int[n];

        while (index + count < n) {
            if (s.charAt(index + count) == s.charAt(count)) {
                p[index + count] = count + 1;
                count++;
            } else {
                if (count == 0) {
                    index++;
                } else {
                    index += count - p[count - 1];
                    count = p[count - 1];
                }
            }
        }

        return p;
    }

    private boolean search(String s, String q, int[] p) {
        int count = 0;
        int index = 0;

        while (index + count < s.length()) {
            if (s.charAt(index + count) == q.charAt(count)) {
                count++;

                if (count == q.length()) return true;
            } else {
                if (count == 0) {
                    index++;
                } else {
                    index += count - p[count - 1];
                    count = p[count - 1];
                }
            }
        }

        return false;
    }

    public int mySol(String[] patterns, String word) {
        int ans = 0;

        for (String p : patterns) {
            if (word.contains(p)) ans++;
        }

        return ans;
    }
}