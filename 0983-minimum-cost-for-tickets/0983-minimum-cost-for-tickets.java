class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        return mySol(days, costs);
    }

    public int mySol(int[] days, int[] costs) {
        return topdown(days, costs, 0, new Integer[days.length]);
    }

    public int topdown(int[] days, int[] costs, int index, Integer[] memo) {
        if (index < 0) index = -(index + 1);

        if (index >= days.length) return 0;

        if (memo[index] != null) return memo[index];

        int ans = costs[0] + topdown(days, costs, Arrays.binarySearch(days, days[index] + 1), memo);
        ans = Math.min(ans, costs[1] + topdown(days, costs, Arrays.binarySearch(days, days[index] + 7), memo));
        ans = Math.min(ans, costs[2] + topdown(days, costs, Arrays.binarySearch(days, days[index] + 30), memo));

        return memo[index] = ans;
    }
}