class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        return official_counting(seats, students);
    }

    public int official_counting(int[] seats, int[] students) {
        int maxPosition = Math.max(findMax(seats), findMax(students));

        int[] freq = new int[maxPosition];

        int n = seats.length;

        for (int i = 0; i < n; i++) {
            freq[seats[i]]++;
            freq[students[i]]--;
        }

        int ans = 0;
        int unmatched = 0;

        for (int i = 1; i < maxPosition; i++) {
            int f = freq[i];
            
            unmatched += f;

            ans += Math.abs(unmatched);
        }

        return ans;
    }

    private int findMax(int[] array) {
        int maximum = 0;
        for (int num : array) {
            if (num > maximum) {
                maximum = num;
            }
        }
        return maximum + 1;
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