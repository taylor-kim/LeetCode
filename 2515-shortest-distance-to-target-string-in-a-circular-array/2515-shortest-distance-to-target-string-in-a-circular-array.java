class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        return official_one_pass(words, target, startIndex);
    }

    public int official_one_pass(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = n;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int dist = Math.abs(i - startIndex);
                ans = Math.min(ans, Math.min(dist, n - dist));
            }
        }

        return ans == n ? -1 : ans;
    }

    public int mySol(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = n;

        int count = 0;

        int index = startIndex;

        while (count++ < n) {
            if (words[(index % n)].equals(target)) {
                ans = Math.min(ans, index - startIndex);
                break;
            }
            index++;
        }

        index = startIndex;

        while (count-- > 0) {
            if (words[((index + n) % n)].equals(target)) {
                ans = Math.min(ans, startIndex - index);
                break;
            }
            index--;
        }

        return ans == n ? -1 : ans;
    }
}