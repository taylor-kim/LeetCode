class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        return mySol(days, costs);
    }

    public int editorial_topdown(int[] days, int[] costs) {
        Set<Integer> needTravels = new HashSet();

        for (int day : days) needTravels.add(day);

        return editorial_topdown(costs, 1, new Integer[days[days.length - 1] + 1], needTravels);
    }

    public int editorial_topdown(int[] costs, int day, Integer[] memo, Set<Integer> needTravels) {
        if (day >= memo.length) return 0;

        if (!needTravels.contains(day)) return editorial_topdown(costs, day + 1, memo, needTravels);

        if (memo[day] != null) return memo[day];

        int ans = costs[0] + editorial_topdown(costs, day + 1, memo, needTravels);
        ans = Math.min(ans, costs[1] + editorial_topdown(costs, day + 7, memo, needTravels));
        ans = Math.min(ans, costs[2] + editorial_topdown(costs, day + 30, memo, needTravels));

        return memo[day] = ans;
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