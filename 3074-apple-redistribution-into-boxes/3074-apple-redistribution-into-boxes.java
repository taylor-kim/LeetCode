class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        return mySol(apple, capacity);
    }

    public int mySol(int[] apple, int[] capacity) {
        int sum = 0;
        for (int a : apple) sum += a;

        Arrays.sort(capacity);

        int ans = 0;

        for (int i = capacity.length - 1; i >= 0 && sum > 0; i--) {
            sum -= capacity[i];

            ans++;
        }

        return ans;
    }
}