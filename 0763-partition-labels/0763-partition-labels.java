class Solution {
    public List<Integer> partitionLabels(String s) {
        return mySol(s);
    }

    public List<Integer> mySol(String s) {
        List<Integer> ans = new ArrayList();

        int n = s.length();

        int[] lastIndex = new int[26];

        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int farthest = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count++;

            farthest = Math.max(farthest, lastIndex[c - 'a']);

            if (farthest == i) {
                ans.add(count);
                count = 0;
            }
        }

        return ans;
    }
}