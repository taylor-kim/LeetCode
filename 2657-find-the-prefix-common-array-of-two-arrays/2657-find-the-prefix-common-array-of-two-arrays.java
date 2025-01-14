class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        return official_freq(A, B);
    }

    public int[] official_freq(int[] a, int[] b) {
        int n = a.length;
        int[] ans = new int[n];
        int[] freq = new int[n + 1];

        int commonCount = 0;

        for (int i = 0; i < n; i++) {
            freq[a[i]]++;
            if (freq[a[i]] == 2) commonCount++;

            freq[b[i]]++;
            if (freq[b[i]] == 2) commonCount++;

            ans[i] = commonCount;
        }

        return ans;
    }

    public int[] mySol(int[] a, int[] b) {
        int n = a.length;
        int[] ans = new int[n];

        Set<Integer> set = new HashSet();

        for (int i = 0; i < n; i++) {
            boolean addedA = set.add(a[i]);
            boolean addedB = set.add(b[i]);

            if (a[i] == b[i]) {
                ans[i] = ans[Math.max(0, i - 1)] + 1;
            } else {
                if (!addedA) {
                    ans[i]++;
                }

                if (!addedB) {
                    ans[i]++;
                }

                ans[i] += ans[Math.max(0, i - 1)];
            }
        }

        return ans;
    }
}