class Solution {
    public int countSymmetricIntegers(int low, int high) {
        return mySol(low, high);
    }

    public int mySol(int low, int high) {
        int ans = 0;

        for (int num = low; num <= high; num++) {
            if (isSym(num)) ans++;
        }

        return ans;
    }

    private boolean isSym(int num) {
        char[] arr = String.valueOf(num).toCharArray();

        int n = arr.length;

        if (n % 2 == 1) return false;

        int sum = 0;
        int half = n / 2;

        for (int i = 0; i < n; i++) {
            if (i < half) {
                sum += arr[i];
            } else {
                sum -= arr[i];
            }
        }

        return sum == 0;
    }
}