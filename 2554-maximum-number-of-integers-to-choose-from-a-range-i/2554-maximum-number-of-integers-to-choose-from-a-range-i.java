class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        return mySol_after_editorial3(banned, n, maxSum);
    }

    public int mySol_after_editorial3(int[] banned, int n, int maxSum) {
        Set<Integer> bans = new HashSet();

        for (int b : banned) {
            if (b <= n) bans.add(b);
        }

        int sum = 0;
        int count = 0;

        for (int i = 1; i <= n && maxSum - i >= 0; i++) {
            if (!bans.contains(i)) {
                maxSum -= i;
                count++;
            }
        }

        return count;
    }

    public int mySol(int[] banned, int n, int maxSum) {
        Set<Integer> bans = new HashSet();

        for (int b : banned) {
            if (b <= n) bans.add(b);
        }

        int[] arr = new int[n - bans.size()];

        int index = 0;

        for (int i = 1; i <= n; i++) {
            if (!bans.contains(i)) {
                arr[index++] = i;
            }
        }

        index = 0;

        int sum = 0;

        // System.out.println(Arrays.toString(arr));

        while (index < arr.length && sum + arr[index] <= maxSum) {
            sum += arr[index++];
        }

        return index;
    }
}