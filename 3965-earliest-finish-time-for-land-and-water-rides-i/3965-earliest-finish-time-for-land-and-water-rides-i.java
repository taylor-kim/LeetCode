class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(
            mySol(landStartTime, landDuration, waterStartTime, waterDuration),
            mySol(waterStartTime, waterDuration, landStartTime, landDuration)
        );
    }

    public int mySol(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int end = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) {
                ans = Math.min(ans, Math.max(end, waterStartTime[j]) + waterDuration[j]);
            }
        }

        return ans;
    }

    public int solution(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int landEnd = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) {
                if (landEnd <= waterStartTime[j]) {
                    ans = Math.min(ans, waterStartTime[j] + waterDuration[j]);
                } else {
                    ans = Math.min(ans, landEnd + waterDuration[j]);
                }
            }
        }

        return ans;
    }
}