class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(
            solution(landStartTime, landDuration, waterStartTime, waterDuration)
            , solution(waterStartTime, waterDuration, landStartTime, landDuration)
        );
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