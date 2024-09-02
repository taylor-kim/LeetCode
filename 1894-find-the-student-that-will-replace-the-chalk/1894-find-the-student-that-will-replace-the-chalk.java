class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        return mySol2(chalk, k);
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