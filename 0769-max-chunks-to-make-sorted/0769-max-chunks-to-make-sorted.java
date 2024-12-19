class Solution {
    public int maxChunksToSorted(int[] arr) {
        return mySol(arr);
    }

    public int mySol(int[] arr) {
        int ans = 0;

        int expectedSum = 0;
        int actualSum = 0;

        for (int i = 0; i < arr.length; i++) {            
            expectedSum += i;
            actualSum += arr[i];

            // System.out.println(String.format("ex:%d, ac:%d", expectedSum, actualSum));

            if (expectedSum == actualSum) {
                ans++;
                expectedSum = 0;
                actualSum = 0;
            }

            // System.out.println(String.format("ex:%d, ac:%d\n", expectedSum, actualSum));
        }

        return ans;
    }
}