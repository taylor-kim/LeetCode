class Solution {
    public List<Integer> partitionLabels(String s) {
        return try_merge_intervals(s);
    }

    public List<Integer> try_merge_intervals(String s) {
        int n = s.length();

        int[] firstIndex = new int[26];
        Arrays.fill(firstIndex, -1);

        int[] lastIndex = new int[26];
        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int start = 0, end = 0;
        List<Integer> ans = new ArrayList();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (firstIndex[c - 'a'] == -1) {
                firstIndex[c - 'a'] = i;
            }

            if (end < firstIndex[c - 'a']) {
                ans.add(end - start + 1);
                start = i;
                end = i;
            }

            end = Math.max(end, lastIndex[c - 'a']);
        }

        ans.add(end - start + 1);

        return ans;
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