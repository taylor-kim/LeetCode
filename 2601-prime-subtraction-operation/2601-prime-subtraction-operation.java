class Solution {
    public boolean primeSubOperation(int[] nums) {
        return mySol(nums);
    }

    public boolean mySol(int[] nums) {
        int n = nums.length;

        if (n <= 1) return true;

        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        List<Integer> primes = getPrimesAtMost(max);

        // System.out.println(primes + "\n");

        for (int i = nums.length - 2; i >= 0; i--) {
            int lo = nums[i];
            int hi = nums[i + 1];

            if (lo >= hi) {
                if (lo - 1 < lo - hi + 1) return false;
                
                // int target = Math.min(lo - 1, lo - hi + 1);
                int target = lo - hi + 1;

                int p = pickGte(primes, target);

                // System.out.println(primes);

                // System.out.println(String.format("target:%d, lo:%d, p:%d", target, lo, p));

                if (p < 0 || p >= lo) return false;

                nums[i] -= p;

                // System.out.println(Arrays.toString(nums) + "\n");
            }
        }

        // System.out.println("\n" + Arrays.toString(nums));

        return true;
    }

    private List<Integer> getPrimesAtMost(int max) {
        boolean[] arr = new boolean[max + 1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;

        for (int i = 2; i <= max; i++) {
            if (!arr[i]) continue;

            for (int j = i + i; j <= max; j += i) {
                arr[j] = false;
            }
        }

        List<Integer> primes = new ArrayList();

        for (int i = 2; i <= max; i++) {
            if (arr[i]) primes.add(i);
        }

        return primes;
    }

    private int pickGte(List<Integer> primes, int target) {
        if (primes.size() == 0) return -1;

        int index = Collections.binarySearch(primes, target);

        if (index >= 0) return primes.get(index);

        index = -(index + 1);

        if (index == primes.size()) return -1;

        return primes.get(index);
    }
}