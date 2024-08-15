class Solution {
    public boolean lemonadeChange(int[] bills) {
        return mySol(bills);
    }

    public boolean mySol(int[] bills) {
        int[] changes = new int[5];

        for (int bill : bills) {
            bill /= 5;
            int need = bill - 1;

            if (need != 0) {
                int unit = need;

                while (unit > 0) {
                    while (unit > 0 && changes[unit] == 0 || need < unit) {
                        unit--;
                    }

                    need -= unit;
                    changes[unit]--;
                }

                if (need != 0) return false;
            }

            changes[bill]++;
        }

        return true;
    }
}