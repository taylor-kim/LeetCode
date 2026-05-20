class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        return try_20260520(A, B);
    }

    public int[] try_20260520(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];

        Set<Integer> set = new HashSet();
        int same = 0;

        for (int i = 0; i < n; i++) {
            if (!set.add(A[i])) same++;

            if (!set.add(B[i])) same++;

            ans[i] = same;
        }

        return ans;
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