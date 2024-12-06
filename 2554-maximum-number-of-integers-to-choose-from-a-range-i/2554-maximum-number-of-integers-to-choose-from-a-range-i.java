class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        return mySol(banned, n, maxSum);
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