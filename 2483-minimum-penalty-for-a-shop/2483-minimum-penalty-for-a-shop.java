class Solution {
    public int bestClosingTime(String customers) {
        return mySol(customers);
    }

    public int mySol(String customers) {
        int n = customers.length();
        int ans = n;
        int yes = 0;
        int no = 0;
        int penalty = n;

        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                yes++;
            } else {
                no++;
            }
        }

        if (yes == 0) return 0;
        if (no == 0) return n;

        int currentY = 0;
        int currentN = 0;

        for (int i = 0; i < n; i++) {
            if (penalty > currentN + (yes - currentY)) {
                penalty = currentN + (yes - currentY);
                ans = i;
            }

            if (customers.charAt(i) == 'Y') {
                currentY++;
            } else {
                currentN++;
            }
        }

        if (penalty > currentN + (yes - currentY)) {
            penalty = currentN + (yes - currentY);
            ans = n;
        }

        return ans;
    }
}