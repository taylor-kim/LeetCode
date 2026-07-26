class Solution {
    public double angleClock(int hour, int minutes) {
        return practice_20260726(hour, minutes);
    }

    public double practice_20260726(int hour, int minutes) {
        double dPerH = 30;
        double dPerM = 6;

        hour %= 12;

        double hdMovingPerMin = 5d / 60 * dPerM;

        double md = minutes * dPerM;
        // double hd = hour * dPerH + (minutes * hdMovingPerMin);
        double rateOfMd = md / 360;
        double hd = hour * dPerH + (rateOfMd * dPerH);

        // System.out.println("hd:%f, md:%f".formatted(hd, md));

        double a = (hd - md + 360) % 360;

        return Math.min(a, 360 - a);
    }
}