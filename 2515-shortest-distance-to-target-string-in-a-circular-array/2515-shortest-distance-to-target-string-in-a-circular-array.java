class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        return mySol(words, target, startIndex);
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