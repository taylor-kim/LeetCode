class Solution {
    public int numberOfBeams(String[] bank) {
        return mySol(bank);
    }

    public int mySol(String[] bank) {
        int ans = 0;

        int prev = 0;

        for (String floor : bank) {
            int current = 0;

            for (int i = 0; i < floor.length(); i++) {
                if (floor.charAt(i) == '1') current++;
            }

            if (current == 0) continue;

            ans += prev * current;
            prev = current;
        }

        return ans;
    }
}