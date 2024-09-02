class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        return official_prefixsum_binarySearch(chalk, k);
    }

    public int official_prefixsum_binarySearch(int[] chalk, int k) {
        int n = chalk.length;
        long[] pSum = new long[n];
        pSum[0] = chalk[0];

        for (int i = 1; i < n; i++) {
            pSum[i] = pSum[i - 1] + chalk[i];
        }

        long sum = pSum[n - 1];
        long remainder = k % sum;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int mid = (right - left) / 2 + left;

            if (pSum[mid] <= remainder) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public int mySol2(int[] chalk, int k) {
        long sum = 0;

        for (int c : chalk) {
            sum += c;
        }

        long remainder = ((long)k) % sum;

        int ans = 0;

        while (chalk[ans] <= remainder) {
            remainder -= chalk[ans++];
        }

        return ans;
    }

    public int mySol_tle(int[] chalk, int k) {
        int ans = 0;

        while (chalk[ans] <= k) {
            k -= chalk[ans++];
            ans = ans % chalk.length;
        }

        return ans;
    }
}