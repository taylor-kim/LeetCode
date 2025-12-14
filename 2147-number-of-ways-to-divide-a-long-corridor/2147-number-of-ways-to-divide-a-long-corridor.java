class Solution {
    public int numberOfWays(String corridor) {
        return mySol2(corridor);
    }

    public int mySol2(String corridor) {
        return Math.max(topdown(corridor.toCharArray(), false, 0), 0);
    }

    public int topdown(char[] arr, boolean hasSeat, int index) {
        if (index >= arr.length) return hasSeat ? 1 : -1;

        int mod = (int)1e9 + 7;

        int seats = 0;
        int plants = 0;
        int next = index;

        while (index != 0 && next < arr.length && arr[next] == 'P') {
            next++;
            plants++;
        }

        while (next < arr.length && seats < 2) {
            if (arr[next++] == 'S') {
                seats++;
            }
        }

        if (seats == 0) return hasSeat ? 1 : -1;

        if (seats < 2) return -1;

        int sub = topdown(arr, true, next);

        if (sub < 0) return -1;

        long ans = 1l * (plants > 0 ? plants + 1 : 1) * sub;

        return (int)(ans % mod);
    }

    public int mySol_fail(String corridor) {
        char[] arr = corridor.toCharArray();
        int n = arr.length;
        int seats = 0;

        for (char c : arr) {
            if (c == 'S') seats++;
        }

        if (seats > 4 || seats % 2 == 1) return 0;

        if (seats == 2) return 1;

        int leftSeats = 0;
        int mod = (int)1e9 + 7;
        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == 'S') leftSeats++;

            if (leftSeats == 2) {
                ans = (ans + 1) % mod;
            } else if (leftSeats > 2) {
                break;
            }
        }

        return ans;
    }
}