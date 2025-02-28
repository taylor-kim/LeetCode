class Solution {
    public int numOfSubarrays(int[] arr) {
        return official_prefixsum(arr);
    }

    public int official_prefixsum(int[] arr) {
        int ans = 0;
        int mod = (int)1e9 + 7;
        int prefixSum = 0;

        int oddCount = 0;
        int evenCount = 1; // due to initial sum is 0

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                ans += oddCount;
                evenCount++;
            } else {
                ans += evenCount;
                oddCount++;
            }

            ans %= mod;
        }

        return ans;
    }

    public int official_dp(int[] arr) {
        int n = arr.length;
        int[] odd = new int[n];
        int[] even = new int[n];

        int ans = 0;
        int mod = (int)1e9 + 7;

        if (arr[0] % 2 == 0) {
            even[0] = 1;
        } else {
            odd[0] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] % 2 == 0) {
                even[i] = (1 + even[i - 1]) % mod;
                odd[i] = odd[i - 1];
            } else {
                odd[i] = (1 + even[i - 1]) % mod;
                even[i] = odd[i - 1];
            }
        }

        // System.out.println(Arrays.toString(odd));
        // System.out.println(Arrays.toString(even));

        for (int count : odd) {
            ans = (ans + count) % mod;
        }
        
        return ans;
    }
}