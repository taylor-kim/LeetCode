class Solution {
    public int countTriplets(int[] arr) {
        return official_n_2pass(arr);
    }

    public int official_n_2pass(int[] arr) {
        int n = arr.length;

        int ans = 0;

        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = arr[i] ^ pSum[i];
        }

        Map<Integer, Integer> counter = new HashMap();
        Map<Integer, Integer> total = new HashMap();

        for (int i = 0; i < n + 1; i++) {
            ans += counter.getOrDefault(pSum[i], 0) * (i - 1) - total.getOrDefault(pSum[i], 0);

            total.put(pSum[i], total.getOrDefault(pSum[i], 0) + i);
            counter.put(pSum[i], counter.getOrDefault(pSum[i], 0) + 1);
        }

        return ans;
    }

    public int official_nxn(int[] arr) {
        int n = arr.length;

        int ans = 0;

        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = arr[i] ^ pSum[i];
        }

        for (int i = 0; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (pSum[i] == pSum[j]) {
                    ans += j - i - 1;
                }
            }
        }

        return ans;
    }

    public int mySol(int[] arr) {
        int n = arr.length;

        int ans = 0;

        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = arr[i] ^ pSum[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = pSum[j] ^ pSum[i];
                for (int k = j; k < n; k++) {
                    int b = pSum[k + 1] ^ pSum[j];

                    if (a == b) ans++;
                }
            }
        }

        return ans;
    }
}