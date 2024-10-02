class Solution {
    public int[] arrayRankTransform(int[] arr) {
        return mySol(arr);
    }

    public int[] mySol(int[] arr) {
        int[][] arr2 = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++) {
            arr2[i][0] = i;
            arr2[i][1] = arr[i];
        }

        Arrays.sort(arr2, (a, b) -> {
            return a[1] - b[1];
        });

        int[] ans = new int[arr.length];

        Integer prev = null;
        int rank = 1;

        for (int i = 0; i < arr2.length; i++) {
            int index = arr2[i][0];
            int num = arr2[i][1];

            if (prev != null && prev < num) {
                rank++;
            }

            ans[index] = rank;

            prev = num;
        }

        return ans;
    }
}