class Solution {
    public double angleClock(int hour, int minutes) {
        return practice_20260726(hour, minutes);
    }

    public double practice_20260726(int hour, int minutes) {
        double hdPerUnit = 30;
        double mdPerUnit = 6;

        hour %= 12;

        double md = minutes * mdPerUnit;
        double hd = hour * hdPerUnit + ((double)minutes / 12.0d * mdPerUnit);

        // System.out.println("hd:%f, md:%f".formatted(hd, md));

        double a = (hd - md + 360) % 360;

        return Math.min(a, 360 - a);
    }
}