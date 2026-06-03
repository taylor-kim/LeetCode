class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(
            mySol(landStartTime, landDuration, waterStartTime, waterDuration),
            mySol(waterStartTime, waterDuration, landStartTime, landDuration)
        );
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