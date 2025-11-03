class Solution {
    public int minCost(String colors, int[] neededTime) {
        return mySol(colors, neededTime);
    }

    public int mySol(String colors, int[] neededTime) {
        return topdown(colors.toCharArray(), neededTime, 0, 1);
    }

    public int topdown(char[] colors, int[] neededTime, int prev, int index) {
        if (index >= colors.length) return 0;

        if (colors[prev] == colors[index]) {
            if (neededTime[prev] < neededTime[index]) {
                return neededTime[prev] + topdown(colors, neededTime, index, index + 1);
            } else {
                return neededTime[index] + topdown(colors, neededTime, prev, index + 1);
            }
        }

        return topdown(colors, neededTime, index, index + 1);
    }
}