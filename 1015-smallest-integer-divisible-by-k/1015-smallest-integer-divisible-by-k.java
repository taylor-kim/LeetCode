class Solution {
    public int smallestRepunitDivByK(int k) {
        return mySol(k);
    }

    public int mySol(int k) {
        int n = 1;
        int ans = 1;

        if (k % 2 == 0) return -1;

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