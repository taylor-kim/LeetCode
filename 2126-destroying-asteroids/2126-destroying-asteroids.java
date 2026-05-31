class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        return mySol(mass, asteroids);
    }

    public boolean mySol(int mass, int[] asteroids) {
        Arrays.sort(asteroids);

        long sum = mass;

        for (int each : asteroids) {
            if (sum < each) return false;

            sum += each;
        }

        return true;
    }
}