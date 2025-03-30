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

        int left = 0;
        int farthest = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);

            farthest = Math.max(farthest, lastIndex[c - 'a']);

            if (farthest == right) {
                ans.add(right - left + 1);
                left = right + 1;
            }
        }

        return ans;
    }
}