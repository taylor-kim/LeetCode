class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        return mySol(colors, k);
    }

    public int mySol(int[] colors, int k) {
        int n = colors.length;
        int count = 0;

        int left = 0;
        int prev = colors[0];

        for (int right = 1; right < n + k - 1; right++) {
            int index = right % n;

            // System.out.print(colors[index] + ", ");

            if (prev == colors[index]) {
                // System.out.println(String.format("%d == %d, left:%d, right:%d", prev, colors[index], left, right));
                left = right;
            } else if (right - left + 1 == k) {
                // System.out.println(String.format("counting! left:%d, right:%d", left, right));
                count++;
                left++;
            }

            prev = colors[index];
        }

        return count;
    }
}