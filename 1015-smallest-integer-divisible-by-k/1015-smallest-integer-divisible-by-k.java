class Solution {
    public int smallestRepunitDivByK(int k) {
        return mySol_improved(k);
    }

    public int editorial(int k) {
        for (int odd = 1; odd < k; odd++) {
            // odd = 
        }

        return 0;
    }

    public int mySol_improved(int k) {
        int n = 1;
        int ans = 1;

        // if (k % 2 == 0) return -1;

        boolean[] visit = new boolean[k + 1];
        visit[n] = true;

        while (n % k != 0) {
            n = (n * 10 + 1) % k;
            if (visit[n]) return -1;
            visit[n] = true;
            ans++;
        }

        return ans;
    }

    public int mySol(int k) {
        int n = 1;
        int ans = 1;

        // if (k % 2 == 0) return -1;

        Set<Integer> visit = new HashSet();
        visit.add(n);

        while (n % k != 0) {
            n = (n * 10 + 1) % k;
            if (!visit.add(n)) return -1;
            ans++;
        }

        return ans;
    }
}