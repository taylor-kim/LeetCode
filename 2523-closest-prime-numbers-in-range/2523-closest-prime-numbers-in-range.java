class Solution {
    public int[] closestPrimes(int left, int right) {
        return mySol(left, right);
    }

    public int[] mySol(int left, int right) {
        boolean[] primes = getPrimes_edit(right + 1);

        List<Integer> list = new ArrayList();

        for (int i = left; i <= right; i++) {
            if (primes[i]) list.add(i);
        }

        if (list.size() < 2) return new int[] {-1, -1};

        int[] ans = {0, Integer.MAX_VALUE};
        int num1 = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            int num2 = list.get(i);

            if (num2 - num1 < ans[1] - ans[0]) {
                ans[0] = num1;
                ans[1] = num2;
            }

            num1 = num2;
        }

        return ans;
    }

    private boolean[] getPrimes_mine(int max) {
        boolean[] primes = new boolean[max];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i < primes.length; i++) {
            if (!primes[i]) continue;

            for (int j = i * 2; j < primes.length; j += i) {
                primes[j] = false;
            }
        }

        return primes;
    }

    private boolean[] getPrimes_edit(int max) {
        boolean[] primes = new boolean[max];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i * i < primes.length; i++) {
            if (!primes[i]) continue;

            for (int j = i * i; j < primes.length; j += i) {
                primes[j] = false;
            }
        }

        return primes;
    }
}