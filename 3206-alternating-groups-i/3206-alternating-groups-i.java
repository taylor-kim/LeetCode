class Solution {
    public int numberOfAlternatingGroups(int[] colors) {
        return mySol(colors);
    }

    public int mySol(int[] colors) {
        int n = colors.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int one = colors[i];
            int two = colors[(i + 1) % n];
            int three = colors[(i + 2) % n];

            if (one != two && two != three) count++;
        }

        return count;
    }
}