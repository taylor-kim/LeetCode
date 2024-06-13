class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        return official_counting(seats, students);
    }

    public int official_counting(int[] seats, int[] students) {
        int[] freq = new int[1001];

        int n = seats.length;

        for (int i = 0; i < n; i++) {
            freq[seats[i]]++;
            freq[students[i]]--;
        }

        int ans = 0;
        int unmatched = 0;

        for (int f : freq) {
            ans += Math.abs(unmatched);
            unmatched += f;
        }

        return ans;
    }

    public int mySol(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);

        int ans = 0;

        for (int i = 0; i < seats.length; i++) {
            ans += Math.abs(seats[i] - students[i]);
        }

        return ans;
    }
}