class Solution {
    public double angleClock(int hour, int minutes) {
        return mySol(hour, minutes);
    }

    public double mySol(int hour, int minutes) {
        double hUnit = 30.0;
        double mUnit = 6.0;

        // 90
        // 97.5

        hour %= 12;

        double md = minutes * mUnit;
        double rateOfMin = md / 360;
        double hd = hour * hUnit + (hUnit * rateOfMin);

        double min = Math.min(hd, md);
        double max = Math.max(hd, md);

        return Math.min(max - min, 360 + min - max);
    }
}