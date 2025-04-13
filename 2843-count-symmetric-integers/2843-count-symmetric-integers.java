class Solution {
    public int countSymmetricIntegers(int low, int high) {
        return mySol(low, high);
    }

    public int official(int low, int high) {
        int ans = 0;

        for (int num = low; num <= high; num++) {
            if (num < 100 && num % 11 == 0) {
                ans++;
            } else if (num >= 1001 && num < 10000) {
                int left = num / 1000 + ((num / 100) % 10);
                int right = (num % 100) / 10 + (num % 10);

                if (left == right) ans++;
            }
        }

        return ans;
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

        for (int i = 0; i < half; i++) {
            sum += arr[i];
            sum -= arr[n - i - 1];
        }

        return sum == 0;
    }
}