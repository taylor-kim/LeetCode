class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        // return Math.min(
        //     official(landStartTime, landDuration, waterStartTime, waterDuration),
        //     official(waterStartTime, waterDuration, landStartTime, landDuration)
        // );

        return byHint(landStartTime, landDuration, waterStartTime, waterDuration);
    }

    public int byHint(int[] lst, int[] ld, int[] wst, int[] wd) {
        int ans = Integer.MAX_VALUE;
        int[][] array1 = getArray(lst, ld);
        int[][] array2 = getArray(wst, wd);

        for (int i = 0; i < array1.length; i++) {
            int endTime = array1[i][0] + array1[i][1];

            int minFinish = findMinFinish(array2, endTime);

            ans = Math.min(ans, minFinish);
        }

        for (int i = 0; i < array2.length; i++) {
            int endTime = array2[i][0] + array2[i][1];

            int minFinish = findMinFinish(array1, endTime);

            ans = Math.min(ans, minFinish);
        }

        return ans;
    }

    private int findMinFinish(int[][] array, int endTime) {
        int lo = 0;
        int hi = array.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (array[mid][0] <= endTime) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        int index = lo - 1;

        if (index < 0) {
            return array[0][3];
        } else if (index + 1 >= array.length) {
            return endTime + array[array.length - 1][2];
        }

        // System.out.println("array.length:%d, index:%d".formatted(array.length, index));

        return Math.min(endTime + array[index][2], array[index + 1][3]);
    }

    private int[][] getArray(int[] st, int[] d) {
        int[][] array1 = new int[st.length][4];

        for (int i = 0; i < st.length; i++) {
            array1[i][0] = st[i];
            array1[i][1] = d[i];
        }

        Arrays.sort(array1, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < st.length; i++) {
            array1[i][2] = i == 0 ? array1[i][1] : Math.min(array1[i][1], array1[i - 1][2]);

            int j = st.length - 1 - i;

            array1[j][3] = j == st.length - 1 ? array1[j][0] + array1[j][1] : Math.min(array1[j][0] + array1[j][1], array1[j + 1][3]);
        }

        return array1;
    }

    public int official(int[] lst, int[] ld, int[] wst, int[] wd) {
        int ans = Integer.MAX_VALUE;
        int endTime = Integer.MAX_VALUE;

        for (int i = 0; i < lst.length; i++) {
            endTime = Math.min(endTime, lst[i] + ld[i]);
        }

        for (int j = 0; j < wst.length; j++) {
            ans = Math.min(ans, Math.max(endTime, wst[j]) + wd[j]);
        }

        return ans;
    }

    public int mySol(int[] lst, int[] ld, int[] wst, int[] wd) {
        int ans = Integer.MAX_VALUE;
        
        Integer[] lands = new Integer[lst.length];

        for (int i = 0; i < lst.length; i++) {
            lands[i] = lst[i] + ld[i];
        }

        Arrays.sort(lands);

        int endTime = lands[0];

        for (int j = 0; j < wst.length; j++) {
            ans = Math.min(ans, Math.max(endTime, wst[j]) + wd[j]);
        }

        return ans;
    }
}