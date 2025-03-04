class Solution {
    public boolean checkPowersOfThree(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        Set<Integer> powers = new HashSet();

        // System.out.println(n);
        while (n >= 3) {
            n = getOdd(n, powers);
            // System.out.println(n);
        }
        // System.out.println(n);

        if (n < 0) return false;

        return n != 2;
    }

    private int getOdd(int n, Set<Integer> powers) {
        int lo = 0;
        int hi = (int)1e7;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            int power = (int)Math.pow(3, mid);

            if (power <= n) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        if (lo > 0) lo--;

        if (!powers.add(lo)) {
            return -1;
        }

        // System.out.println(String.format("lo:%d, powers:%s", lo, powers));

        return n - (int)(Math.pow(3, lo));
    }
}