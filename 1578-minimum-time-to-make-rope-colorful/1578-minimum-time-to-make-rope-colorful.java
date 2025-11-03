class Solution {
    public int minCost(String colors, int[] neededTime) {
        return mySol_iter(colors, neededTime);
    }

    public int mySol_iter(String colors, int[] neededTime) {
        int ans = 0;
        int left = 0;
        char prev = colors.charAt(0);
        
        for (int right = 1; right < colors.length(); right++) {
            char cur = colors.charAt(right);

            if (prev != cur) {
                left = right;
                prev = cur;
            } else {
                if (neededTime[left] < neededTime[right]) {
                    ans += neededTime[left];
                    left = right;
                    prev = cur;
                } else {
                    ans += neededTime[right];
                }
            }
        }

        return ans;
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