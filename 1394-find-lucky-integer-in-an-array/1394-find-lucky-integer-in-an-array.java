class Solution {
    public int findLucky(int[] arr) {
        return mySol(arr);
    }

    public int mySol(int[] arr) {
        int[] counter = new int[501];

        for (int num : arr) {
            counter[num]++;
        }

        int ans = 500;

        while (ans > 0) {
            if (counter[ans] == ans) {
                return ans;
            }

            ans--;
        }

        return -1;
    }
}