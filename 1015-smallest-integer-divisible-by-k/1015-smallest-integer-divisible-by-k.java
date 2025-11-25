class Solution {
    public int smallestRepunitDivByK(int k) {
        return editorial(k);
    }

    public int editorial(int k) {
        int remainder = 0;
        for (int length = 1; length <= k; length++) {
            remainder = (remainder * 10 + 1) % k;
            if (remainder % k == 0) {
                return length;
            }
        }

        return -1;
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