class Solution {
    public long maximumCoins(int[][] coins, int k) {
        return lee(coins, k);
    }

    public long lee(int[][] coins, int k) {
        int n = coins.length;

        Arrays.sort(coins, (a, b) -> {
            return a[0] - b[0];
        });

        long ans = 0;
        long sum = 0;

        for (int left = 0, right = 0; left < n; left++) {
            if (right + 1 < left) throw new RuntimeException(String.format("left:%d, right:%d", left, right));

            while (right < n && coins[right][1] <= coins[left][0] + k - 1) {
                sum += 1l * (coins[right][1] - coins[right][0] + 1) * coins[right][2];
                right++;
            }

            if (right < n) {
                long part = 1l * Math.max(0, coins[left][0] + k - coins[right][0]) * coins[right][2];
                ans = Math.max(ans, sum + part);
            }

            sum -= 1l * (coins[left][1] - coins[left][0] + 1) * coins[left][2];
        }

        sum = 0;

        for (int left = 0, right = 0; left < n; left++) {
            sum += 1l * (coins[left][1] - coins[left][0] + 1) * coins[left][2];

            while (right < n && coins[right][1] < coins[left][1] - k + 1) {
                sum -= 1l * (coins[right][1] - coins[right][0] + 1) * coins[right][2];
                right++;
            }

            long part = 1l * Math.max(0, coins[left][1] - k + 1 - coins[right][0]) * coins[right][2];
            ans = Math.max(ans, sum - part);
        }

        return ans;
    }

    public long tryAgain_20250106_fail(int[][] coins, int k) {
        Arrays.sort(coins, (a, b) -> {
            return a[0] - b[0];
        });

        long ans = 0;
        long sum = 0;

        int n = coins.length;
        int left = coins[0][0];
        int coinLeft = 0;
        int coinRight = 0;

        for (int right = left; right <= coins[n - 1][1]; right++) {
            if (right > coins[coinRight][1]) {
                right = coins[++coinRight][0];
            }

            int remainCapa = coins[coinRight][1] - right + 1;
            int lengthSoFar = right - left;

            int canObtain = k - lengthSoFar;

            System.out.println(String.format("remainCapa:%d, canObtain:%d", remainCapa, canObtain));

            if (remainCapa <= canObtain) {
                sum += remainCapa * coins[coinRight][2];
                right = coins[coinRight][1];
            } else {
                sum += canObtain * coins[coinRight][2];
                right += canObtain;
            }

            // if (right - left + 1 > k) {
            //     sum -= coins[coinLeft][2];
            //     left++;
            // }

            // if (left > coins[coinLeft][1]) {
            //     left = coins[++coinLeft][0];
            // }
            
            ans = Math.max(ans, sum);
        }

        return ans;
    }

    public long mySol2_mle(int[][] coins, int k) {
        Arrays.sort(coins, (a, b) -> {
            return a[0] - b[0];
        });

        long sum = 0;
        long ans = 0;

        int window = 0;

        for (int i = 0; i < coins.length; i++) {
            int start = coins[i][0];
            int end = coins[i][1];

            int currentLength = end - start + 1;

            // if () {
                
            // }
        }

        return ans;
    }

    public long mySol(int[][] coins, int k) {
        List<Integer> list = new ArrayList();

        TreeMap<Integer, Integer> map = new TreeMap();

        for (int[] coin : coins) {
            for (int i = coin[0]; i <= coin[1]; i++) {
                map.put(i, coin[2]);
            }
        }

        int min = map.firstKey();
        int max = map.lastKey();
        
        int left = min;
        long sum = 0;

        long ans = 0;

        for (int right = min; right <= max; right++) {
            sum += map.getOrDefault(right, 0);

            ans = Math.max(ans, sum);

            if (right - left + 1 == k) {
                sum -= map.getOrDefault(left++, 0);
            }
        }

        return ans;
    }
}