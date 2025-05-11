class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        return try_20250511(arr);
    }

    public boolean try_20250511(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            count = arr[i] % 2 == 0 ? 0 : count + 1;

            if (count == 3) return true;
        }

        return false;
    }

    public boolean mySol(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                count++;
            } else {
                count = 0;
            }

            if (count == 3) return true;
        }

        return false;
    }
}