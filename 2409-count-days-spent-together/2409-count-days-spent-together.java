class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        return editorial(arriveAlice, leaveAlice, arriveBob, leaveBob);
    }

    public int editorial(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] pSum = new int[13];

        for (int i = 0; i < daysPerMonth.length; i++) {
            pSum[i + 1] = pSum[i] + daysPerMonth[i];
        }

        int aa = getDays(arriveAlice, pSum);
        int ab = getDays(arriveBob, pSum);

        int la = getDays(leaveAlice, pSum);
        int lb = getDays(leaveBob, pSum);

        return Math.max(0, Math.min(la, lb) - Math.max(aa, ab) + 1);
    }

    private int getDays(String s, int[] pSum) {
        int month = Integer.parseInt(s.substring(0, 2));
        int day = Integer.parseInt(s.substring(3));

        return pSum[month - 1] + day;
    }

    public int mySol(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        String from = arriveAlice.compareTo(arriveBob) < 0 ?  arriveBob : arriveAlice;
        String to = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;

        int monthFrom = Integer.parseInt(from.substring(0, 2));
        int monthTo = Integer.parseInt(to.substring(0, 2));

        int dayFrom = Integer.parseInt(from.substring(3));
        int dayTo = Integer.parseInt(to.substring(3));

        if (monthFrom > monthTo) return 0;

        if (monthFrom == monthTo) {
            return Math.max(dayTo - dayFrom + 1, 0);
        }

        int ans = 0;

        for (int i = monthFrom + 1; i < monthTo; i++) {
            ans += daysPerMonth[i - 1];
        }

        ans += (daysPerMonth[monthFrom - 1] - dayFrom + 1) + dayTo;

        return ans;
    }
}